package com.jonas.backend.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_compra")
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //Declarando chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @NotNull
    @Min(1)
    private Integer qtdItens;

    @NotNull
    @DecimalMin("0.1") 
    private Double precoVenda;

    public Double getTotal() {
        double sum = 0.0;
        sum = qtdItens * precoVenda;
        return sum;
    }

}
