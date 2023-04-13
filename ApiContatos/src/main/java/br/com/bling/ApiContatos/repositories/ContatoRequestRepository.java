package br.com.bling.ApiContatos.repositories;

import br.com.bling.ApiContatos.controllers.request.ContatoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRequestRepository extends JpaRepository<ContatoRequest, String> {

}

