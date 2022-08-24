package com.jonas.backend.services;

import com.jonas.backend.entities.Venda;
import com.jonas.backend.repositories.ClientRepository;
import com.jonas.backend.repositories.LivroRepository;
import com.jonas.backend.repositories.VendaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendarRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LivroRepository livroRepository;

//    public Venda create(Venda venda) {
//        Venda newVenda = new Venda();
//        Client client = clientRepository.findById(venda.getId()).get();
//        Livro livro = livroRepository.findById(venda.getId()).get();
//
//        newVenda.setClient(client);
//        newVenda.setLivro(livro);
//        newVenda.setQtdItens(venda.getQtdItens());
//        newVenda.setPrecoVenda(venda.getPrecoVenda());
//
//        return vendarRepository.save(newVenda);
//    }
    
     public Venda insert(Venda obj) {
        return vendarRepository.save(obj);
    }
    

    public List<Venda> getAll() {

        return vendarRepository.findAll();
    }

    public Venda get(Long id) {
        return null;
    }

}
