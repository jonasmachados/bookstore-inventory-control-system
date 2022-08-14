package com.jonas.backend.entities;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_pessoaFisica")
@Getter
@Setter
public class PessoaFisica extends Client {

    private String rg;
    private String cpf;

    public PessoaFisica(Long id, String rg, String cpf, String name, Endereco endereco) {
        super(id, name, endereco);
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
