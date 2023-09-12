package com.jonas.backend.services;

import com.jonas.backend.entities.Venda;
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
    private VendaRepository vendaRepository;

    @Autowired
    private LivroService livroService;

    public List<Venda> findAll() {

        return vendaRepository.findAll();

    }

    public Venda findById(Long id) {

        Optional<Venda> obj = vendaRepository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException(id));

    }
    
    public List<Venda> findBookByClient(int idCliente) {

        return vendaRepository.findVendaByClient(idCliente);

    }

    public Venda insert(Venda venda) {

        vendaRepository.save(venda);

        livroService.removeBook(venda.getLivro().getId(),
                venda.getQtdItens());

        return venda;

    }

    public Venda update(Long id, Venda obj) {

        try {

            Venda venda = findById(id);

            int updatedQuantity = obj.getQtdItens();

            livroService.updateSale(
                    venda.getLivro().getId(),
                    updatedQuantity,
                    venda);

            updateData(venda, obj);

            return vendaRepository.save(venda);

        } catch (EntityNotFoundException e) {

            throw new ResourceNotFoundException(id);

        }

    }

    private void updateData(Venda entity, Venda obj) {

        entity.setCliente(obj.getCliente());
        entity.setLivro(obj.getLivro());
        entity.setQtdItens(obj.getQtdItens());
        entity.setPrecoVenda(obj.getPrecoVenda());

    }

    public void delete(Long id) {

        try {

            Venda venda = findById(id);

            livroService.addBook(
                    venda.getLivro().getId(),
                    venda.getQtdItens());

            vendaRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

}
