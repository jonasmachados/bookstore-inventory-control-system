package com.jonas.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jonas.backend.entities.Cliente;
import com.jonas.backend.enums.ClienteType;
import com.jonas.backend.entities.PessoaFisica;
import com.jonas.backend.entities.PessoaJuridica;
import com.jonas.backend.entities.Venda;
import com.jonas.backend.repositories.ClientRepository;
import com.jonas.backend.repositories.PFRepository;
import com.jonas.backend.repositories.PJRepository;
import com.jonas.backend.request.ClienteRequest;
import com.jonas.backend.services.exceptions.DatabaseException;
import com.jonas.backend.services.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest
public class ClientServiceTest {

    private static final Long NON_EXISTENT_ID = 999L;
    private static final String EXPECTED_MESSAGE = "Resource not found. Id "
            + NON_EXISTENT_ID;

    private static final String RUA = "Rua São José";
    private static final Integer NUMERO = 658;
    private static final String BAIRRO = "Centro";
    private static final String CIDADE = "São Paulo";
    private static final String ESTADO = "SP";
    private static final String CEP = "37890-000";

    private static final Integer INDEX_PF = 0;
    private static final Long ID_PF = 1L;
    private static final String NOME_PF = "John Brown";
    private static final String RG_PF = "12.345.678-9";
    private static final String CPF_PF = "987.654.321-00";

    private static final Integer INDEX_PJ = 1;
    private static final Long ID_PJ = 2L;
    private static final String NOME_PJ = "JB Imports";
    private static final String CNPJ = "12.345.678/0001-90";

    @InjectMocks
    private ClientService clienteService;

    @Mock
    private ClientRepository clienteRepository;

    @Mock
    private PJRepository pjRepository;

    @Mock
    private PFRepository pfRepository;

    private final ModelMapper mapper = new ModelMapper();

    private Cliente clientePF;
    private Cliente clientePJ;
    private Optional<Cliente> optionalClientePF;
    private Optional<Cliente> optionalClientePJ;
    ClienteRequest clienteRequestPF;
    ClienteRequest clienteRequestPJ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindAllThenReturnAListOfClients() {

        when(clienteRepository.findAll(any(Sort.class)))
                .thenReturn(List.of(clientePF, clientePJ));

        List<Cliente> response = clienteService.findAll();

        assertNotNull(response);
        assertEquals(2, response.size());

        PessoaFisica pessoaFisica = (PessoaFisica) response.get(INDEX_PF);
        assertIndividual(pessoaFisica);

        PessoaJuridica pessoaJuridica = (PessoaJuridica) response.get(INDEX_PJ);
        assertCompany(pessoaJuridica);

    }

    @Test
    public void whenFindByIdPFThenReturnAnIndividual() {

        when(clienteRepository.findById(anyLong()))
                .thenReturn(optionalClientePF);

        Cliente response = clienteService.findById(ID_PF);

        assertNotNull(response);

        assertEquals(PessoaFisica.class, response.getClass());

        assertIndividual((PessoaFisica) response);

    }

    @Test
    public void whenFindByIdPJThenReturnCompany() {

        when(clienteRepository.findById(anyLong()))
                .thenReturn(optionalClientePJ);

        Cliente response = clienteService.findById(ID_PJ);

        assertNotNull(response);

        assertEquals(PessoaJuridica.class, response.getClass());

        assertCompany((PessoaJuridica) response);

    }

    @Test
    void whenFindByIdNonexistentThenReturnAnObjectNotFoundException() {

        when(clienteRepository.findById(anyLong()))
                .thenThrow(new ResourceNotFoundException(NON_EXISTENT_ID));

        try {
            clienteService.findById(NON_EXISTENT_ID);
        } catch (Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals(EXPECTED_MESSAGE, ex.getMessage());
        }

    }

    @Test
    void whenInsertAnIndividualThenReturnSuccess() {

        when(pfRepository.save(any())).thenReturn(clientePF);

        Cliente response = clienteService.insert(clienteRequestPF);

        assertNotNull(response);

        assertIndividual((PessoaFisica) response);

        assertTrue(response instanceof PessoaFisica);

        verify(pfRepository, times(1)).save((PessoaFisica) clientePF);

    }

    @Test
    void whenInsertAnCompanyThenReturnSuccess() {

        when(pjRepository.save(any())).thenReturn(clientePJ);

        Cliente response = clienteService.insert(clienteRequestPJ);

        assertNotNull(response);

        assertCompany((PessoaJuridica) response);

        assertTrue(response instanceof PessoaJuridica);

        verify(pjRepository, times(1)).save((PessoaJuridica) clientePJ);

    }

    @Test
    void whenUpdateAnIndividualThenReturnSuccess() {

        when(clienteRepository.findById(anyLong()))
                .thenReturn(optionalClientePF);
        when(pfRepository.save(any()))
                .thenReturn(clientePF);

        Cliente response = clienteService.update(ID_PF, clienteRequestPF);

        assertNotNull(response);

        assertIndividual((PessoaFisica) response);

        assertTrue(response instanceof PessoaFisica);

        verify(pfRepository, times(1)).save((PessoaFisica) clientePF);

    }

    @Test
    void whenUpdateAnCompanyThenReturnSuccess() {

        when(clienteRepository.findById(anyLong()))
                .thenReturn(optionalClientePJ);
        when(pjRepository.save(any()))
                .thenReturn(clientePJ);

        Cliente response = clienteService.update(ID_PJ, clienteRequestPJ);

        assertNotNull(response);

        assertCompany((PessoaJuridica) response);

        assertTrue(response instanceof PessoaJuridica);

        verify(pjRepository, times(1)).save((PessoaJuridica) clientePJ);

    }

    @Test
    void whenUpdatingtAnClientWithIdNonexistentThenReturnAnResourceNotFoundException() {

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            clienteService.update(NON_EXISTENT_ID, clienteRequestPF);
        } catch (Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals(EXPECTED_MESSAGE, ex.getMessage());
        }

    }

    @Test
    void whenUpdatingIndividualWithCompanyIDThenThrowIllegalArgumentException() {

        when(clienteRepository.findById(anyLong())).thenReturn(optionalClientePF);
        when(pfRepository.save(any())).thenThrow(ClassCastException.class);

        try {
            clienteService.update(ID_PJ, clienteRequestPF);
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertEquals("Invalid operation: Cannot cast Cliente to PessoaFisica",
                    ex.getMessage());
        }
    }

    @Test
    void whenUpdatingAnClientWithInvalidClienteTypeThrowIllegalArgumentException() throws JsonProcessingException {

        String json = "{\"clienteType\": \"INVALID_TYPE\","
                + " \"pf\": { \"cpf\": \"123.456.789-01\" }}";

        when(clienteRepository.findById(anyLong()))
                .thenReturn(optionalClientePF);
        when(pfRepository.save(any()))
                .thenThrow(IllegalArgumentException.class);

        try {
            clienteService.update(ID_PF, mapper.map(json, ClienteRequest.class));
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertEquals("Invalid ClienteType", ex.getMessage());
        }

    }

    @Test
    void whenDeleteAnIndividualWithSuccess() {

        when(clienteRepository.findById(anyLong()))
                .thenReturn(optionalClientePF);
        doNothing().when(pfRepository).deleteById(anyLong());

        clienteService.delete(ID_PF);

        verify(clienteRepository, times(1)).deleteById(anyLong());

    }

    @Test
    void whenDeleteAnCompanyWithSuccess() {

        when(clienteRepository.findById(anyLong()))
                .thenReturn(optionalClientePJ);
        doNothing().when(pjRepository).deleteById(anyLong());

        clienteService.delete(ID_PJ);

        verify(clienteRepository, times(1)).deleteById(anyLong());

    }

    @Test
    void whenDeleteAnIndividualWithSalesThrowsIllegalArgumentException() {

        clientePF.getVendas().add(new Venda());

        when(clienteRepository.findById(anyLong()))
                .thenReturn(Optional.of(clientePF));
        doThrow(IllegalStateException.class
        )
                .when(clienteRepository).deleteById(ID_PF);

        try {
            clienteService.delete(ID_PF);
        } catch (IllegalArgumentException ex) {
            assertEquals("Cannot delete a client with associated sales",
                    ex.getMessage());
        }

    }

    @Test
    void whenDeleteAnCompanyWithSalesThrowsIllegalArgumentException() {

        clientePJ.getVendas().add(new Venda());

        when(clienteRepository.findById(anyLong()))
                .thenReturn(optionalClientePJ);
        doThrow(IllegalStateException.class)
                .when(clienteRepository).deleteById(ID_PJ);

        try {
            clienteService.delete(ID_PJ);
        } catch (IllegalArgumentException ex) {
            assertEquals("Cannot delete a client with associated sales",
                    ex.getMessage());
        }

    }

    @Test
    void whenDeleteAnClientWithNonexistentIdThenThrowResourceNotFoundException() {

        when(clienteRepository.findById(anyLong()))
                .thenThrow(EmptyResultDataAccessException.class);

        try {
            clienteService.delete(NON_EXISTENT_ID);
        } catch (Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals(EXPECTED_MESSAGE, ex.getMessage());
        }

    }

    @Test
    void whenDeleteClientWithIntegrityViolation() {

        when(clienteRepository.findById(anyLong()))
                .thenReturn(optionalClientePF);

        doThrow(DataIntegrityViolationException.class)
                .when(clienteRepository).deleteById(anyLong());

        try {
            clienteService.delete(ID_PF);
        } catch (Exception ex) {
            assertEquals(DatabaseException.class, ex.getClass());
            assertEquals("Data integrity violation error", ex.getMessage());

        }
    }

    private void assertIndividual(PessoaFisica pessoaFisica) {
        assertEquals(ID_PF, pessoaFisica.getId());
        assertEquals(NOME_PF, pessoaFisica.getNome());
        assertEquals(RUA, pessoaFisica.getRua());
        assertEquals(NUMERO, pessoaFisica.getNumero());
        assertEquals(BAIRRO, pessoaFisica.getBairro());
        assertEquals(CIDADE, pessoaFisica.getCidade());
        assertEquals(ESTADO, pessoaFisica.getEstado());
        assertEquals(CEP, pessoaFisica.getCep());
        assertEquals(RG_PF, pessoaFisica.getRg());
        assertEquals(CPF_PF, pessoaFisica.getCpf());
    }

    private void assertCompany(PessoaJuridica pessoaJuridica) {
        assertEquals(ID_PJ, pessoaJuridica.getId());
        assertEquals(NOME_PJ, pessoaJuridica.getNome());
        assertEquals(RUA, pessoaJuridica.getRua());
        assertEquals(NUMERO, pessoaJuridica.getNumero());
        assertEquals(BAIRRO, pessoaJuridica.getBairro());
        assertEquals(CIDADE, pessoaJuridica.getCidade());
        assertEquals(ESTADO, pessoaJuridica.getEstado());
        assertEquals(CEP, pessoaJuridica.getCep());
        assertEquals(CNPJ, pessoaJuridica.getCnpj());
    }

    private void startUser() {
        clientePF = new PessoaFisica(
                ID_PF,
                NOME_PF,
                RUA,
                NUMERO,
                BAIRRO,
                CIDADE,
                ESTADO,
                CEP,
                RG_PF,
                CPF_PF);

        optionalClientePF = Optional.of(clientePF);

        clienteRequestPF = new ClienteRequest(
                ClienteType.PF,
                (PessoaFisica) clientePF);

        clientePJ = new PessoaJuridica(
                ID_PJ,
                NOME_PJ,
                RUA,
                NUMERO,
                BAIRRO,
                CIDADE,
                ESTADO,
                CEP,
                CNPJ);

        optionalClientePJ = Optional.of(clientePJ);

        clienteRequestPJ = new ClienteRequest(
                ClienteType.PJ,
                (PessoaJuridica) clientePJ);

    }

}
