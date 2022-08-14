package com.jonas.backend.config;

import com.jonas.backend.entities.Client;
import com.jonas.backend.entities.Compra;
import com.jonas.backend.entities.Endereco;
import com.jonas.backend.entities.Livro;
import com.jonas.backend.entities.PessoaFisica;
import com.jonas.backend.entities.PessoaJuridica;
import com.jonas.backend.repositories.ClientRepository;
import com.jonas.backend.repositories.CompraRepository;
import com.jonas.backend.repositories.EnderecoRepository;
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
    private EnderecoRepository enderecoRepository;

    @Autowired
    private LivroRepository livrorepository;

    @Autowired
    VendaRepository vendaRepository;

    @Override
    public void run(String... args) throws Exception {
        Livro livro1 = new Livro(null, "Harry Potter e a Pedra Filosofal", "J. K. Rowling", "Presenca", formatador.parse("14/10/1999"));
        Livro livro2 = new Livro(null, "Don Quixote", "Miguel de Cervantes", "Livraria José Olympio Editora", formatador.parse("01/01/1952"));
        Livro livro3 = new Livro(null, "Um Conto de Duas Cidades", "Charles Dickens", "	Bradbury & Evans", formatador.parse("30/04/1859"));
        Livro livro4 = new Livro(null, "O Senhor dos Anéis", "J. R. R. Tolkien", "Allen & Unwin", formatador.parse("20/10/1955"));
        Livro livro5 = new Livro(null, "O Pequeno Príncipe", "	Antoine de Saint-Exupéry", "Editora Agir", formatador.parse("01/01/1956"));
        livrorepository.saveAll(Arrays.asList(livro1, livro2, livro3, livro4, livro5));

        Endereco endereco1 = new Endereco(null, "Rua São José", 658, "Centro", "São Paulo", "SP", "37890-000");
        Endereco endereco2 = new Endereco(null, "Rua São Pedro ", 73, "Jd Oliveira", "Belo Horizonte", "MG", "32890-780");
        enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));

        Client client1 = new PessoaFisica(null, "SP123456", "111879965-98", "Ana Green", endereco1);
        Client client2 = new PessoaFisica(null, "MG987547", "123555789-55", " John Brown", endereco2);
        Client client3 = new PessoaJuridica(null, "1555879966-00001", " JB Imports", endereco2);
        clientRepository.saveAll(Arrays.asList(client1, client2, client3));

    }

    /*
    
            Livro livro1 = new Livro(null, "Harry Potter e a Pedra Filosofal", "J. K. Rowling", "Presenca", formatador.parse("14/10/1999"));
        Livro livro2 = new Livro(null, "Don Quixote", "Miguel de Cervantes", "Livraria José Olympio Editora", formatador.parse("01/01/1952"));
        Livro livro3 = new Livro(null, "Um Conto de Duas Cidades", "Charles Dickens", "	Bradbury & Evans", formatador.parse("30/04/1859"));
        Livro livro4 = new Livro(null, "O Senhor dos Anéis", "J. R. R. Tolkien", "Allen & Unwin", formatador.parse("20/10/1955"));
        Livro livro5 = new Livro(null, "O Pequeno Príncipe", "	Antoine de Saint-Exupéry", "Editora Agir", formatador.parse("01/01/1956"));
    
        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);

        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

        userRepository.saveAll(Arrays.asList(u1, u2));//SaveAll: Salva uma lista de objetos no banco de dados
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
        
         Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
        o1.setPayment(pay1);
        orderRepository.save(o1);

    }*/
}
