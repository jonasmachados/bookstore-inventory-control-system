package com.jonas.backend.repositories;

import com.jonas.backend.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long>{
}
