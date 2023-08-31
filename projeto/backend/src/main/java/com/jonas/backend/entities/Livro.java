package com.jonas.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tb_livro")
@NoArgsConstructor
@Data
@Entity
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Titulo é obrigatorio!")
    @Size(min = 1, max = 50, message = "Titulo deve ter de 3 a 50 characters")
    private String titulo;

    @NotEmpty(message = "Nome do autor é obrigatorio!")
    @Size(min = 1, max = 50, message = "Nome do autor deve ter de 3 a 50 characters")
    private String autor;

    @NotEmpty(message = "Editora é obrigatorio!")
    @Size(min = 1, max = 50, message = "Editora deve ter de 3 a 50 characters")
    private String editora;

    @NotEmpty(message = "Imagem é obrigatorio!")
    private String linkImg;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date anoPublicacao;

    private Integer estoque;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Compra> compras = new ArrayList<>();

    @OneToMany(mappedBy = "livro", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Venda> vendas = new ArrayList<>();

    public Livro(Long id, String titulo, String autor, String editora, String linkImg, Date anoPublicacao, Integer estoque) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.linkImg = linkImg;
        this.anoPublicacao = anoPublicacao;
        this.estoque = estoque;
    }

}
