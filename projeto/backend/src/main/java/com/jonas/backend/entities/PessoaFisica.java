package com.jonas.backend.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PessoaFisica extends Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "RG é obrigatorio!")
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}-[0-9Xx]",
            message = "Invalid RG format")
    private String rg;

    @NotEmpty(message = "CPF é obrigatorio!")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
            message = "Invalid CPF format")
    private String cpf;

    public PessoaFisica(Long id, String nome, String rua, Integer numero,
            String bairro, String cidade, String estado, String cep, String rg, String cpf) {
        super(id, nome, rua, numero, bairro, cidade, estado, cep);
        this.rg = rg;
        this.cpf = cpf;
    }

}
