package com.jonas.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Table(name = "tb_livro")
@Getter
@Setter
@Entity
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //Declarando chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String titulo;

    @NotBlank
    @Size(min = 1, max = 100)
    private String autor;

    @NotBlank
    @Size(min = 1, max = 100)
    private String editora;

    private String linkImg;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date anoPublicacao;

    @NotNull
    private Integer estoque;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Compra> compras = new ArrayList<>();

    @OneToMany(mappedBy = "livro", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Venda> vendas = new ArrayList<>();

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

}
