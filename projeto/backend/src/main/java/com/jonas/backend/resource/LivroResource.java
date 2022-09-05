package com.jonas.backend.resource;

import com.jonas.backend.dto.QuantityDTO;
import com.jonas.backend.entities.Livro;
import com.jonas.backend.services.LivroService;
import java.net.URI;
import java.util.List;
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
import org.springframework.web.bind.annotation.PatchMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/livro")
public class LivroResource {

    @Autowired
    private LivroService service;

    //Metodo que retorna os usuarios
    @GetMapping
    public ResponseEntity<List<Livro>> findAll() {
        List<Livro> list = service.findAll();
        return ResponseEntity.ok().body(list);//Contralador rest
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Long id) { //ResponseEntity: resposta de aquisicao web
        Livro obj = service.findById(id);
        return ResponseEntity.ok().body(obj);

    }

    @PostMapping
    public ResponseEntity<Livro> insert(@RequestBody Livro obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody Livro obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @PatchMapping("/{id}/addEstoque")
    public Livro add(@PathVariable Long id, @RequestBody QuantityDTO quantityDTO){
        return service.add(id, quantityDTO.getQtdItens());
    }


}
