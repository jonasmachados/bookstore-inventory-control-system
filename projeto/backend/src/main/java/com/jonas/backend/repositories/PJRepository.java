package com.jonas.backend.repositories;

import com.jonas.backend.entities.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PJRepository extends JpaRepository<PessoaJuridica, Long> {


}
