package com.jonas.backend.repositories;

import com.jonas.backend.entities.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PFRepository extends JpaRepository<PessoaFisica, Long> {

}
