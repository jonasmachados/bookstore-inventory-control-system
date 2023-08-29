package com.jonas.backend.controllers;

import com.jonas.backend.dto.CompraDTO;
import com.jonas.backend.entities.Compra;
import com.jonas.backend.services.CompraService;
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
@RequestMapping(value = "/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CompraDTO>> findAll() {
        List<CompraDTO> clientesDTO = compraService.findAll()
                .stream()
                .map(this::convertToCompraDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(clientesDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompraDTO> findById(@PathVariable Long id) {
        CompraDTO compraDTO = convertToCompraDTO(compraService.findById(id));

        return ResponseEntity.ok().body(compraDTO);
    }

    @PostMapping
    public ResponseEntity<CompraDTO> insert(@RequestBody Compra compra) {
        Compra compraToInsert = compraService.insert(compra);
        CompraDTO compraDTO = mapper.map(compraToInsert, CompraDTO.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(compraDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(compraDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CompraDTO> update(@PathVariable Long id, 
            @RequestBody Compra compra) {
        Compra compraToUpdate = compraService.update(id, compra);
        CompraDTO compraDTO = mapper.map(compraToUpdate, CompraDTO.class);
        return ResponseEntity.ok().body(compraDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        compraService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private CompraDTO convertToCompraDTO(Compra compra) {
        return mapper.map(compra, CompraDTO.class);
    }

}
