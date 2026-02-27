package com.luis.sebolivros.domain.sebo.service;

import com.luis.sebolivros.domain.usuario.entity.Usuario;
import com.luis.sebolivros.domain.usuario.repository.UsuarioRepository;
import com.luis.sebolivros.exceptions.ObjectNotFoundException;
import com.luis.sebolivros.domain.sebo.dto.SeboDTO;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import com.luis.sebolivros.domain.sebo.repository.SeboRepository;
import com.luis.sebolivros.infra.cep.client.CepClient;
import com.luis.sebolivros.infra.cep.dto.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import com.luis.sebolivros.exceptions.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class SeboService {

    @Autowired
    private SeboRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private CepClient cepClient;

    public Sebo findById(int id){
        Optional<Sebo> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Sebo não encontrado"));
    }

    public List<Sebo> findAll(){
        return repository.findAll();
    }

    public Sebo create(SeboDTO objDto){
        objDto.setId(null);
        validarEmail(objDto);
        objDto.setSenha(encoder.encode(objDto.getSenha()));
        return repository.save(new Sebo(objDto));
    }

    public Sebo update(Integer id, SeboDTO objDto){
        objDto.setId(id);
        validarEmail(objDto);
        Sebo oldObj = findById(id);

        if (!objDto.getSenha().equals(oldObj.getSenha())){
            objDto.setSenha(encoder.encode(objDto.getSenha()));
        }

        oldObj = new Sebo(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id){
        Sebo obj = findById(id);
        repository.delete(obj);
    }

    private void validarEmail(SeboDTO objDto){
        Optional<Usuario> obj = usuarioRepository.findByEmail(objDto.getEmail());

        if (obj.isPresent()){
            throw new DataIntegrityViolationException("Email já cadastrado");
        }
    }

    public EnderecoDTO findAddress(Integer id){
        Sebo obj = findById(id);
        return cepClient.viaCep(obj.getCep().replace("-", "")).block();
    }
}
