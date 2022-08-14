package com.jonas.backend.entities;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_cliente")
@Getter 
@Setter
public abstract class Client {
    
    @Id //Declarando chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @OneToOne
    private Endereco endereco;
    
    @OneToMany
    private Set<Venda> vendas = new LinkedHashSet<>();

    public Client(Long id, String name, Endereco endereco) {
        this.id = id;
        this.name = name;
        this.endereco = endereco;
    }
    
    
    
       
    
}
