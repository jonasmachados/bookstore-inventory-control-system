package com.jonas.backend.resource;

import com.jonas.backend.entities.Venda;
import com.jonas.backend.repositories.VendaRepository;
import com.jonas.backend.services.VendaService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/vendas")
public class VendaResource {

    @Autowired
    private VendaService service;
    
     @Autowired
    private VendaRepository repository;
    
    @GetMapping
    public List<Venda> getAll() {
        return service.getAll();
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Venda> getFindById(@PathVariable Long id) { //ResponseEntity: resposta de aquisicao web
        Venda obj = service.getFindById(id);
        return ResponseEntity.ok().body(obj);

    }
    
    @PostMapping(path =  "/add")
    public ResponseEntity<Venda> insert(@RequestBody Venda obj) {
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
    public ResponseEntity<Venda> update(@PathVariable Long id, @RequestBody Venda obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }


}
