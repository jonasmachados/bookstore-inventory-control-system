package com.jonas.backend.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "tb_cliente")
@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty(message = "Nome é obrigatorio!")
    @Length(min = 3, max = 100, message = "Nome deve ter de 3 a 100 characters")
    private String nome;

    @NotEmpty(message = "Rua é obrigatorio!")
    @Length(min = 3, max = 50, message = "Rua deve ter de 3 a 50 characters")
    private String rua;

    @Positive(message = "Numero deve ser positivo")
    private Integer numero;

    @NotEmpty(message = "Bairro é obrigatorio!")
    @Length(min = 3, max = 50, message = "Bairro deve ter de 3 a 50 characters")
    private String bairro;

    @NotEmpty(message = "Cidade é obrigatorio!")
    @Length(min = 3, max = 20, message = "Cidade deve ter de 3 a 20 characters")
    private String cidade;

    @NotEmpty(message = "Estado é obrigatorio!")
    @Length(min = 2, max = 10, message = "estado deve ter de 3 a 10 characters")
    private String estado;

    @NotEmpty(message = "Cep é obrigatorio!")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Cep invalido. Use XXXXX-XXX.")
    private String cep;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Venda> vendas = new ArrayList<>();

    public Cliente(Long id, String nome, String rua, Integer numero, String bairro, String cidade, String estado, String cep) {
        this.id = id;
        this.nome = nome;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

}
