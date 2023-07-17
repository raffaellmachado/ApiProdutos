package br.com.bling.ApiFormaPagamento.service;

import br.com.bling.ApiFormaPagamento.controllers.response.SelecionaLoja;

import java.util.List;
import java.util.Optional;

public interface SelecionaLojaService {

    List<SelecionaLoja> getAllLojas();

    Optional<SelecionaLoja> getLojaById(String idLoja);

    void deleteLojaById(String idLoja);

    SelecionaLoja saveLoja(SelecionaLoja selecionaLoja);

}
