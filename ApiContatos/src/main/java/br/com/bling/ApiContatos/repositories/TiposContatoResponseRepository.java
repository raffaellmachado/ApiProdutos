package br.com.bling.ApiContatos.repositories;


import br.com.bling.ApiContatos.controllers.response.TiposContatoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TiposContatoResponseRepository extends JpaRepository<TiposContatoResponse, Long> {

    Optional<TiposContatoResponse> findById(Long id);

    Optional<TiposContatoResponse> findByContatoResponseIdAndTipoContatoId(Long contatoResponseId, Long tipoContatoId);

}
