package com.jonas.backend.entities.services;

import com.jonas.backend.entities.Endereco;
import com.jonas.backend.entities.services.exceptions.DatabaseException;
import com.jonas.backend.entities.services.exceptions.ResourceNotFoundException;
import com.jonas.backend.repositories.EnderecoRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }
    
    public Endereco findById(Long id) {
        Optional<Endereco> obj = enderecoRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    
    public Endereco insert(Endereco obj) {
        return enderecoRepository.save(obj);
    }
    
    public Endereco update(long id, Endereco obj) {
        try {
            Endereco entity = enderecoRepository.getOne(id);
            updateData(entity, obj);
            return enderecoRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Endereco entity, Endereco obj) {
        entity.setRua(obj.getRua());
        entity.setNumero(obj.getNumero());
        entity.setBairro(obj.getBairro());      
        entity.setCidade(obj.getCidade());
        entity.setEstado(obj.getEstado());
        entity.setCep(obj.getCep());
        
    }
    
    public void delete(Long id) {
        try {
            enderecoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
