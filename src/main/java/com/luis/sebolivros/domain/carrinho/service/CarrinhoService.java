package com.luis.sebolivros.domain.carrinho.service;

import com.luis.sebolivros.domain.carrinho.dto.CarrinhoDTO;
import com.luis.sebolivros.domain.carrinho.entity.Carrinho;
import com.luis.sebolivros.domain.carrinho.enums.Status;
import com.luis.sebolivros.domain.carrinho.repository.CarrinhoRepository;
import com.luis.sebolivros.domain.cliente.entity.Cliente;
import com.luis.sebolivros.domain.cliente.service.ClienteService;
import com.luis.sebolivros.domain.estoque.service.EstoqueService;
import com.luis.sebolivros.domain.itemCarrinho.entity.ItemCarrinho;
import com.luis.sebolivros.domain.itemCarrinho.service.ItemCarrinhoService;
import com.luis.sebolivros.exceptions.DataIntegrityViolationException;
import com.luis.sebolivros.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository repository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EstoqueService estoqueService;

    public Carrinho findById(int id){
        Optional<Carrinho> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Carrinho não encontrado"));
    }

    public List<Carrinho> findAll(){
        return repository.findAll();
    }

    public Carrinho create(CarrinhoDTO objDto){
        objDto.setId(null);
        if(verifiarCarrinho(objDto.getCliente()) != null){
            throw new DataIntegrityViolationException("Não e possível ter dois carrinhos ativos");
        }
        Carrinho obj = newCarrinho(objDto);
        return repository.save(obj);
    }

    private Carrinho verifiarCarrinho(int id){
        Cliente obj = clienteService.findById(id);
        for (Carrinho x : obj.getCarrinhos()){
            if (x.getStatus().equals(Status.ATIVO)){
                return x;
            }
        }
        return null;
    }

    public Carrinho finalizar(int id){
        Carrinho obj = findById(id);
        if (!validarFinalizar(obj)){
            throw new DataIntegrityViolationException("Erro na finalização de carrinho");
        }
        atualizandoEstoque(obj);
        obj.setStatus(Status.FINALIZADO);
        novoCarrinho(obj.getCliente()
                .getId());
        return repository.save(obj);
    }

    private boolean validarFinalizar(Carrinho carrinho){
        if (carrinho.getStatus().equals(Status.ATIVO)){
            if (!carrinho.getItens().isEmpty()){
                for (ItemCarrinho x : carrinho.getItens()){
                    int quantidade = x.getEstoque().getQuantidade() - x.getQuantidade();
                    if (quantidade < 0) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void novoCarrinho(int clienteId){
        create(new CarrinhoDTO(clienteId));
    }

    private void atualizandoEstoque(Carrinho obj){
        for (ItemCarrinho x : obj.getItens()){
            int quantidade = x.getEstoque().getQuantidade() - x.getQuantidade();
            // Talvez trocque x.getId();
            estoqueService.atualizarQuantidade(x.getEstoque().getId(), quantidade);
        }
    }

    private Carrinho newCarrinho(CarrinhoDTO objDto){
        Cliente cliente = clienteService.findById(objDto.getCliente());

        Carrinho obj = new Carrinho();

        obj.setCliente(cliente);
        return obj;
    }
}
