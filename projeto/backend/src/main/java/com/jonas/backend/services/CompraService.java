package com.jonas.backend.services;


import com.jonas.backend.entities.Compra;
import com.jonas.backend.entities.Itens;
import com.jonas.backend.services.exceptions.DatabaseException;
import com.jonas.backend.services.exceptions.ResourceNotFoundException;
import com.jonas.backend.repositories.CompraRepository;
import com.jonas.backend.repositories.ItensRepository;
import java.util.List;
import java.util.Optional;
import static org.hibernate.criterion.Projections.id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    @Autowired
    private CompraRepository repository;
    
    @Autowired
    private ItensRepository itensRepository;

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
    
    public Itens insertItens(Itens obj) {
        return itensRepository.save(obj);
    }
    
    public void addCompraedProducts(Itens Itens) {
        itensRepository.save(Itens);
    }
    
   
}
