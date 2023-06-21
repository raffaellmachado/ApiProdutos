package br.com.bling.ApiFormaPagamento.service;

import br.com.bling.ApiFormaPagamento.controllers.response.SelecionaLoja;
import br.com.bling.ApiFormaPagamento.controllers.response.SelecionaLojaResponse;
import br.com.bling.ApiFormaPagamento.repositories.SelecionaLojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class SelecionaLojaServiceImpl implements SelecionaLojaService {


    @Autowired
    public SelecionaLojaRepository selecionaLojaRepository;

    @Autowired
    public RestTemplate restTemplate;

    @Override
    public List<SelecionaLoja> getAllLojas() {
        return selecionaLojaRepository.findAll();
    }

//    @Override
//    public SelecionaLoja getLojaById(String idLoja) {
//        return selecionaLojaRepository.findById(idLoja);
//    }
//
//    @Override
//    public SelecionaLoja saveLoja(SelecionaLoja selecionaLoja) {
//        return selecionaLojaRepository.save(selecionaLoja);
//    }
//
//    @Override
//    public SelecionaLoja updateLoja(SelecionaLoja selecionaLoja) {
//        return null;
//    }
}
