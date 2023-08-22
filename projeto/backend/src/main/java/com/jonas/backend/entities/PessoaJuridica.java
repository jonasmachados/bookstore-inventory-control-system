package com.jonas.backend.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pessoaJuridica")
public class PessoaJuridica extends Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}",
            message = "Invalid CNPJ format")
    private String cnpj;

    public PessoaJuridica(Long id, String nome, String rua, Integer numero, 
            String bairro, String cidade, String estado, String cep, String cnpj) {
        super(id, nome, rua, numero, bairro, cidade, estado, cep);
        this.cnpj = cnpj;
    }

}
