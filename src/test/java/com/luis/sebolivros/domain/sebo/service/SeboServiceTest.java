package com.luis.sebolivros.domain.sebo.service;

import com.luis.sebolivros.domain.sebo.dto.SeboDTO;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import com.luis.sebolivros.domain.sebo.repository.SeboRepository;
import com.luis.sebolivros.domain.sebo.service.SeboService;
import com.luis.sebolivros.domain.livro.entity.Livro;
import com.luis.sebolivros.domain.usuario.repository.UsuarioRepository;
import com.luis.sebolivros.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SeboServiceTest {

    @Mock
    private SeboRepository repository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private SeboService SeboService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find by id Sebo successfully")
    void findByIdCase1(){
        int id = 1;

        Sebo obj = new Sebo(1, "Maravilha", "maravilha@gmail.com", "123", "38204-054");

        when(repository.findById(id)).thenReturn(Optional.of(obj));

        Sebo result = SeboService.findById(id);

        assertNotNull(result);
        assertEquals("Maravilha", result.getNome());
        assertEquals(1, result.getId());
        assertEquals("maravilha@gmail.com", result.getEmail());
        assertEquals("123", result.getSenha());
        assertEquals("38204-054", result.getCep());

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should throw Exception when not get find by id Sebo")
    void findByIdCase2() throws Exception{
        int id = 1;


        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception thrown = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            SeboService.findById(id); });

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should find all Sebo successfully")
    void findAllCase1() {

        Sebo obj01 = new Sebo(1, "Maravilha", "maravilha@gmail.com", "123", "38204-054");
        Sebo obj02 = new Sebo(2, "Maravilha1", "maravilha1@gmail.com", "1232", "38204-054");
        Sebo obj03 = new Sebo(3, "Maravilha2", "maravilha2@gmail.com", "1233", "38204-054");

        when(repository.findAll()).thenReturn(List.of(obj01, obj02, obj03));

        List<Sebo> result = SeboService.findAll();

        assertNotNull(result);
        assertEquals(3, result.size());

        assertEquals("Maravilha", result.get(0).getNome());
        assertEquals(1, result.get(0).getId());
        assertEquals("maravilha@gmail.com", result.get(0).getEmail());
        assertEquals("123", result.get(0).getSenha());
        assertEquals("38204-054", result.get(0).getCep());


        assertEquals("Maravilha1", result.get(1).getNome());
        assertEquals(2, result.get(1).getId());
        assertEquals("maravilha1@gmail.com", result.get(1).getEmail());
        assertEquals("1232", result.get(1).getSenha());
        assertEquals("38204-054", result.get(1).getCep());


        assertEquals("Maravilha2", result.get(2).getNome());
        assertEquals(3, result.get(2).getId());
        assertEquals("maravilha2@gmail.com", result.get(2).getEmail());
        assertEquals("1233", result.get(2).getSenha());
        assertEquals("38204-054", result.get(2).getCep());


        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find all Sebo empty")
    void findAllCase2() throws Exception{
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<Sebo> result = SeboService.findAll();

        assertEquals(0, result.size());
        assertTrue(result.isEmpty());

        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should create Sebo successfully")
    void create() {
        Sebo obj = new Sebo(1, "Maravilha", "maravilha@gmail.com", "123", "38204-054");

        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        when(encoder.encode(anyString())).thenAnswer(invocation -> invocation.getArgument(0));

        when(repository.save(any(Sebo.class))).thenReturn(obj);

        Sebo result = SeboService.create(new SeboDTO(obj));

        assertNotNull(result);
        assertEquals("Maravilha", result.getNome());
        assertEquals(1, result.getId());
        assertEquals("maravilha@gmail.com", result.getEmail());
        assertEquals("123", result.getSenha());
        assertEquals("38204-054", result.getCep());


        verify(repository, times(1)).save(any(Sebo.class));
    }


    @Test
    @DisplayName("Should update Sebo successfully")
    void updateCase1() {
        int id = 1;

        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        when(encoder.encode(anyString())).thenAnswer(invocation -> invocation.getArgument(0));

        Sebo obj = new Sebo(1, "Maravilha", "maravilha@gmail.com", "123", "38204-054");

        when(repository.findById(id)).thenReturn(Optional.of(obj));

        when(repository.save(any(Sebo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Sebo result = SeboService.update(id, new SeboDTO(1, "Maravilha1", "maravilha1@gmail.com", "1231", "38204-054"));

        assertNotNull(result);
        assertEquals("Maravilha1", result.getNome());
        assertEquals(1, result.getId());
        assertEquals("maravilha1@gmail.com", result.getEmail());
        assertEquals("1231", result.getSenha());
        assertEquals("38204-054", result.getCep());

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(Sebo.class));
    }

    @Test
    @DisplayName("Should not found Sebo in update")
    void updateCase2() throws Exception {
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception thrown = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            SeboService.update(id, new SeboDTO(1, "Maravilha1", "maravilha1@gmail.com", "1231", "38204-054")); });

        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Should delete Sebo successfully")
    void deleteCase1() {
        int id = 1;

        Livro livro = new Livro();

        Sebo obj = new Sebo(1, "Maravilha1", "maravilha1@gmail.com", "1231", "38204-054");

        when(repository.findById(id)).thenReturn(Optional.of(obj));

        SeboService.delete(id);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(obj);
    }

    @Test
    @DisplayName("Should delete not found Sebo")
    void deleteCase2() {
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception thrown = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            SeboService.delete(id);
        });

        verify(repository, times(1)).findById(id);
        verify(repository, never()).delete(any());
    }

}