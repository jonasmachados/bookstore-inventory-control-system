package com.jonas.backend.repositories;

import com.jonas.backend.entities.Itens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensRepository extends JpaRepository<Itens, Long>{
    
}
