package br.com.bling.ApiContatos.repositories;


import br.com.bling.ApiContatos.controllers.request.TiposContatoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TiposContatoRequestRepository extends JpaRepository<TiposContatoRequest, Long> {

    Optional<TiposContatoRequest> findById(Long id);
}
