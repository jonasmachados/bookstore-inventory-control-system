package com.jonas.backend.controllers;

import com.jonas.backend.request.ClienteRequest;
import com.jonas.backend.dto.ClienteDTO;
import com.jonas.backend.dto.PessoaFisicaDTO;
import com.jonas.backend.dto.PessoaJuridicaDTO;
import com.jonas.backend.entities.Cliente;
import com.jonas.backend.entities.PessoaFisica;
import com.jonas.backend.entities.PessoaJuridica;
import com.jonas.backend.services.ClientService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClientService clienteService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> clientesDTO = clienteService.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(clientesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        ClienteDTO clienteDTO = convertToResponseDTO(clienteService.findById(id));

        return ResponseEntity.ok().body(clienteDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> insert(@RequestBody ClienteRequest clienteRequest) {
        ClienteDTO cliente = mapper.map(clienteService.insert(clienteRequest),
                ClienteDTO.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = clienteService.update(id, clienteRequest);
        ClienteDTO clienteDTO = mapper.map(cliente, ClienteDTO.class);
        return ResponseEntity.ok().body(clienteDTO);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ClienteDTO convertToResponseDTO(Cliente cliente) {
        if (cliente instanceof PessoaFisica) {
            return mapper.map(cliente, PessoaFisicaDTO.class);
        } else if (cliente instanceof PessoaJuridica) {
            return mapper.map(cliente, PessoaJuridicaDTO.class);
        } else {
            return mapper.map(cliente, ClienteDTO.class);
        }
    }

}
