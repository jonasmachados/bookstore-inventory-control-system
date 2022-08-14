package com.jonas.backend.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_pessoaJuridica")
@Getter
@Setter
public class PessoaJuridica extends Client{
    
    private String cnpj;

    public PessoaJuridica(Long id, String cnpj, String name, Endereco endereco) {
        super(id, name, endereco);
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" + "cnpj=" + cnpj + '}';
    }

    
    
}
