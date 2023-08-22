package com.jonas.backend.services;

import com.jonas.backend.entities.Cliente;
import com.jonas.backend.entities.ClienteType;
import com.jonas.backend.entities.PessoaFisica;
import com.jonas.backend.entities.PessoaJuridica;
import com.jonas.backend.services.exceptions.ResourceNotFoundException;
import com.jonas.backend.repositories.ClientRepository;
import com.jonas.backend.repositories.PFRepository;
import com.jonas.backend.repositories.PJRepository;
import com.jonas.backend.request.ClienteRequest;
import com.jonas.backend.services.exceptions.DatabaseException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clienteRepository;

    @Autowired
    private PJRepository pjRepository;

    @Autowired
    private PFRepository pfRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Cliente findById(Long id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Cliente insert(ClienteRequest obj) {
        validateClienteType(obj.getClienteType());

        Cliente cliente;
        if (obj.getClienteType() == ClienteType.PJ) {
            return pjRepository.save(obj.getPj());
        } else {
            return pfRepository.save(obj.getPf());
        }

    }

    public Cliente update(Long id, ClienteRequest clienteRequest) {
        validateClienteType(clienteRequest.getClienteType());

        Cliente updatedCliente;
        if (clienteRequest.getClienteType() == ClienteType.PF) {
            updatedCliente = updateDataForPF(id, clienteRequest.getPf());

        } else if (clienteRequest.getClienteType() == ClienteType.PJ) {
            updatedCliente = updateDataForPJ(id, clienteRequest.getPj());
        } else {
            throw new IllegalArgumentException("Invalid ClienteType");
        }

        return updatedCliente;
    }

    private PessoaFisica updateDataForPF(Long id, PessoaFisica obj) {
        Cliente cliente = findById(id);

        try {
            PessoaFisica pf = (PessoaFisica) cliente;

            pf.setNome(obj.getNome());
            pf.setRua(obj.getRua());
            pf.setNumero(obj.getNumero());
            pf.setBairro(obj.getBairro());
            pf.setCidade(obj.getCidade());
            pf.setEstado(obj.getEstado());
            pf.setCep(obj.getCep());
            pf.setRg(obj.getRg());
            pf.setCpf(obj.getCpf());

            return pfRepository.save(pf);
        } catch (IllegalArgumentException e) {
            throw new ClassCastException("Invalid operation: Cannot cast Cliente to PessoaFisica");
        }
    }

    private PessoaJuridica updateDataForPJ(Long id, PessoaJuridica obj) {

        Cliente cliente = findById(id);

        try {
            PessoaJuridica pj = (PessoaJuridica) cliente;

            pj.setNome(obj.getNome());
            pj.setRua(obj.getRua());
            pj.setNumero(obj.getNumero());
            pj.setBairro(obj.getBairro());
            pj.setCidade(obj.getCidade());
            pj.setEstado(obj.getEstado());
            pj.setCep(obj.getCep());
            pj.setCnpj(obj.getCnpj());

            return pjRepository.save(pj);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Invalid operation: Cannot cast Cliente to PessoaFisica");
        }
    }

    private void validateClienteType(ClienteType clienteType) {
        if (clienteType == null || (clienteType != ClienteType.PJ && clienteType != ClienteType.PF)) {
            throw new IllegalArgumentException("Invalid ClienteType");
        }
    }

    public void delete(Long id) {
        try {
            if (!findById(id).getVendas().isEmpty()) {
                throw new IllegalStateException("Cannot delete a client with associated sales");
            }

            clienteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Data integrity violation error");
        }
    }

}
