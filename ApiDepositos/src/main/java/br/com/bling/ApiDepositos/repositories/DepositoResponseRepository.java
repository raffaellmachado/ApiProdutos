package br.com.bling.ApiDepositos.repositories;

import br.com.bling.ApiDepositos.controllers.request.DepositoRequest;
import br.com.bling.ApiDepositos.controllers.response.DepositoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepositoResponseRepository extends JpaRepository<DepositoResponse, Long> {

    Optional<DepositoResponse> findById(Long id);

}

