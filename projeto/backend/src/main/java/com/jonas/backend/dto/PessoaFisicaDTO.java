package com.jonas.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFisicaDTO extends ClienteDTO {
    
    private String cpf;
    private String rg;

}


