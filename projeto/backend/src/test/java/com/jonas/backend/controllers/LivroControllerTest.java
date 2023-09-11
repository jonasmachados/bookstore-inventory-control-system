package com.jonas.backend.controllers;

import com.jonas.backend.dto.LivroDTO;
import com.jonas.backend.entities.Cliente;
import com.jonas.backend.entities.Compra;
import com.jonas.backend.entities.Livro;
import com.jonas.backend.entities.Venda;
import com.jonas.backend.services.LivroService;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@SpringBootTest
public class LivroControllerTest {

    private static final Integer INDEX = 0;
    private static final Long ID = 1L;
    private static final String TITULO = "Harry Potter e a Pedra Filosofal";
    private static final String AUTOR = "J. K. Rowling";
    private static final String EDITORA = "Presenca";
    private static final String LINK_IMG = "https://";
    private static final String ANO_PUBLICACAO = "20/10/1955";
    private static final Integer ESTOQUE = 30;

    @InjectMocks
    private LivroController livroController;

    @Mock
    private LivroService livroService;

    @Mock
    private ModelMapper mapper;

    private final ModelMapper modelMapper = new ModelMapper();

    private Livro livro;
    private LivroDTO livroDTO;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        startUser();

    }

    @Test
    void whenFindAll_ThenReturnAListOfBooksDTO() {

        when(livroService.findAll())
                .thenReturn(List.of(livro));
        when(mapper.map(any(), any()))
                .thenReturn(livroDTO);

        ResponseEntity<List<LivroDTO>> response = livroController.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(LivroDTO.class, response.getBody().get(INDEX).getClass());

        assertBook(modelMapper.map(
                response.getBody().get(INDEX), Livro.class));

        verify(livroService, times(1)).findAll();

    }

    @Test
    void whenFindById_ThenReturnAnBookDTO() {

        when(livroService.findById(anyLong()))
                .thenReturn(livro);
        when(mapper.map(any(), any()))
                .thenReturn(livroDTO);

        ResponseEntity<LivroDTO> response = livroController.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(LivroDTO.class, response.getBody().getClass());

        assertBook(modelMapper.map(response.getBody(), Livro.class));

        verify(livroService, times(1)).findById((ID));

    }

    @Test
    void whenInsertAnBook_ThenReturnCreated() {

        when(livroService.insert(any()))
                .thenReturn(livro);
        when(mapper.map(any(), eq(LivroDTO.class)))
                .thenReturn(livroDTO);

        ResponseEntity<LivroDTO> response = livroController.insert(livroDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        URI expectedUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(1L).toUri();
        assertEquals(expectedUri, response.getHeaders().getLocation());

        assertBook(modelMapper.map(response.getBody(), Livro.class));

        verify(livroService, times(1)).insert(any());

    }

    @Test
    void whenUpdateAnBook_ThenReturnSuccess() {

        when(livroService.update(eq(ID), any()))
                .thenReturn(livro);
        when(mapper.map(any(), eq(LivroDTO.class)))
                .thenReturn(livroDTO);

        ResponseEntity<LivroDTO> response = livroController
                .update(ID, livroDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(LivroDTO.class, response.getBody().getClass());

        assertBook(modelMapper.map(response.getBody(), Livro.class));

        verify(livroService, times(1)).update(eq(ID), any());

    }

    @Test
    void whenDeleteAnBook_ThenReturnSuccess() {

        doNothing()
                .when(livroService)
                .delete(anyLong());

        ResponseEntity<LivroDTO> response = livroController.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(livroService, times(1)).delete(anyLong());

    }

    private void assertBook(Livro livro) {

        assertEquals(ID, livro.getId());
        assertEquals(TITULO, livro.getTitulo());
        assertEquals(AUTOR, livro.getAutor());
        assertEquals(EDITORA, livro.getEditora());
        assertEquals(LINK_IMG, livro.getLinkImg());
        assertEquals(ANO_PUBLICACAO, livro.getAnoPublicacao());
        assertEquals(ESTOQUE, livro.getEstoque());

    }

    private void startUser() {

        livro = new Livro(
                ID,
                TITULO,
                AUTOR,
                EDITORA,
                LINK_IMG,
                ANO_PUBLICACAO,
                ESTOQUE);

        livroDTO = new LivroDTO(ID,
                TITULO,
                AUTOR,
                EDITORA,
                LINK_IMG,
                ANO_PUBLICACAO,
                ESTOQUE);

    }

}
