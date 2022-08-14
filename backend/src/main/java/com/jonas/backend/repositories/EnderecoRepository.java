package com.jonas.backend.repositories;

import com.jonas.backend.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
    
}
