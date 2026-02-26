package com.luis.sebolivros.domain.autor.service;

import com.luis.sebolivros.domain.autor.dto.AutorDTO;
import com.luis.sebolivros.domain.autor.entity.Autor;
import com.luis.sebolivros.domain.autor.repository.AutorRepository;
import com.luis.sebolivros.domain.livro.entity.Livro;
import com.luis.sebolivros.domain.livro.enums.Condicao;
import com.luis.sebolivros.domain.livro.enums.Estado;
import com.luis.sebolivros.exceptions.DataIntegrityViolationException;
import com.luis.sebolivros.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AutorServiceTest {

    @Mock
    private AutorRepository repository;

    @Autowired
    @InjectMocks
    private AutorService autorService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find by id Autor successfully")
    void findByIdCase1(){
        int id = 1;

        Autor obj = new Autor("Marcos", id);

        when(repository.findById(id)).thenReturn(Optional.of(obj));

        Autor result = autorService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Marcos", result.getNome());

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should throw Exception when not get find by id Autor")
    void findByIdCase2() throws Exception{
        int id = 1;


        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception thrown = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            autorService.findById(id); });

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should find all Autor successfully")
    void findAllCase1() {

        Autor obj01 = new Autor("Marcos", 1);
        Autor obj02 = new Autor("Thiago", 2);
        Autor obj03 = new Autor("Julio", 3);

        when(repository.findAll()).thenReturn(List.of(obj01, obj02, obj03));

        List<Autor> result = autorService.findAll();

        assertNotNull(result);
        assertEquals(3, result.size());

        assertEquals("Marcos", result.get(0).getNome());
        assertEquals(1, result.get(0).getId());

        assertEquals("Thiago", result.get(1).getNome());
        assertEquals(2, result.get(1).getId());

        assertEquals("Julio", result.get(2).getNome());
        assertEquals(3, result.get(2).getId());

        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find all Autor empty")
    void findAllCase2() throws Exception{
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<Autor> result = autorService.findAll();

        assertEquals(0, result.size());
        assertTrue(result.isEmpty());

        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should create Autor successfully")
    void create() {
        Autor obj = new Autor("Marcos", 1);

        when(repository.save(any(Autor.class))).thenReturn(obj);

        Autor result = autorService.create(new AutorDTO(obj));

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Marcos", result.getNome());
        assertTrue(result.getLivros().isEmpty());

        verify(repository, times(1)).save(any(Autor.class));
    }


    @Test
    @DisplayName("Should update Autor successfully")
    void updateCase1() {
        int id = 1;

        Autor obj = new Autor("Marcos", id);

        when(repository.findById(id)).thenReturn(Optional.of(obj));

        when(repository.save(any(Autor.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Autor result = autorService.update(id, new AutorDTO("Lucas"));

        assertNotNull(result);
        assertEquals("Lucas", result.getNome());
        assertEquals(id, result.getId());
        assertTrue(result.getLivros().isEmpty());

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(Autor.class));
    }

    @Test
    @DisplayName("Should not found Autor in update")
    void updateCase2() throws Exception {
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception thrown = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            autorService.update(id, new AutorDTO("Lucas")); });

        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Should delete Autor successfully")
    void deleteCase1() {
        int id = 1;

        Livro livro = new Livro();

        Autor obj = new Autor("Marcos", id);

        when(repository.findById(id)).thenReturn(Optional.of(obj));

        autorService.delete(id);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(obj);
    }

    @Test
    @DisplayName("Should delete not found Autor")
    void deleteCase2() {
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception thrown = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            autorService.delete(id);
        });

        verify(repository, times(1)).findById(id);
        verify(repository, never()).delete(any());
    }

    @Test
    @DisplayName("Should delete Autor Error")
    void deleteCase3() {
        int id = 1;

        Autor obj = new Autor("Marcos", id);

        Livro livro01 = new Livro(null, "A Odisseia", 3, obj, null, "9788563560271", Condicao.CONSERVADO, Estado.DISPONIVEL, null);

        obj.getLivros().add(livro01);

        when(repository.findById(id)).thenReturn(Optional.of(obj));

        Exception thrown = Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            autorService.delete(id);
        });

        verify(repository, times(1)).findById(id);
        verify(repository, never()).delete(any());
    }
}