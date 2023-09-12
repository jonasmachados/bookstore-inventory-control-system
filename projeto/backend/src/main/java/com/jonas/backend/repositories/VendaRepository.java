package com.jonas.backend.repositories;

import com.jonas.backend.entities.Venda;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query(nativeQuery = true, value = "SELECT * "
            + "FROM TB_VENDAS "
            + "WHERE CLIENTE_ID LIKE (:idClient)")
    List<Venda> findVendaByClient(int idClient);
    
}
