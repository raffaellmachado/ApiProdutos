package br.com.bling.ApiContatos.repositories;

import br.com.bling.ApiContatos.controllers.request.ContatoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
//public interface ContatoRequestRepository extends JpaRepository<ContatoRequest, Long> {
//
//
//    Optional<ContatoRequest> findById(Long id);
//
//    @Query("SELECT c FROM ContatoRequest c WHERE c.cpf_cnpj = :cpf_cnpj")
//    List<ContatoRequest> findByCpfCnpj(@Param("cpf_cnpj") String cpf_cnpj);
//}

