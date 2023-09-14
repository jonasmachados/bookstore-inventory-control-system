package com.jonas.backend.controllers;

import com.jonas.backend.dto.VendaDTO;
import com.jonas.backend.entities.Venda;
import com.jonas.backend.services.ClientService;
import com.jonas.backend.services.VendaService;
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
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<VendaDTO>> findAll() {

        List<VendaDTO> vendaDTO = vendaService.findAll()
                .stream()
                .map(this::convertToVendaDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(vendaDTO);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VendaDTO> findById(@PathVariable Long id) {

        VendaDTO vendaDTO = convertToVendaDTO(vendaService.findById(id));

        return ResponseEntity.ok().body(vendaDTO);

    }

    @GetMapping(value = "/cliente/{idCliente}")
    public ResponseEntity<List<VendaDTO>> findSaleByClient(
            @PathVariable Integer idCliente) {

        List<VendaDTO> vendaDTO = vendaService.findSaleByClient(idCliente)
                .stream()
                .map(this::convertToVendaDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(vendaDTO);

    }

    @PostMapping
    public ResponseEntity<VendaDTO> insert(@RequestBody VendaDTO vendaDTO) {

        Venda venda = mapper.map(vendaDTO, Venda.class);
        venda.setCliente(clientService.findByNome(vendaDTO.getClienteNome()));
        venda = vendaService.insert(venda);

        VendaDTO savedvendaDTO = convertToVendaDTO(venda);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedvendaDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(savedvendaDTO);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VendaDTO> update(@PathVariable Long id,
            @RequestBody VendaDTO vendaDTO) {

        Venda venda = mapper.map(vendaDTO, Venda.class);
        venda.setCliente(clientService.findByNome(vendaDTO.getClienteNome()));

        venda = vendaService.update(id, venda);

        VendaDTO updatedVendaDTO = convertToVendaDTO(venda);

        return ResponseEntity.ok().body(updatedVendaDTO);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        vendaService.delete(id);

        return ResponseEntity.noContent().build();

    }

    private VendaDTO convertToVendaDTO(Venda venda) {

        return mapper.map(venda, VendaDTO.class);

    }

}
