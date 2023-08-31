package com.jonas.backend.controllers;

import com.jonas.backend.dto.LivroDTO;
import com.jonas.backend.entities.Livro;
import com.jonas.backend.services.LivroService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/livro")
public class LivroController {

    @Autowired
    private LivroService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll() {

        List<LivroDTO> livroDTO = service.findAll()
                .stream()
                .map(this::convertToLivroDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(livroDTO);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Long id) {

        LivroDTO livroDTO = convertToLivroDTO(service.findById(id));

        return ResponseEntity.ok().body(livroDTO);

    }

    @PostMapping
    public ResponseEntity<LivroDTO> insert(@RequestBody @Valid LivroDTO livroDTO) {

        Livro livro = mapper.map(livroDTO, Livro.class);
        livro = service.insert(livro);

        LivroDTO savedLivroDTO = convertToLivroDTO(livro);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedLivroDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(savedLivroDTO);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> update(@PathVariable Long id,
            @RequestBody LivroDTO livroDTO) {

        Livro livro = mapper.map(livroDTO, Livro.class);
        livro = service.update(id, livro);

        LivroDTO updatedLivroDTO = convertToLivroDTO(livro);

        return ResponseEntity.ok().body(updatedLivroDTO);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();

    }

    private LivroDTO convertToLivroDTO(Livro livro) {

        return mapper.map(livro, LivroDTO.class);

    }

}
