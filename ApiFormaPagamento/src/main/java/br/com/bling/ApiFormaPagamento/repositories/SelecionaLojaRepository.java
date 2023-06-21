package br.com.bling.ApiFormaPagamento.repositories;

import br.com.bling.ApiFormaPagamento.controllers.response.SelecionaLoja;
import br.com.bling.ApiFormaPagamento.controllers.response.SelecionaLojaResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelecionaLojaRepository extends JpaRepository<SelecionaLoja, Long> {

    List<SelecionaLoja> findAll();
//    SelecionaLoja findById(String idLoja);
//
//    SelecionaLoja save(SelecionaLoja selecionaLoja);
//
//    SelecionaLoja update(SelecionaLoja selecionaLoja);
//
//    void delete(SelecionaLoja selecionaLoja);

}

