package br.com.bling.ApiDepositos.repositories;

import br.com.bling.ApiDepositos.controllers.request.DepositoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepositoRequestRepository extends JpaRepository<DepositoRequest, Long> {

    Optional<DepositoRequest> findById(Long idDeposito);
    @Query("SELECT c FROM DepositoRequest c WHERE c.descricao = :descricao")
    List<DepositoRequest> findByDescricao(@Param("descricao") String descricao);

}

