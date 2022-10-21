package com.jonas.backend.services;

import com.jonas.backend.entities.Venda;
import com.jonas.backend.repositories.ClientRepository;
import com.jonas.backend.repositories.LivroRepository;
import com.jonas.backend.repositories.VendaRepository;
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
public class VendaService {

    @Autowired
    private VendaRepository vendarRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LivroRepository livroRepository;

    public Venda insert(Venda obj) {
        return vendarRepository.save(obj);
    }

    public List<Venda> getAll() {

        return vendarRepository.findAll();
    }

    public Venda get(Long id) {
        return null;
    }

    public Venda getFindById(Long id) {
        Optional<Venda> obj = vendarRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Venda> findBookByClient(int idCliente) {
        return vendarRepository.findVendaByClient(idCliente);
    }

    public void delete(Long id) {
        try {
            vendarRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Venda update(Long id, Venda obj) {
        try {
            Venda entity = vendarRepository.getOne(id); //GetOne let a obj mapped for to JPA, dont go to DB
            updateData(entity, obj);
            return vendarRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);

        }

    }

    private void updateData(Venda entity, Venda obj) {
        entity.setClient(obj.getClient());
        entity.setLivro(obj.getLivro());
        entity.setQtdItens(obj.getQtdItens());
        entity.setPrecoVenda(obj.getPrecoVenda());
    }

}
