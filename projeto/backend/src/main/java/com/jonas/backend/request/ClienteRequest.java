package com.jonas.backend.request;

import com.jonas.backend.entities.ClienteType;
import com.jonas.backend.entities.PessoaFisica;
import com.jonas.backend.entities.PessoaJuridica;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    private ClienteType clienteType;
    private PessoaJuridica pj;
    private PessoaFisica pf;

}
