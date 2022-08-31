package com.jonas.backend.resource;

import com.jonas.backend.entities.Compra;
import com.jonas.backend.entities.Itens;
import com.jonas.backend.entities.Venda;
import com.jonas.backend.services.CompraService;
import java.net.URI;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/compra")
public class CompraResource {

    @Autowired
    private CompraService service;

    @GetMapping
    public ResponseEntity<List<Compra>> findAll() {
        List<Compra> list = service.findAll();
        return ResponseEntity.ok().body(list);//Contralador rest
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Compra> findById(@PathVariable Long id) { //ResponseEntity: resposta de aquisicao web
        Compra obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @PostMapping
    public ResponseEntity<Compra> insert(@RequestBody Compra obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    
     @PutMapping(value = "/{id}")
    public ResponseEntity<Compra> update(@PathVariable Long id, @RequestBody Compra obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
