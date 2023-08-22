package com.jonas.backend.repositories;

import com.jonas.backend.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Cliente, Long> {

}
