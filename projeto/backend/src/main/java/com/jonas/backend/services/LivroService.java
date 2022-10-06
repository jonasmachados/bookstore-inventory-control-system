package com.jonas.backend.services;

import com.jonas.backend.entities.Livro;
import com.jonas.backend.entities.Compra;
import com.jonas.backend.entities.Venda;
import com.jonas.backend.repositories.CompraRepository;
import com.jonas.backend.services.exceptions.DatabaseException;
import com.jonas.backend.services.exceptions.ResourceNotFoundException;
import com.jonas.backend.repositories.LivroRepository;
import com.jonas.backend.repositories.VendaRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private CompraRepository compraRepository;
    
    @Autowired
    private VendaRepository vendaRepository;

    public List<Livro> findAll() {
        return repository.findAll();
    }

    public Livro findById(Long id) {
        Optional<Livro> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Livro insert(Livro obj) {
        return repository.save(obj);
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

    public Livro update(Long id, Livro obj) {
        try {
            Livro entity = repository.getOne(id); //GetOne let a obj mapped for to JPA, dont go to DB
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);

        }

    }

    private void updateData(Livro entity, Livro obj) {
        entity.setTitulo(obj.getTitulo());
        entity.setAutor(obj.getAutor());
        entity.setEditora(obj.getEditora());
        entity.setLinkImg(obj.getLinkImg());
        entity.setAnoPublicacao(obj.getAnoPublicacao());

    }

    private Livro verifyIfExists(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private Compra verifyIfCompraExists(Long id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
    
    private Venda verifyIfVendaExists(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Livro add(Long id, int quantityToAdd) {
        Livro livroToAdd = verifyIfExists(id);
        livroToAdd.setEstoque(livroToAdd.getEstoque() + quantityToAdd);
        return repository.save(livroToAdd);
    }

    public Livro remover(Long id, int quantityToRemove) {
        Livro livroToRemove = verifyIfExists(id);
        livroToRemove.setEstoque(livroToRemove.getEstoque() - quantityToRemove);
        return repository.save(livroToRemove);
    }

    public Livro atualizar(Long id, int quantity, Long idCompra) {
        Livro livroToAdd = verifyIfExists(id);
        Compra compra = verifyIfCompraExists(idCompra);
        Integer diferenca;

        if (quantity < compra.getQtdItens()) {
            diferenca = compra.getQtdItens() - quantity;
            livroToAdd.setEstoque(livroToAdd.getEstoque() - diferenca);
        } else if (quantity > compra.getQtdItens()) {
            diferenca = quantity - compra.getQtdItens();
            livroToAdd.setEstoque(livroToAdd.getEstoque() + diferenca);
        } else {
            livroToAdd.setEstoque(livroToAdd.getEstoque());
        }

        return repository.save(livroToAdd);
    }
    
    public Livro atualizarVenda(Long id, int quantity, Long idVenda) {
        Livro livroToAdd = verifyIfExists(id);
        Venda venda = verifyIfVendaExists(idVenda);
        Integer diferenca;

        if (quantity < venda.getQtdItens()) {
            diferenca = venda.getQtdItens() - quantity;
            livroToAdd.setEstoque(livroToAdd.getEstoque() + diferenca);
        } else if (quantity > venda.getQtdItens()) {
            diferenca = quantity - venda.getQtdItens();
            livroToAdd.setEstoque(livroToAdd.getEstoque() - diferenca);
        } else {
            livroToAdd.setEstoque(livroToAdd.getEstoque());
        }

        return repository.save(livroToAdd);
    } 


}
