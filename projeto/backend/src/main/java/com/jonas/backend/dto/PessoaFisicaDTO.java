package com.jonas.backend.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PessoaFisicaDTO extends ClienteDTO {

    private String cpf;
    private String rg;

    public PessoaFisicaDTO(Long id, String nome, String rua, Integer numero,
            String bairro, String cidade, String estado, String cep,
            String rg, String cpf, List<VendaDTO> vendas) {
        super(id, nome, rua, numero, bairro, cidade, estado, cep, vendas);
        this.cpf = cpf;
        this.rg = rg;
    }

}
