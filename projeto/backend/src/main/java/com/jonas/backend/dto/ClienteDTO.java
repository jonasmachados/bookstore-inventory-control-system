package com.jonas.backend.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;

    private String nome;

    private String rua;

    private Integer numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;

    private List<VendaDTO> vendas = new ArrayList<>();
    
}
