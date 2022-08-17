package com.jonas.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_livro")
@Getter
@Setter
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //Declarando chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String editora;
    //private String link_imagem;
    private String linkImg;
    private Date anoPublicacao;
    private Integer estoque;

    @OneToMany
    private Set<Compra> compra = new LinkedHashSet<>();

    public Livro() {
    }

    public Livro(Long id, String titulo, String autor, String editora, String linkImg, Date anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.linkImg = linkImg;
        this.anoPublicacao = anoPublicacao;
    }

    public Livro(Long id, String titulo, String autor, String editora, String linkImg, Date anoPublicacao, Integer estoque) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.linkImg = linkImg;
        this.anoPublicacao = anoPublicacao;
        this.estoque = estoque;
    }

    public void comprar(Double preco, Integer quantidade) {
        int sum = 0;
        sum = estoque + quantidade;
        setEstoque(sum);
    }

    public void vender(Integer quantidade) {
        int sum = 0;
        if (estoque < quantidade) {
            System.out.println("Valor nao permitido");
        } else {
            sum = estoque - quantidade;
            setEstoque(sum);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Livro other = (Livro) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Livro: " + "id: " + id + ", titulo: " + titulo + ", autor: " + autor + ", editora: " + editora + ", img: " + linkImg + ", anoPublicacao: " + anoPublicacao + '}';
    }

}