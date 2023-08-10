package com.jonas.backend.config;

import com.jonas.backend.entities.Client;
import com.jonas.backend.entities.Compra;
import com.jonas.backend.entities.Livro;
import com.jonas.backend.entities.PessoaFisica;
import com.jonas.backend.entities.PessoaJuridica;
import com.jonas.backend.entities.Venda;
import com.jonas.backend.repositories.ClientRepository;
import com.jonas.backend.repositories.CompraRepository;
import com.jonas.backend.repositories.LivroRepository;
import com.jonas.backend.repositories.VendaRepository;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class DatabaseSeeder implements CommandLineRunner {

    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private LivroRepository livrorepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Override
    public void run(String... args) throws Exception {
        Livro livro1 = new Livro(null,
                "Harry Potter e a Pedra Filosofal",
                "J. K. Rowling", "Presenca",
                "https://ingresso-a.akamaihd.net/img/cinema/cartaz/7766-cartaz.jpg",
                formatador.parse("14/10/1999"),
                30);
        Livro livro2 = new Livro(null,
                "Don Quixote",
                "Miguel de Cervantes",
                "Livraria José Olympio Editora",
                "https://images-na.ssl-images-amazon.com/images/I/41JKGW9P6AL.jpg",
                formatador.parse("01/01/1952"),
                10);
        Livro livro3 = new Livro(null,
                "Um Conto de Duas Cidades",
                "Charles Dickens",
                "Bradbury & Evans",
                "https://images-na.ssl-images-amazon.com/images/I/61fD+SMxHeL.jpg",
                formatador.parse("30/04/1859"),
                10);
        Livro livro4 = new Livro(null,
                "O Senhor dos Anéis",
                "J. R. R. Tolkien",
                "Allen & Unwin",
                "https://images-na.ssl-images-amazon.com/images/I/71Xl6pR0k9L.jpg",
                formatador.parse("20/10/1955"),
                45);
        Livro livro5 = new Livro(null,
                "O Pequeno Príncipe",
                "Antoine de Saint-Exupéry",
                "Editora Agir",
                "https://a-static.mlcdn.com.br/1500x1500/livro-o-pequeno-principe/magazineluiza/228782900/159d6918b41a93af36fe6727aab9b701.jpg",
                formatador.parse("01/01/1956"),
                50);

        livrorepository.saveAll(Arrays.asList(livro1, livro2, livro3, livro4, livro5));

        Client client1 = new PessoaFisica(null,
                "Ana Green", "Rua São José",
                658,
                "Centro",
                "São Paulo",
                "SP",
                "37890-000",
                "12.345.678-9",
                "123.456.789-09");
        Client client2 = new PessoaFisica(null,
                " John Brown",
                "Rua São José",
                658,
                "Centro",
                "São Paulo",
                "SP",
                "37890-000",
                "12.345.678-9",
                "987.654.321-00");
        Client client3 = new PessoaJuridica(null,
                " JB Imports",
                "Rua São José",
                658,
                "Centro",
                "São Paulo",
                "SP",
                "37890-000",
                "12.345.678/0001-90");
        clientRepository.saveAll(Arrays.asList(client1, client2, client3));

        Compra c1 = new Compra(null, livro1, 30, 20.20);
        Compra c2 = new Compra(null, livro2, 10, 15.20);
        Compra c3 = new Compra(null, livro3, 10, 18.20);
        Compra c4 = new Compra(null, livro4, 45, 15.20);
        Compra c5 = new Compra(null, livro5, 25, 16.20);
        Compra c6 = new Compra(null, livro5, 25, 16.20);
        compraRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));

        Venda venda1 = new Venda(null, client1, livro1, 10, 15.20);
        Venda venda2 = new Venda(null, client1, livro2, 20, 15.20);
        Venda venda3 = new Venda(null, client2, livro2, 30, 18.00);
        vendaRepository.saveAll(Arrays.asList(venda1, venda2, venda3));

    }

}
