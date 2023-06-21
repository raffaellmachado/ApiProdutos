package br.com.bling.ApiFormaPagamento.service;

import br.com.bling.ApiFormaPagamento.controllers.response.SelecionaLoja;
import br.com.bling.ApiFormaPagamento.controllers.response.SelecionaLojaResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface SelecionaLojaService {

    List<SelecionaLoja> getAllLojas();

//    SelecionaLoja getLojaById(@PathVariable("idLoja") String idLoja);
//
//    SelecionaLoja saveLoja(SelecionaLoja selecionaLoja);
//
//    SelecionaLoja updateLoja(SelecionaLoja selecionaLoja);
}
