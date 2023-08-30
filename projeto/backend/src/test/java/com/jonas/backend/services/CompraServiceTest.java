package com.jonas.backend.services;

import com.jonas.backend.entities.Compra;
import com.jonas.backend.entities.Livro;
import com.jonas.backend.repositories.CompraRepository;
import com.jonas.backend.services.exceptions.DatabaseException;
import com.jonas.backend.services.exceptions.ResourceNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
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
public class CompraServiceTest {

    private static final Long NON_EXISTENT_ID = 999L;
    private static final String EXPECTED_MESSAGE = "Resource not found. Id "
            + NON_EXISTENT_ID;
    private static final Integer INDEX = 0;
    private static final Long ID = 1L;
    private static final Integer QTD_ITENS = 30;
    private static final Double PRECO_VENDA = 20.20;

    @InjectMocks
    private CompraService compraService;

    @Mock
    private CompraRepository compraRepository;

    @Mock
    private LivroService livroService;

    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

    private Livro livro;
    private Compra compra;
    private Optional<Compra> optionalCompra;

    @BeforeEach
    void setUp() throws ParseException {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindAllThenReturnAListOfPurchases() {

        when(compraRepository.findAll())
                .thenReturn(List.of(compra));

        List<Compra> response = compraService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());

        Compra newCompra = response.get(INDEX);
        assertPurchase(newCompra);

        verify(compraRepository, times(1)).findAll();

    }

    @Test
    public void whenFindByIdThenReturnAnPurchase() {

        when(compraRepository.findById(anyLong()))
                .thenReturn(optionalCompra);

        Compra response = compraService.findById(ID);

        assertNotNull(response);

        assertEquals(Compra.class, response.getClass());

        assertPurchase(response);

        verify(compraRepository, times(1)).findById((ID));

    }

    @Test
    void whenFindByIdNonexistentThenReturnAnObjectNotFoundException() {

        when(compraRepository.findById(anyLong()))
                .thenThrow(new ResourceNotFoundException(NON_EXISTENT_ID));

        try {
            compraService.findById(NON_EXISTENT_ID);
        } catch (Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals(EXPECTED_MESSAGE, ex.getMessage());
        }

    }

    @Test
    void whenInsertAnPurchaseThenReturnSuccess() {

        when(compraRepository.save(any())).thenReturn(compra);

        Compra response = compraService.insert(compra);

        assertNotNull(response);

        assertPurchase(response);

        verify(compraRepository, times(1)).save(compra);

    }

    @Test
    void whenUpdateAnPurchaseThenReturnSuccess() {

        when(compraRepository.findById(anyLong()))
                .thenReturn(optionalCompra);
        when(compraRepository.save(any()))
                .thenReturn(compra);

        Compra response = compraService.update(ID, compra);

        assertNotNull(response);

        assertPurchase(response);

        verify(compraRepository, times(1)).save(compra);

    }

    @Test
    void whenUpdatingtAnPurchasetWithIdNonexistentThenReturnAnResourceNotFoundException() {

        when(compraRepository.findById(anyLong()))
                .thenReturn(optionalCompra);
        when(compraRepository.save(any(Compra.class)))
                .thenThrow(EntityNotFoundException.class);

        try {
            compraService.update(NON_EXISTENT_ID, compra);
        } catch (ResourceNotFoundException ex) {
            assertEquals(EXPECTED_MESSAGE, ex.getMessage());
        }

    }

    @Test
    void whenDeleteAnPurchaselWithSuccess() {

        when(compraRepository.findById(anyLong()))
                .thenReturn(optionalCompra);
        doNothing().when(compraRepository).deleteById(anyLong());

        compraService.delete(ID);

        verify(compraRepository, times(1)).deleteById(anyLong());

    }

    @Test
    void whenDeleteAnPurchaseWithNonexistentIdThenThrowResourceNotFoundException() {

        when(compraRepository.findById(anyLong()))
                .thenReturn(optionalCompra);
        doThrow(EmptyResultDataAccessException.class)
                .when(compraRepository).deleteById(NON_EXISTENT_ID);

        try {
            compraService.delete(NON_EXISTENT_ID);
        } catch (Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals(EXPECTED_MESSAGE, ex.getMessage());
        }

    }

    @Test
    void whenDeleteAnPurchaseWithExistingIdAndDataIntegrityViolationExceptionThenThrowDatabaseException() {

        when(compraRepository.findById(anyLong()))
                .thenReturn(optionalCompra);
        doThrow(DataIntegrityViolationException.class)
                .when(compraRepository).deleteById(ID);

        try {
            compraService.delete(ID);
        } catch (Exception ex) {
            assertEquals(DatabaseException.class, ex.getClass());
            assertEquals("Erro de violação de integridade nos dados", ex.getMessage());
        }

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

        compra = new Compra(ID,
                livro,
                QTD_ITENS,
                PRECO_VENDA);

        optionalCompra = Optional.of(compra);

    }

}
