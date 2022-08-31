package com.jonas.backend.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_vendas")
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id //Declarando chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;
    
    private Integer qtdItens;
    private Double precoVenda;

    
    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", client=" + client + ", qtdItens=" + qtdItens + ", precoVenda=" + precoVenda + '}';
    }
    

}
