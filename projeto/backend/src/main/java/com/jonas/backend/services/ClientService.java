package com.jonas.backend.services;

import com.jonas.backend.entities.Client;
import com.jonas.backend.entities.PessoaFisica;
import com.jonas.backend.entities.PessoaJuridica;
import com.jonas.backend.services.exceptions.DatabaseException;
import com.jonas.backend.services.exceptions.ResourceNotFoundException;
import com.jonas.backend.repositories.ClientRepository;
import com.jonas.backend.repositories.PFRepository;
import com.jonas.backend.repositories.PJRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private PJRepository pjRepository;

    @Autowired
    private PFRepository pfRepository;

    public List<Client> findAll() {
        return repository.findAll();
    }
    
    public Client findById(Long id) {
        Optional<Client> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public PessoaJuridica insertPJ(PessoaJuridica obj) {
        return pjRepository.save(obj);
    }

    public PessoaFisica insertPF(PessoaFisica obj) {
        return pfRepository.save(obj);
    }

    //PJ
    public PessoaJuridica updatePJ(Long id, PessoaJuridica obj) {
        try {
            PessoaJuridica entity = pjRepository.getOne(id);
            updateDataPJ(entity, obj);
            return pjRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);

        }

    }

    private void updateDataPJ(PessoaJuridica entity, PessoaJuridica obj) {
        entity.setName(obj.getName());
        entity.setRua(obj.getRua());
        entity.setNumero(obj.getNumero());
        entity.setBairro(obj.getBairro());
        entity.setCidade(obj.getCidade());
        entity.setEstado(obj.getEstado());
        entity.setCep(obj.getCep());
        entity.setCnpj(obj.getCnpj());
    }

    //PF
    public PessoaFisica updatePF(Long id, PessoaFisica obj) {
        try {
            PessoaFisica entity = pfRepository.getOne(id);
            updateDataPF(entity, obj);
            return pfRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);

        }

    }

    private void updateDataPF(PessoaFisica entity, PessoaFisica obj) {
        entity.setName(obj.getName());
        entity.setRua(obj.getRua());
        entity.setNumero(obj.getNumero());
        entity.setBairro(obj.getBairro());
        entity.setCidade(obj.getCidade());
        entity.setEstado(obj.getEstado());
        entity.setCep(obj.getCep());
        entity.setRg(obj.getRg());
        entity.setCpf(obj.getCpf());
    }
    
     public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}