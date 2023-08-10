package com.jonas.backend.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_compra")
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @NotNull(message = "Quantidade é obrigatorio!")
    @Positive(message = "Quantidade deve ser numero positivo!")
    private Integer qtdItens;

    @NotNull(message = "Preco venda é obrigatorio!")
    @DecimalMin(value = "0.1", message = "Prece venda deve ser maior ou igual a 0.1!")
    private Double precoVenda;

    public Double getTotal() {
        double sum = 0.0;
        sum = qtdItens * precoVenda;
        return sum;
    }

}
