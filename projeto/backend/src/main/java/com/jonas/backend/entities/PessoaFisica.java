package com.jonas.backend.entities;

import java.io.Serializable;
import java.util.Objects;
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
public class PessoaFisica extends Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "RG é obrigatorio!")
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}-[0-9Xx]",
            message = "Invalid RG format")
    private String rg;

    @NotEmpty(message = "CPF é obrigatorio!")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
            message = "Invalid CPF format")
    private String cpf;

    public PessoaFisica(Long id, String name, String rua, Integer numero, String bairro, String cidade, String estado, String cep, String rg, String cpf) {
        super(id, name, rua, numero, bairro, cidade, estado, cep);
        this.rg = rg;
        this.cpf = cpf;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.cpf);
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
        final PessoaFisica other = (PessoaFisica) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }

}
