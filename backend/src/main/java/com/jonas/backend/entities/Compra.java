package com.jonas.backend.entities;


import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_compra")
@Getter 
@Setter
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id //Declarando chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer qtdExemplar;
    private Double valorUnitario;
    
    @OneToMany
    private Set<Livro> livros = new LinkedHashSet<>();

    public Compra
        () {
    }

    public Compra
        (Long id, Integer qtdExemplar, Double valorUnitario) {
        this.id = id;
        this.qtdExemplar = qtdExemplar;
        this.valorUnitario = valorUnitario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Compra
                other = (Compra
                ) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Compra -  " + "id: " + id + ", qtdExemplar: " + qtdExemplar + ", valorUnitario: " + valorUnitario + ", livros: " + livros + '}';
    }
    
    
    
    
}
