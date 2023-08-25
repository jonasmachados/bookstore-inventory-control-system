package com.jonas.backend.request;

import com.jonas.backend.enums.ClienteType;
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

    public ClienteRequest(ClienteType clienteType, PessoaFisica pf) {
        this.clienteType = clienteType;
        this.pf = pf;
    }

    public ClienteRequest(ClienteType clienteType, PessoaJuridica pj) {
        this.clienteType = clienteType;
        this.pj = pj;
    }

}
