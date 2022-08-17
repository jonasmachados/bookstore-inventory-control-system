package com.jonas.backend.entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
//@DiscriminatorValue("pj")
@Table(name = "tb_pessoaJuridica")
@Getter
@Setter
public class PessoaJuridica extends Client{
    
    private String cnpj;

    public PessoaJuridica() {
    }

    public PessoaJuridica(Long id, String name, String rua, Integer numero, String bairro, String cidade, String estado, String cep, String cnpj) {
        super(id, name, rua, numero, bairro, cidade, estado, cep);
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" + "cnpj=" + cnpj + '}';
    }

   
}