package com.jonas.backend.config;

import com.jonas.backend.entities.Client;
import com.jonas.backend.entities.Compra;
import com.jonas.backend.entities.Itens;
import com.jonas.backend.entities.Livro;
import com.jonas.backend.entities.PessoaFisica;
import com.jonas.backend.entities.PessoaJuridica;
import com.jonas.backend.repositories.ClientRepository;
import com.jonas.backend.repositories.CompraRepository;
import com.jonas.backend.repositories.ItensRepository;
import com.jonas.backend.repositories.LivroRepository;
import com.jonas.backend.repositories.VendaRepository;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class TestConfig implements CommandLineRunner {

    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private LivroRepository livrorepository;

    @Autowired
    private ItensRepository itensRepository;
    
    @Override
    public void run(String... args) throws Exception {
        Livro livro1 = new Livro(null, "Harry Potter e a Pedra Filosofal", "J. K. Rowling", "Presenca",  "link", formatador.parse("14/10/1999"), 0);
        Livro livro2 = new Livro(null, "Don Quixote", "Miguel de Cervantes", "Livraria José Olympio Editora",  "link",formatador.parse("01/01/1952"), 0);
        Livro livro3 = new Livro(null, "Um Conto de Duas Cidades", "Charles Dickens", "	Bradbury & Evans",  "link",formatador.parse("30/04/1859"), 0);
        Livro livro4 = new Livro(null, "O Senhor dos Anéis", "J. R. R. Tolkien", "Allen & Unwin",  "link", formatador.parse("20/10/1955"), 0);
        Livro livro5 = new Livro(null, "O Pequeno Príncipe", "	Antoine de Saint-Exupéry", "Editora Agir", "link",formatador.parse("01/01/1956"), 0);
      
        livrorepository.saveAll(Arrays.asList(livro1, livro2, livro3, livro4, livro5));

        Client client1 = new PessoaFisica(null, "Ana Green", "Rua São José", 658, "Centro", "São Paulo", "SP", "37890-000", "SP123456", "111879965-98");
        Client client2 = new PessoaFisica(null, " John Brown", "Rua São José", 658, "Centro", "São Paulo", "SP", "37890-000", "MG987547", "123555789-55");
        Client client3 = new PessoaJuridica(null, " JB Imports", "Rua São José", 658, "Centro", "São Paulo", "SP", "37890-000", "1555879966-00001");
        clientRepository.saveAll(Arrays.asList(client1, client2, client3));
        
        Compra c1 = new Compra(null);
        Compra c2 = new Compra(null);
        compraRepository.saveAll(Arrays.asList(c1, c2));
        
        Itens itens1 = new Itens(c1, livro1, 15, 15.9);
        Itens itens2 = new Itens(c1, livro2, 25, 16.90);
        Itens itens3 = new Itens(c2, livro3, 30, 20.30);
        itensRepository.saveAll(Arrays.asList(itens1, itens2, itens3));
          
//        livro1.comprar(10.1, 20);
        livrorepository.save(livro1);
//        
//        livro1.vender(2);
//        livrorepository.save(livro1);
    }

}
