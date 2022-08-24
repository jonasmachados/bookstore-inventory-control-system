package com.jonas.backend.resource;

import com.jonas.backend.entities.Venda;
import com.jonas.backend.repositories.VendaRepository;
import com.jonas.backend.services.VendaService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    
    @PostMapping(path =  "/add")
    public ResponseEntity<Venda> insert(@RequestBody Venda obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

}
