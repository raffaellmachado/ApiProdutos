package br.com.bling.ApiFormaPagamento.repositories;

import br.com.bling.ApiFormaPagamento.controllers.response.SelecionaLoja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SelecionaLojaRepository extends JpaRepository<SelecionaLoja, Long> {

    List<SelecionaLoja> findAll();
    Optional<SelecionaLoja> findByIdLoja(String idLoja);
    void deleteByIdLoja(String idLoja);

}

