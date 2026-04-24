package com.luis.sebolivros.domain.editora.service;

import com.luis.sebolivros.domain.editora.dto.EditoraDTO;
import com.luis.sebolivros.domain.editora.entity.Editora;
import com.luis.sebolivros.domain.editora.repository.EditoraRepository;
import com.luis.sebolivros.domain.livro.entity.Livro;
import com.luis.sebolivros.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class EditoraServiceTest {
    
    @Mock
    private EditoraRepository repository;

    @Autowired
    @InjectMocks
    private EditoraService EditoraService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find by id Editora successfully")
    void findByIdCase1(){
        int id = 1;

        Editora obj = new Editora("Livre", id);

        when(repository.findById(id)).thenReturn(Optional.of(obj));

        Editora result = EditoraService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Livre", result.getNome());

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should throw Exception when not get find by id Editora")
    void findByIdCase2() throws Exception{
        int id = 1;


        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception thrown = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            EditoraService.findById(id); });

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should find all Editora successfully")
    void findAllCase1() {

        Editora obj01 = new Editora("Livre", 1);
        Editora obj02 = new Editora("Beneiro", 2);
        Editora obj03 = new Editora("Alves", 3);

        when(repository.findAll()).thenReturn(List.of(obj01, obj02, obj03));

        List<Editora> result = EditoraService.findAll();

        assertNotNull(result);
        assertEquals(3, result.size());

        assertEquals("Livre", result.get(0).getNome());
        assertEquals(1, result.get(0).getId());

        assertEquals("Beneiro", result.get(1).getNome());
        assertEquals(2, result.get(1).getId());

        assertEquals("Alves", result.get(2).getNome());
        assertEquals(3, result.get(2).getId());

        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find all Editora empty")
    void findAllCase2() throws Exception{
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<Editora> result = EditoraService.findAll();

        assertEquals(0, result.size());
        assertTrue(result.isEmpty());

        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should create Editora successfully")
    void create() {
        Editora obj = new Editora("Livre", 1);

        when(repository.save(any(Editora.class))).thenReturn(obj);

        Editora result = EditoraService.create(new EditoraDTO(obj));

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Livre", result.getNome());

        verify(repository, times(1)).save(any(Editora.class));
    }


    @Test
    @DisplayName("Should update Editora successfully")
    void updateCase1() {
        int id = 1;

        Editora obj = new Editora("Livre", id);

        when(repository.findById(id)).thenReturn(Optional.of(obj));

        when(repository.save(any(Editora.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Editora result = EditoraService.update(id, new EditoraDTO("Beneiro"));

        assertNotNull(result);
        assertEquals("Beneiro", result.getNome());
        assertEquals(id, result.getId());

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(Editora.class));
    }

    @Test
    @DisplayName("Should not found Editora in update")
    void updateCase2() throws Exception {
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception thrown = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            EditoraService.update(id, new EditoraDTO("Beneiro")); });

        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Should delete Editora successfully")
    void deleteCase1() {
        int id = 1;

        Livro livro = new Livro();

        Editora obj = new Editora("Livre", id);

        when(repository.findById(id)).thenReturn(Optional.of(obj));

        EditoraService.delete(id);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(obj);
    }

    @Test
    @DisplayName("Should delete not found Editora")
    void deleteCase2() {
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception thrown = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            EditoraService.delete(id);
        });

        verify(repository, times(1)).findById(id);
        verify(repository, never()).delete(any());
    }
}