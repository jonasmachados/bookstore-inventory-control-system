package com.jonas.backend.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

//@DiscriminatorValue("pj")
@Table(name = "tb_pessoaJuridica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PessoaJuridica extends Client implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cnpj;

    public PessoaJuridica(Long id, String name, String rua, Integer numero, String bairro, String cidade, String estado, String cep, String cnpj) {
        super(id, name, rua, numero, bairro, cidade, estado, cep);
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" + "cnpj=" + cnpj + '}';
    }

}
