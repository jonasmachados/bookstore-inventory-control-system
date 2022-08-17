package com.jonas.backend.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_venda")
@Getter 
@Setter
public class Venda {
    
    @Id //Declarando chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Client client;
    
    private Integer qtdItens;
    private Double precoVenda;

    public Venda(Long id, Client client, Integer qtdItens, Double precoVenda) {
        this.id = id;
        this.client = client;
        this.qtdItens = qtdItens;
        this.precoVenda = precoVenda;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", client=" + client + ", qtdItens=" + qtdItens + ", precoVenda=" + precoVenda + '}';
    }
    
    
    
    
    
    
    
}
