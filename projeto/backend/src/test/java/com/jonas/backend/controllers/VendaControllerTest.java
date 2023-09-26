package com.jonas.backend.controllers;

import com.jonas.backend.dto.LivroDTO;
import com.jonas.backend.dto.VendaDTO;
import com.jonas.backend.entities.Cliente;
import com.jonas.backend.entities.Livro;
import com.jonas.backend.entities.PessoaFisica;
import com.jonas.backend.entities.Venda;
import com.jonas.backend.services.ClientService;
import com.jonas.backend.services.VendaService;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@SpringBootTest
public class VendaControllerTest {

    private static final Integer INDEX = 0;
    private static final Long ID = 1L;
    private static final String CLIENTE_NOME = "Ana Green";
    private static final Integer QTD_ITENS = 10;
    private static final Double PRECO_VENDA = 15.20;

    @InjectMocks
    private VendaController vendaController;

    @Mock
    private VendaService vendaService;

    @Mock
    private ClientService clientService;

    @Mock
    private ModelMapper mapper;

    private final ModelMapper modelMapper = new ModelMapper();

    private final List<Venda> listaVenda = new ArrayList<>();

    private Venda venda;
    private VendaDTO vendaDTO;
    private Livro livro;
    private LivroDTO livroDTO;
    private Cliente cliente;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        startSale();

    }

    @Test
    void whenFindAll_ThenReturnAListOfVendaDTO() {

        when(vendaService.findAll())
                .thenReturn(listaVenda);
        when(mapper.map(any(), any()))
                .thenReturn(vendaDTO);

        ResponseEntity<List<VendaDTO>> response = vendaController.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(VendaDTO.class, response.getBody().get(INDEX).getClass());

        assertSale(modelMapper.map(
                response.getBody().get(INDEX), Venda.class));

        verify(vendaService, times(1)).findAll();

    }

    @Test
    public void whenFindById_ThenReturnAnVendaDTO() {

        when(vendaService.findById(anyLong()))
                .thenReturn(venda);
        when(mapper.map(any(), any()))
                .thenReturn(vendaDTO);

        ResponseEntity<VendaDTO> response = vendaController.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(VendaDTO.class, response.getBody().getClass());

        assertSale(modelMapper.map(response.getBody(), Venda.class));

        verify(vendaService, times(1)).findById((ID));

    }

    @Test
    public void whenFindSaleByClient_ThenReturnListOfVendaDTO() {

        when(vendaService.findSaleByClient(anyInt()))
                .thenReturn(listaVenda);
        when(mapper.map(any(), any()))
                .thenReturn(vendaDTO);

        ResponseEntity<List<VendaDTO>> response = vendaController
                .findSaleByClient(1);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(VendaDTO.class, response.getBody().get(INDEX).getClass());

        assertSale(modelMapper.map(
                response.getBody().get(INDEX), Venda.class));

        verify(vendaService, times(1)).findSaleByClient(1);

    }

    @Test
    void whenInsertAnSale_ThenReturnCreated() {

        when(mapper.map(any(), eq(VendaDTO.class)))
                .thenReturn(vendaDTO);
        when(mapper.map(any(), eq(Venda.class)))
                .thenReturn(venda);
        when(clientService.findByNome(CLIENTE_NOME))
                .thenReturn(cliente);
        when(vendaService.insert(venda))
                .thenReturn(venda);

        ResponseEntity<VendaDTO> response = vendaController.insert(vendaDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        URI expectedUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(1L).toUri();
        assertEquals(expectedUri, response.getHeaders().getLocation());

        assertSale(modelMapper.map(response.getBody(), Venda.class));

        verify(vendaService, times(1)).insert(any());

    }

    @Test
    void whenUpdateAnSale_ThenReturnSuccess() {

        when(mapper.map(any(), eq(VendaDTO.class)))
                .thenReturn(vendaDTO);
        when(mapper.map(any(), eq(Venda.class)))
                .thenReturn(venda);
        when(clientService.findByNome(CLIENTE_NOME))
                .thenReturn(cliente);
        when(vendaService.update(ID, venda)).thenReturn(venda);

        ResponseEntity<VendaDTO> response = vendaController
                .update(ID, vendaDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(VendaDTO.class, response.getBody().getClass());

        assertSale(modelMapper.map(response.getBody(), Venda.class));

        verify(vendaService, times(1)).update(eq(ID), any());

    }

    @Test
    void whenDeleteAnSale_ThenReturnSuccess() {

        doNothing()
                .when(vendaService)
                .delete(anyLong());

        ResponseEntity<Void> response = vendaController.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(vendaService, times(1)).delete(eq(ID));

    }

    private void assertSale(Venda venda) {

        assertEquals(ID, venda.getId());
        assertEquals(CLIENTE_NOME, venda.getCliente().getNome());
        assertEquals(livro, venda.getLivro());
        assertEquals(QTD_ITENS, venda.getQtdItens());
        assertEquals(PRECO_VENDA, venda.getPrecoVenda());

        assertEquals(Venda.class, venda.getClass());

    }

    private void startSale() {

        livroDTO = new LivroDTO(
                ID,
                "Harry Potter e a Pedra Filosofal",
                "J. K. Rowling", "Presenca",
                "https://",
                "14/10/1999",
                30);

        livro = new Livro(
                ID,
                "Harry Potter e a Pedra Filosofal",
                "J. K. Rowling", "Presenca",
                "https://",
                "14/10/1999",
                30);

        cliente = new PessoaFisica(
                ID,
                CLIENTE_NOME,
                "Rua São José",
                658,
                "Centro",
                "São Paulo",
                "SP",
                "37890-000",
                "12.345.678-9",
                "123.456.789-09"
        );

        venda = new Venda(
                ID,
                cliente,
                livro,
                QTD_ITENS,
                PRECO_VENDA);

        vendaDTO = new VendaDTO(
                ID,
                CLIENTE_NOME,
                livroDTO,
                QTD_ITENS,
                PRECO_VENDA,
                152.00
        );

        listaVenda.add(venda);

    }

}
