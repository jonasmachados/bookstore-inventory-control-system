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
public class PessoaJuridicaDTO extends ClienteDTO {

    private String cnpj;

    public PessoaJuridicaDTO(Long id, String nome, String rua, Integer numero,
            String bairro, String cidade, String estado, String cep,
            String cnpj, List<VendaDTO> vendas) {
        super(id, nome, rua, numero, bairro, cidade, estado, cep, vendas);
        this.cnpj = cnpj;
    }

}
