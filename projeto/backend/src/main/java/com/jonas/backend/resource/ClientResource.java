package com.jonas.backend.resource;

import com.jonas.backend.entities.Client;
import com.jonas.backend.entities.PessoaFisica;
import com.jonas.backend.entities.PessoaJuridica;
import com.jonas.backend.services.ClientService;
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

@RestController //controller rest
@RequestMapping(value = "/clientes") //Path of controller
public class ClientResource {

    @Autowired //Anoatcao que  Associa a instancia 
    private ClientService service;

    //Metodo que retorna os usuarios
    @GetMapping
    public ResponseEntity<List<Client>> findAll() { //ResponseEntity: resposta de aquisicao web
        List<Client> list = service.findAll();
        return ResponseEntity.ok().body(list);//Contralador rest
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) { //ResponseEntity: resposta de aquisicao web
        Client obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    //PJ 
    @PostMapping("pj")
    public ResponseEntity<PessoaJuridica> insert(@RequestBody PessoaJuridica obj) {
        obj = service.insertPJ(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    //PJ 
    @PutMapping(value = "/pj/{id}")
    public ResponseEntity<PessoaJuridica> update(@PathVariable Long id, @RequestBody PessoaJuridica obj) {
        obj = service.updatePJ(id, obj);
        return ResponseEntity.ok().body(obj);
    }
    
    //PF 
    @PostMapping("pf")
    public ResponseEntity<PessoaFisica> insert(@RequestBody PessoaFisica obj) {
        obj = service.insertPF(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    //PF 
    @PutMapping(value = "/pf/{id}")
    public ResponseEntity<PessoaFisica> update(@PathVariable Long id, @RequestBody PessoaFisica obj) {
        obj = service.updatePF(id, obj);
        return ResponseEntity.ok().body(obj);
    }
    
     @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
