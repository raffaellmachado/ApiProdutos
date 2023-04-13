package br.com.bling.ApiContatos.repositories;

import br.com.bling.ApiContatos.controllers.response.ContatoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContatoResponseRepository extends JpaRepository<ContatoResponse, String> {

    Optional<ContatoResponse> findById(String id);

}

