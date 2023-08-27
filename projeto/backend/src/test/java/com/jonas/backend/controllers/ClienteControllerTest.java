package com.jonas.backend.controllers;

import com.jonas.backend.dto.ClienteDTO;
import com.jonas.backend.dto.PessoaFisicaDTO;
import com.jonas.backend.dto.PessoaJuridicaDTO;
import com.jonas.backend.dto.VendaDTO;
import com.jonas.backend.entities.Cliente;
import com.jonas.backend.entities.PessoaFisica;
import com.jonas.backend.entities.PessoaJuridica;
import com.jonas.backend.enums.ClienteType;
import com.jonas.backend.request.ClienteRequest;
import com.jonas.backend.services.ClientService;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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
public class ClienteControllerTest {

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
    private ClienteController clienteController;

    @Mock
    private ClientService clienteService;

    @Mock
    private ModelMapper mapper;

    private final ModelMapper modelMapper = new ModelMapper();

    private Cliente clientePF;
    private Cliente clientePJ;
    private ClienteDTO clientePF_DTO;
    private ClienteDTO clientePJ_DTO;
    ClienteRequest clienteRequestPF;
    ClienteRequest clienteRequestPJ;

    List<VendaDTO> listaVendasDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    public void whenFindAllClientsThenReturnAListOfClientDTO() {

        when(clienteService.findAll())
                .thenReturn(List.of(clientePF, clientePJ));
        when(mapper.map(eq(clientePF), any()))
                .thenReturn(clientePF_DTO);
        when(mapper.map(eq(clientePJ), any()))
                .thenReturn(clientePJ_DTO);

        ResponseEntity<List<ClienteDTO>> response = clienteController.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());

        PessoaFisica pessoaFisica = modelMapper.map(response.getBody()
                .get(INDEX_PF), PessoaFisica.class);
        assertIndividual(pessoaFisica);

        PessoaJuridica pessoaJuridica = modelMapper.map(response.getBody()
                .get(INDEX_PJ), PessoaJuridica.class);
        assertCompany(pessoaJuridica);

    }

    @Test
    public void whenFindByIdPFThenReturnAnIndividual() {

        when(clienteService.findById(anyLong()))
                .thenReturn(clientePF);
        when(mapper.map(eq(clientePF), any()))
                .thenReturn(clientePF_DTO);

        ResponseEntity<ClienteDTO> response = clienteController.findById(ID_PF);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());

        PessoaFisica pessoaFisica = modelMapper.map(response.getBody(),
                PessoaFisica.class);
        assertIndividual(pessoaFisica);

    }

    @Test
    public void whenFindByIdPJThenReturnCompany() {

        when(clienteService.findById(anyLong()))
                .thenReturn(clientePJ);
        when(mapper.map(eq(clientePJ), any()))
                .thenReturn(clientePJ_DTO);

        ResponseEntity<ClienteDTO> response = clienteController.findById(ID_PJ);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());

        PessoaJuridica pessoaJuridica = modelMapper.map(response.getBody(),
                PessoaJuridica.class);
        assertCompany(pessoaJuridica);

    }

    @Test
    void whenInsertAnIndividualThenReturnCreated() {

        when(clienteService.insert(clienteRequestPF))
                .thenReturn(clientePF);
        when(mapper.map(eq(clientePF), any()))
                .thenReturn(clientePF_DTO);

        ResponseEntity<ClienteDTO> response = clienteController
                .insert(clienteRequestPF);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        URI expectedUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(1L).toUri();
        assertEquals(expectedUri, response.getHeaders().getLocation());

    }

    @Test
    void whenInsertAnCompanyThenReturnCreated() {

        when(clienteService.insert(clienteRequestPJ))
                .thenReturn(clientePJ);
        when(mapper.map(eq(clientePJ), any()))
                .thenReturn(clientePJ_DTO);

        ResponseEntity<ClienteDTO> response = clienteController
                .insert(clienteRequestPJ);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        URI expectedUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("2")
                .buildAndExpand(1L).toUri();
        assertEquals(expectedUri, response.getHeaders().getLocation());

    }

    @Test
    void whenUpdateAnIndividualThenReturnSuccess() {

        when(clienteService.update(ID_PF, clienteRequestPF))
                .thenReturn(clientePF);
        when(mapper.map(any(), any())).thenReturn(clientePF_DTO);

        ResponseEntity<ClienteDTO> response = clienteController
                .update(ID_PF, clienteRequestPF);

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

        PessoaFisica pessoaFisica = modelMapper.map(response.getBody(),
                PessoaFisica.class);
        assertIndividual(pessoaFisica);

    }

    @Test
    void whenUpdateAnCompanyThenReturnSuccess() {

        when(clienteService.update(ID_PJ, clienteRequestPJ))
                .thenReturn(clientePJ);
        when(mapper.map(any(), any())).thenReturn(clientePJ_DTO);

        ResponseEntity<ClienteDTO> response = clienteController
                .update(ID_PJ, clienteRequestPF);

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

        PessoaJuridica pessoaJuridica = modelMapper.map(response.getBody(),
                PessoaJuridica.class);
        assertCompany(pessoaJuridica);

    }

    @Test
    void whenDeleteAnIndividualWithSuccess() {

        doNothing().when(clienteService).delete(anyLong());

        ResponseEntity<ClienteDTO> response = clienteController.delete(ID_PF);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(clienteService, times(1)).delete(anyLong());

    }

    @Test
    void whenDeleteAnCompanyWithSuccess() {

        doNothing().when(clienteService).delete(anyLong());

        ResponseEntity<ClienteDTO> response = clienteController.delete(ID_PJ);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(clienteService, times(1)).delete(anyLong());

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

        clientePF_DTO = new PessoaFisicaDTO(
                ID_PF,
                NOME_PF,
                RUA,
                NUMERO,
                BAIRRO,
                CIDADE,
                ESTADO,
                CEP,
                RG_PF,
                CPF_PF,
                listaVendasDTO);

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

        clientePJ_DTO = new PessoaJuridicaDTO(
                ID_PJ,
                NOME_PJ,
                RUA,
                NUMERO,
                BAIRRO,
                CIDADE,
                ESTADO,
                CEP,
                CNPJ,
                listaVendasDTO);

        clienteRequestPJ = new ClienteRequest(
                ClienteType.PJ,
                (PessoaJuridica) clientePJ);

    }

}
