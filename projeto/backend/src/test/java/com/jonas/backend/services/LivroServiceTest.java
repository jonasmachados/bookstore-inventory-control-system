package com.jonas.backend.services;

import com.jonas.backend.entities.Cliente;
import com.jonas.backend.entities.Compra;
import com.jonas.backend.entities.Livro;
import com.jonas.backend.entities.Venda;
import com.jonas.backend.repositories.LivroRepository;
import com.jonas.backend.services.exceptions.DatabaseException;
import com.jonas.backend.services.exceptions.DateParsingException;
import com.jonas.backend.services.exceptions.ResourceNotFoundException;
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
public class LivroServiceTest {

    private static final Long NON_EXISTENT_ID = 999L;
    private static final String EXPECTED_MESSAGE_ID_INCORRECT = "Resource not "
            + "found. Id " + NON_EXISTENT_ID;
    private static final String EXPECTED_MESSAGE_DATE_INCORRECT = "Failed to "
            + "parse the date: Invalid date format";
    private static final Integer INDEX = 0;
    private static final Long ID = 1L;
    private static final String TITULO = "Harry Potter e a Pedra Filosofal";
    private static final String AUTOR = "J. K. Rowling";
    private static final String EDITORA = "Presenca";
    private static final String LINK_IMG = "https://";
    private static final String ANO_PUBLICACAO = "20/10/1955";
    private static final Integer ESTOQUE = 30;

    @InjectMocks
    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    private Livro livro;
    private Optional<Livro> optionalLivro;
    private Compra compra;
    private Venda venda;
    private Cliente cliente;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        startUser();

    }

    @Test
    void whenFindAll_ThenReturnAListOfBooks() {

        when(livroRepository.findAll())
                .thenReturn(List.of(livro));

        List<Livro> response = livroService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());

        Livro novoLivro = response.get(INDEX);
        assertBook(novoLivro);

        verify(livroRepository, times(1)).findAll();

    }

    @Test
    public void whenFindById_ThenReturnAnPurchase() {

        when(livroRepository.findById(anyLong()))
                .thenReturn(optionalLivro);

        Livro response = livroService.findById(ID);

        assertNotNull(response);

        assertEquals(Livro.class, response.getClass());

        assertBook(response);

        verify(livroRepository, times(1)).findById((ID));

    }

    @Test
    void whenFindByIdNonexistent_ThenReturnAnObjectNotFoundException() {

        when(livroRepository.findById(anyLong()))
                .thenThrow(new ResourceNotFoundException(NON_EXISTENT_ID));

        try {
            livroService.findById(NON_EXISTENT_ID);
        } catch (Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals(EXPECTED_MESSAGE_ID_INCORRECT, ex.getMessage());
        }

    }

    @Test
    void whenInsertAnBook_ThenReturnSuccess() {

        when(livroRepository.save(any()))
                .thenReturn(livro);

        Livro response = livroService.insert(livro);

        assertNotNull(response);

        assertBook(response);

        verify(livroRepository, times(1)).save(livro);

    }

    @Test
    void whenInsertBookWithIncorrectDateFormat_ThenThrowsDateParsingException() {

        when(livroRepository.save(any(Livro.class)))
                .thenThrow(new DateParsingException(EXPECTED_MESSAGE_DATE_INCORRECT));

        livro.setAnoPublicacao("50/50/50");

        try {
            livroService.insert(livro);
        } catch (DateParsingException ex) {
            assertEquals(DateParsingException.class, ex.getClass());
            assertEquals(EXPECTED_MESSAGE_DATE_INCORRECT, ex.getMessage());
        }

        verify(livroRepository, times(1)).save(livro);

    }

    @Test
    void whenUpdateBook_ThenReturnSuccess() {

        when(livroRepository.findById(anyLong()))
                .thenReturn(optionalLivro);
        when(livroRepository.save(any()))
                .thenReturn(livro);

        Livro response = livroService.update(ID, livro);

        assertNotNull(response);

        assertBook(response);

        verify(livroRepository, times(1)).save(livro);

    }

    @Test
    void whenUpdatingBookWithIdNonexistent_ThenReturnResourceNotFoundException() {

        when(livroRepository.findById(anyLong()))
                .thenReturn(optionalLivro);
        when(livroRepository.save(any(Livro.class)))
                .thenThrow(EntityNotFoundException.class);

        try {
            livroService.update(NON_EXISTENT_ID, livro);
        } catch (ResourceNotFoundException ex) {
            assertEquals(EXPECTED_MESSAGE_ID_INCORRECT, ex.getMessage());
        }

    }

    @Test
    void whenUpdateBookWithIncorrectDateFormat_ThenThrowsDateParsingException() {

        when(livroRepository.findById(anyLong()))
                .thenReturn(optionalLivro);
        when(livroRepository.save(any(Livro.class)))
                .thenThrow(new DateParsingException(EXPECTED_MESSAGE_DATE_INCORRECT));

        livro.setAnoPublicacao("50/50/50");

        try {
            livroService.update(ID, livro);
        } catch (DateParsingException ex) {
            assertEquals(DateParsingException.class, ex.getClass());
            assertEquals(EXPECTED_MESSAGE_DATE_INCORRECT, ex.getMessage());
        }

        verify(livroRepository, times(1)).save(livro);

    }

    @Test
    void whenDeleteAnBook_ThenReturnSuccess() {

        when(livroRepository.findById(anyLong()))
                .thenReturn(optionalLivro);
        doNothing().when(livroRepository).deleteById(anyLong());

        livroService.delete(ID);

        verify(livroRepository, times(1)).deleteById(anyLong());

    }

    @Test
    void whenDeleteBookWithNonexistentId_ThenThrowResourceNotFoundException() {

        when(livroRepository.findById(anyLong()))
                .thenReturn(optionalLivro);
        doThrow(EmptyResultDataAccessException.class)
                .when(livroRepository).deleteById(NON_EXISTENT_ID);

        try {
            livroService.delete(NON_EXISTENT_ID);
        } catch (Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals(EXPECTED_MESSAGE_ID_INCORRECT, ex.getMessage());
        }

        verify(livroRepository, times(1)).deleteById(anyLong());

    }

    @Test
    void whenDeleteBook_ThenThrowDataIntegrityException() {

        when(livroRepository.findById(anyLong()))
                .thenReturn(optionalLivro);
        doThrow(DataIntegrityViolationException.class)
                .when(livroRepository).deleteById(ID);

        try {
            livroService.delete(ID);
        } catch (Exception ex) {
            assertEquals(DatabaseException.class, ex.getClass());
            assertEquals("Erro de violação de integridade nos dados",
                    ex.getMessage());
        }

        verify(livroRepository, times(1)).deleteById(anyLong());

    }

    @Test
    void whenAddNewPurchase_ThenIncreaseStockOfBook() {

        when(livroRepository.findById(anyLong()))
                .thenReturn(optionalLivro);
        when(livroRepository.save(any()))
                .thenReturn(livro);

        Livro result = livroService.addBook(ID, 20);

        assertEquals(50, result.getEstoque());

        verify(livroRepository, times(1)).findById(ID);
        verify(livroRepository, times(1)).save(livro);

    }

    @Test
    void whenDeleteAPurchase_ThenDecreaseStockOfBook() {

        when(livroRepository.findById(anyLong()))
                .thenReturn(optionalLivro);
        when(livroRepository.save(any()))
                .thenReturn(livro);

        Livro result = livroService.removeBook(ID, 20);

        assertEquals(10, result.getEstoque());

        verify(livroRepository, times(1)).findById(ID);
        verify(livroRepository, times(1)).save(livro);

    }

    @Test
    void whenUpdatePurchaseWithQuantityGreater_ThenIncreaseStock() {

        when(livroRepository.findById(anyLong()))
                .thenReturn(optionalLivro);
        when(livroRepository.save(any()))
                .thenReturn(livro);

        Livro result = livroService.updatePurchase(
                ID, 50, compra);

        assertEquals(50, result.getEstoque());

        verify(livroRepository, times(1)).findById(ID);
        verify(livroRepository, times(1)).save(livro);

    }

    @Test
    void whenUpdatePurchaseWithQuantitySmaller_ThenDecreaseStock() {

        when(livroRepository.findById(anyLong()))
                .thenReturn(optionalLivro);
        when(livroRepository.save(any()))
                .thenReturn(livro);

        Livro result = livroService.updatePurchase(
                ID, 20, compra);

        assertEquals(20, result.getEstoque());

        verify(livroRepository, times(1)).findById(ID);
        verify(livroRepository, times(1)).save(livro);

    }

    @Test
    void whenUpdatePurchaseWithSameQuantity_ThenQuantityRemainsSame() {

        when(livroRepository.findById(anyLong()))
                .thenReturn(optionalLivro);
        when(livroRepository.save(any()))
                .thenReturn(livro);

        Livro result = livroService.updatePurchase(
                ID, 30, compra);

        assertEquals(30, result.getEstoque());

        verify(livroRepository, times(1)).findById(ID);
        verify(livroRepository, times(1)).save(livro);

    }

    @Test
    void whenUpdateSaleWithQuantityGreater_ThenDecreaseStock() {

        when(livroRepository.findById(anyLong()))
                .thenReturn(optionalLivro);
        when(livroRepository.save(any()))
                .thenReturn(livro);

        Livro result = livroService.updateSale(ID, 20, venda);

        assertEquals(20, result.getEstoque());

        verify(livroRepository, times(1)).findById(ID);
        verify(livroRepository, times(1)).save(livro);

    }

    @Test
    void whenUpdateSaleWithQuantitySmaller_ThenIncreaseStock() {

        when(livroRepository.findById(anyLong()))
                .thenReturn(optionalLivro);
        when(livroRepository.save(any()))
                .thenReturn(livro);

        Livro result = livroService.updateSale(ID, 5, venda);

        assertEquals(35, result.getEstoque());

        verify(livroRepository, times(1)).findById(ID);
        verify(livroRepository, times(1)).save(livro);

    }

    @Test
    void whenUpdateSaleWithSameQuantity_ThenQuantityRemainsSame() {

        when(livroRepository.findById(anyLong()))
                .thenReturn(optionalLivro);
        when(livroRepository.save(any()))
                .thenReturn(livro);

        Livro result = livroService.updateSale(ID, 10, venda);

        assertEquals(30, result.getEstoque());

        verify(livroRepository, times(1)).findById(ID);
        verify(livroRepository, times(1)).save(livro);

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

        optionalLivro = Optional.of(livro);

        compra = new Compra(ID, livro, 30, 20.20);

        venda = new Venda(ID, cliente, livro, 10, 15.20);

    }

}