package com.jonas.backend.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PessoaFisica extends Client implements Serializable {

    private static final long serialVersionUID = 1L;

    private String rg;
    private String cpf;

    
    public PessoaFisica(Long id, String name, String rua, Integer numero, String bairro, String cidade, String estado, String cep, String rg, String cpf) {
        super(id, name, rua, numero, bairro, cidade, estado, cep);
        this.rg = rg;
        this.cpf = cpf;
    }


    @Override
    public String toString() {
        return "PessoaFisica - " + "rg: " + rg + ", cpf: " + cpf + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.rg);
        hash = 83 * hash + Objects.hashCode(this.cpf);
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
        if (!Objects.equals(this.rg, other.rg)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }

}