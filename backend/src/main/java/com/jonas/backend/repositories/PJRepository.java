package com.jonas.backend.repositories;

import com.jonas.backend.entities.Client;
import com.jonas.backend.entities.PessoaJuridica;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PJRepository extends JpaRepository<PessoaJuridica, Long> {


}
