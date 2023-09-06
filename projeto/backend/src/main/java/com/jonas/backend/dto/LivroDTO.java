package com.jonas.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LivroDTO {

    private Long id;

    private String titulo;

    private String autor;

    private String editora;

    private String linkImg;

    private String anoPublicacao;

    private Integer estoque;

}
