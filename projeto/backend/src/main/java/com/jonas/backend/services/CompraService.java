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

    @Autowired
    private LivroService livroService;

    public List<Compra> findAll() {
        return repository.findAll();
    }

    public Compra findById(Long id) {
        Optional<Compra> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Compra insert(Compra compra) {
        repository.save(compra);

        livroService.addBook(compra.getLivro().getId(),
                compra.getQtdItens());

        return compra;

    }

    public Compra update(Long id, Compra obj) {
        try {
            Compra compra = findById(id);

            int updatedQuantity = obj.getQtdItens();

            livroService.updatePurchase(compra.getLivro().getId(),
                    updatedQuantity,
                    compra);

            updateData(compra, obj);

            return repository.save(compra);
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

            Compra compra = findById(id);

            livroService.removeBook(compra.getLivro().getId(),
                    compra.getQtdItens());

            repository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro de violação de integridade nos dados");
        }
    }

}
