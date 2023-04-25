package br.com.bling.ApiContatos.repositories;

import br.com.bling.ApiContatos.controllers.response.TipoContatoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoContatoResponseRepository extends JpaRepository<TipoContatoResponse, Long> {

    Optional<TipoContatoResponse> findById(Long id);

    Optional<TipoContatoResponse> findByDescricao(String descricao);


}
