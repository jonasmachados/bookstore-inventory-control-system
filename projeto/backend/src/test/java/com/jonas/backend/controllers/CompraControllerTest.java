package com.jonas.backend.controllers;

import com.jonas.backend.dto.CompraDTO;
import com.jonas.backend.dto.LivroDTO;
import com.jonas.backend.entities.Compra;
import com.jonas.backend.entities.Livro;
import com.jonas.backend.services.CompraService;
import com.jonas.backend.services.LivroService;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
public class CompraControllerTest {

    private static final Integer INDEX = 0;
    private static final Long ID = 1L;
    private static final Integer QTD_ITENS = 30;
    private static final Double PRECO_VENDA = 20.20;
    private static final Double TOTAL = 606.00;

    @InjectMocks
    private CompraController compraController;

    @Mock
    private CompraService compraService;

    @Mock
    private LivroService livroService;

    @Mock
    private ModelMapper mapper;

    private final ModelMapper modelMapper = new ModelMapper();

    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

    private Livro livro;
    private LivroDTO livroDTo;
    private Compra compra;
    private CompraDTO compraDTO;

    @BeforeEach
    void setUp() throws ParseException {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindAllThenReturnAListOfCompraDTO() {

        when(compraService.findAll()).thenReturn(List.of(compra));
        when(mapper.map(any(), any())).thenReturn(compraDTO);

        ResponseEntity<List<CompraDTO>> response = compraController.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(CompraDTO.class, response.getBody().get(INDEX).getClass());

        assertPurchase(modelMapper.map(
                response.getBody().get(INDEX), Compra.class));

        verify(compraService, times(1)).findAll();

    }

    @Test
    public void whenFindByIdThenReturnAnCompraDTO() {

        when(compraService.findById(anyLong())).thenReturn(compra);
        when(mapper.map(any(), any())).thenReturn(compraDTO);

        ResponseEntity<CompraDTO> response = compraController.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(CompraDTO.class, response.getBody().getClass());

        assertPurchase(modelMapper.map(response.getBody(), Compra.class));

        verify(compraService, times(1)).findById((ID));

    }

    @Test
    void whenInsertAnPurchaseThenReturnCreated() {

        when(compraService.insert(compra)).thenReturn(compra);
        when(mapper.map(any(), any())).thenReturn(compraDTO);

        ResponseEntity<CompraDTO> response = compraController.insert(compra);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        URI expectedUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(1L).toUri();
        assertEquals(expectedUri, response.getHeaders().getLocation());

        assertPurchase(modelMapper.map(response.getBody(), Compra.class));

        verify(compraService, times(1)).insert(compra);

    }

    @Test
    void whenUpdateAnPurchaseThenReturnSuccess() {

        when(compraService.update(ID, compra)).thenReturn(compra);
        when(mapper.map(any(), any())).thenReturn(compraDTO);

        ResponseEntity<CompraDTO> response = compraController
                .update(ID, compra);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(CompraDTO.class, response.getBody().getClass());

        assertPurchase(modelMapper.map(response.getBody(), Compra.class));

        verify(compraService, times(1)).update(ID, compra);

    }

    @Test
    void whenDeleteAnPurchaselWithSuccess() {

        doNothing().when(compraService).delete(anyLong());

        ResponseEntity<CompraDTO> response = compraController.delete(ID);

        assertNotNull(response);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(compraService, times(1)).delete(anyLong());

    }

    private void assertPurchase(Compra Compra) {

        assertEquals(ID, Compra.getId());
        assertEquals(livro, Compra.getLivro());
        assertEquals(QTD_ITENS, Compra.getQtdItens());
        assertEquals(PRECO_VENDA, Compra.getPrecoVenda());

    }

    private void startUser() throws ParseException {

        livro = new Livro(ID,
                "O Senhor dos Anéis",
                "J. R. R. Tolkien",
                "Allen & Unwin",
                "https://",
                formatador.parse("20/10/1955"),
                45);

        livroDTo = new LivroDTO(ID,
                "O Senhor dos Anéis",
                "J. R. R. Tolkien",
                "Allen & Unwin",
                "https://",
                formatador.parse("20/10/1955"),
                45);

        compra = new Compra(ID,
                livro,
                QTD_ITENS,
                PRECO_VENDA);

        compraDTO = new CompraDTO(ID,
                livroDTo,
                QTD_ITENS,
                PRECO_VENDA,
                TOTAL
        );

    }

}
