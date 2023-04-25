package br.com.bling.ApiContatos.repositories;

import br.com.bling.ApiContatos.controllers.request.TipoContatoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoContatoRequestRepository extends JpaRepository<TipoContatoRequest, Long> {

    Optional<TipoContatoRequest> findById(Long id);

    Optional<TipoContatoRequest> findByDescricao(String descricao);

}
