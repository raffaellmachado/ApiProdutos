package br.com.bling.ApiDepositos.repositories;

import br.com.bling.ApiDepositos.controllers.request.DepositoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositoRequestRepository extends JpaRepository<DepositoRequest, Long> {

}

