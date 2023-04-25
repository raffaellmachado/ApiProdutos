package br.com.bling.ApiContatos.repositories;

import br.com.bling.ApiContatos.controllers.response.ContatoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContatoResponseRepository extends JpaRepository<ContatoResponse, Long> {

    Optional<ContatoResponse> findById(Long id);

    @Query("SELECT c.cnpj FROM ContatoResponse c")
    List<String> findAllDescricao();

}

