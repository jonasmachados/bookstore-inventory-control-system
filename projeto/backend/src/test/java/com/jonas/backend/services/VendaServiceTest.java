package com.jonas.backend.services;

import com.jonas.backend.entities.Cliente;
import com.jonas.backend.entities.Livro;
import com.jonas.backend.entities.Venda;
import com.jonas.backend.repositories.VendaRepository;
import com.jonas.backend.services.exceptions.DatabaseException;
import com.jonas.backend.services.exceptions.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest
public class VendaServiceTest {

    private static final Long NON_EXISTENT_ID = 999L;
    private static final String EXPECTED_MESSAGE_ID_INCORRECT = "Resource not "
            + "found. Id " + NON_EXISTENT_ID;
    private static final Integer INDEX = 0;
    private static final Long ID = 1L;
    private static final Integer QTD_ITENS = 10;
    private static final Double PRECO_VENDA = 15.20;

    @InjectMocks
    private VendaService vendaService;

    @Mock
    private VendaRepository vendaRepository;

    @Mock
    private LivroService livroService;

    private final List<Venda> listaVenda = new ArrayList<>();
    private Venda venda;
    private Optional<Venda> optionalVenda;
    private Livro livro;
    private Cliente cliente;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        startUser();

    }

    @Test
    void whenFindAll_ThenReturnAListOfSales() {

        when(vendaRepository.findAll())
                .thenReturn(listaVenda);

        List<Venda> response = vendaService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());

        Venda newVenda = response.get(INDEX);
        assertSale(newVenda);

        verify(vendaRepository, times(1)).findAll();

    }

    @Test
    public void whenFindById_ThenReturnAnSale() {

        when(vendaRepository.findById(anyLong()))
                .thenReturn(optionalVenda);

        Venda response = vendaService.findById(ID);

        assertNotNull(response);

        assertSale(response);

        verify(vendaRepository, times(1)).findById((ID));

    }

    @Test
    void whenFindByIdNonexistent_ThenReturnAnObjectNotFoundException() {

        when(vendaRepository.findById(anyLong()))
                .thenThrow(new ResourceNotFoundException(NON_EXISTENT_ID));

        try {
            vendaService.findById(NON_EXISTENT_ID);
        } catch (Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals(EXPECTED_MESSAGE_ID_INCORRECT, ex.getMessage());
        }

    }

    @Test
    void whenFindSaleByClient_ThenReturnListOfSales() {

        when(vendaRepository.findSaleByClient(anyInt()))
                .thenReturn(listaVenda);

        List<Venda> response = vendaService.findSaleByClient(1);

        assertNotNull(response);

        assertEquals(1, response.size());
        assertEquals(ArrayList.class, response.getClass());

        Venda newSale = response.get(INDEX);
        assertSale(newSale);

        verify(vendaRepository, times(1)).findSaleByClient(1);

    }

    @Test
    void whenInsertAnSale_ThenReturnSuccess() {

        when(vendaRepository.save(any()))
                .thenReturn(venda);
        when(livroService.findById(anyLong()))
                .thenReturn(livro);

        Venda response = vendaService.insert(venda);

        assertNotNull(response);

        assertSale(response);

        verify(vendaRepository, times(1)).save(venda);

    }

    @Test
    void whenInsertAnSaleWithQuantityEqual0_ThenThrowIllegalArgumentException() {

        when(livroService.findById(anyLong()))
                .thenReturn(livro);

        venda.setQtdItens(0);

        try {
            vendaService.insert(venda);
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertEquals("Quantidade deve ser maior ou igual a zero.",
                    ex.getMessage());
        }

    }

    @Test
    void whenInsertSaleWithInvalidQuantity_ThenThrowIllegalArgumentException() {

        when(livroService.findById(anyLong()))
                .thenReturn(livro);

        venda.setQtdItens(500);

        try {
            vendaService.insert(venda);
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertEquals("Estoque insuficiente.",
                    ex.getMessage());
        }

    }

    @Test
    void whenUpdateSale_ThenReturnSuccess() {

        when(vendaRepository.findById(anyLong()))
                .thenReturn(optionalVenda);
        when(vendaRepository.save(any()))
                .thenReturn(venda);
        when(livroService.findById(anyLong()))
                .thenReturn(livro);

        Venda response = vendaService.update(ID, venda);

        assertNotNull(response);

        assertSale(response);

        verify(vendaRepository, times(1)).save(venda);

    }

    @Test
    void whenUpdatingSaleWithIdNonexistent_ThenReturnResourceNotFoundException() {

        when(vendaRepository.findById(anyLong()))
                .thenReturn(optionalVenda);
        when(vendaRepository.save(any(Venda.class)))
                .thenThrow(EntityNotFoundException.class);

        try {
            vendaService.update(NON_EXISTENT_ID, venda);
        } catch (ResourceNotFoundException ex) {
            assertEquals(EXPECTED_MESSAGE_ID_INCORRECT, ex.getMessage());
            assertEquals(ResourceNotFoundException.class, ex.getClass());
        }

    }

    @Test
    void whenDeleteAnSale_ThenReturnSuccess() {

        when(vendaRepository.findById(anyLong()))
                .thenReturn(optionalVenda);
        doNothing().when(vendaRepository).deleteById(anyLong());

        vendaService.delete(ID);

        verify(vendaRepository, times(1)).deleteById(anyLong());

    }

    @Test
    void whenDeleteSaleWithNonexistentId_ThenThrowResourceNotFoundException() {

        when(vendaRepository.findById(anyLong()))
                .thenReturn(optionalVenda);
        doThrow(EmptyResultDataAccessException.class)
                .when(vendaRepository).deleteById(NON_EXISTENT_ID);

        try {
            vendaService.delete(NON_EXISTENT_ID);
        } catch (Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals(EXPECTED_MESSAGE_ID_INCORRECT, ex.getMessage());
        }

        verify(vendaRepository, times(1)).deleteById(anyLong());

    }

    @Test
    void whenDeleteSale_ThenThrowDataIntegrityException() {

        when(vendaRepository.findById(anyLong()))
                .thenReturn(optionalVenda);
        doThrow(DataIntegrityViolationException.class)
                .when(vendaRepository).deleteById(ID);

        try {
            vendaService.delete(ID);
        } catch (Exception ex) {
            assertEquals(DatabaseException.class, ex.getClass());
            assertEquals("Erro de violação de integridade nos dados",
                    ex.getMessage());
        }

        verify(vendaRepository, times(1)).deleteById(anyLong());

    }

    private void assertSale(Venda venda) {

        assertEquals(ID, venda.getId());
        assertEquals(cliente, venda.getCliente());
        assertEquals(livro, venda.getLivro());
        assertEquals(QTD_ITENS, venda.getQtdItens());
        assertEquals(PRECO_VENDA, venda.getPrecoVenda());
        
        assertEquals(Venda.class, venda.getClass());

    }

    private void startUser() {

        livro = new Livro(ID,
                "Harry Potter e a Pedra Filosofal",
                "J. K. Rowling", "Presenca",
                "https://",
                "14/10/1999",
                30);

        venda = new Venda(ID, cliente, livro, 10, 15.20);

        listaVenda.add(venda);

        optionalVenda = Optional.of(venda);

    }

}