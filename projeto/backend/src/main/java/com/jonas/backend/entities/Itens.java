package com.jonas.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jonas.backend.entities.pk.ItemPK;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_item")
public class Itens implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private ItemPK id = new ItemPK();

    private Integer qtdExemplar;
    private Double preco;

    public Itens() {
    }

    public Itens(Compra compra, Livro livro, Integer qtdExemplar, Double preco) {
        id.setCompra(compra);
        id.setLivro(livro);
        this.qtdExemplar = qtdExemplar;
        this.preco = preco;
    }
        
    public Double getSubTotal(){
        return preco * qtdExemplar;
    }
    
    public void getEstoque(Livro livro){
        int sum = 0;
        sum = livro.getEstoque() + qtdExemplar;
        livro.setEstoque(sum); 
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Itens other = (Itens) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    @JsonIgnore
     public Compra getCompra() {
        return id.getCompra();
    }

    public void setCompra(Compra compra) {
        id.setCompra(compra);
    }

    public Livro getLivro() {
        return id.getLivro();
    }

    public void setLivro(Livro livro) {
        id.setLivro(livro);
    }

    public Integer getQtdExemplar() {
        return qtdExemplar;
    }

    public void setQtdExemplar(Integer qtdExemplar) {
        this.qtdExemplar = qtdExemplar;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    

}
