package com.jonas.backend.services;

import com.jonas.backend.entities.Compra;
import com.jonas.backend.repositories.CompraRepository;
import com.jonas.backend.services.exceptions.DatabaseException;
import com.jonas.backend.services.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    @Autowired
    private CompraRepository repository;

    public List<Compra> findAll() {
        return repository.findAll();
    }

    public Compra findById(Long id) {
        Optional<Compra> obj = repository.findById(id);
        return obj.get();
    }

    public Compra insert(Compra obj) {
        return repository.save(obj);
    }

    public Compra update(Long id, Compra obj) {
        try {
            Compra entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);

        }

    }

    private void updateData(Compra entity, Compra obj) {
        entity.setLivro(obj.getLivro());
        entity.setQtdItens(obj.getQtdItens());
        entity.setPrecoVenda(obj.getPrecoVenda());
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
