package com.jonas.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
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

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date anoPublicacao;

    private Integer estoque;

}
