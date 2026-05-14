package com.luis.sebolivros.domain.itemCarrinho.service;

import com.luis.sebolivros.domain.carrinho.entity.Carrinho;
import com.luis.sebolivros.domain.carrinho.enums.Status;
import com.luis.sebolivros.domain.carrinho.service.CarrinhoService;
import com.luis.sebolivros.domain.cliente.entity.Cliente;
import com.luis.sebolivros.domain.cliente.service.ClienteService;
import com.luis.sebolivros.domain.estoque.entity.Estoque;
import com.luis.sebolivros.domain.estoque.service.EstoqueService;
import com.luis.sebolivros.domain.itemCarrinho.dto.ItemCarrinhoDTO;
import com.luis.sebolivros.domain.itemCarrinho.entity.ItemCarrinho;
import com.luis.sebolivros.domain.itemCarrinho.repository.ItemCarrinhoRepository;
import com.luis.sebolivros.exceptions.DataIntegrityViolationException;
import com.luis.sebolivros.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCarrinhoService {

    @Autowired
    private ItemCarrinhoRepository repository;

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private CarrinhoService carrinhoService;

    public ItemCarrinho findById(int id){
        Optional<ItemCarrinho> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Item de Carrinho não encontrado"));
    }

    public List<ItemCarrinho> findAll(){
        return repository.findAll();
    }

    public ItemCarrinho create(ItemCarrinhoDTO objDTO){

        objDTO.setId(null);

        ItemCarrinho itemExistente = verificar(objDTO);

        if (itemExistente != null){
            return itemExistente;
        }

        ItemCarrinho obj = newItemCarrinho(objDTO);
        return repository.save(obj);
    }

    private ItemCarrinho verificar(ItemCarrinhoDTO objDto){
        Carrinho carrinho = carrinhoService.findById(objDto.getCarrinho());

        for (ItemCarrinho x : carrinho.getItens()){
            if (x.getEstoque().getId().equals(objDto.getEstoque())){
                int quantidade = x.getQuantidade() + 1;
                Estoque estoque = estoqueService.findById(objDto.getEstoque());
                if (quantidade > estoque.getQuantidade()){
                    throw new DataIntegrityViolationException("Quantidade indisponível");
                }
                x.setQuantidade(quantidade);
                return repository.save(x);
            }
        }
        return null;
    }


    public ItemCarrinho update(int id, ItemCarrinhoDTO objDTO){
        ItemCarrinho newObj = findById(id);
        objDTO.setId(newObj.getId());
        newObj = newItemCarrinho(objDTO);
        return repository.save(newObj);
    }

    private void validarQuantidade(ItemCarrinho obj){
        if (obj.getQuantidade() > obj.getEstoque().getQuantidade()){
            throw new DataIntegrityViolationException("Quantidade em estoque não disponivel");
        }
    }

    private void validarCarrinho(ItemCarrinho obj){
        if (obj.getCarrinho().getStatus().equals(Status.FINALIZADO)){
            throw new DataIntegrityViolationException("Não é possível alterar um carrinho finalizado");
        }
    }

    public void delete(int id){
        ItemCarrinho obj = findById(id);
        validarCarrinho(obj);
        repository.delete(obj);
    }

    private ItemCarrinho newItemCarrinho(ItemCarrinhoDTO objDTO){
        Estoque estoque = estoqueService.findById(objDTO.getEstoque());
        Carrinho carrinho = carrinhoService.findById(objDTO.getCarrinho());

        ItemCarrinho obj = new ItemCarrinho();

        if (objDTO.getId() != null){
            obj.setId(objDTO.getId());
        }

        obj.setEstoque(estoque);
        obj.setQuantidade(objDTO.getQuantidade());
        obj.setCarrinho(carrinho);

        validarQuantidade(obj);
        validarCarrinho(obj);
        return obj;
    }
}
