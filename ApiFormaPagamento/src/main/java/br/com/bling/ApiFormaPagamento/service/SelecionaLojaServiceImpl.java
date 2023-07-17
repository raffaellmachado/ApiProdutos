package br.com.bling.ApiFormaPagamento.service;

import br.com.bling.ApiFormaPagamento.controllers.response.SelecionaLoja;
import br.com.bling.ApiFormaPagamento.repositories.SelecionaLojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class SelecionaLojaServiceImpl implements SelecionaLojaService {

    private final SelecionaLojaRepository selecionaLojaRepository;

    @Autowired
    public SelecionaLojaServiceImpl(SelecionaLojaRepository selecionaLojaRepository) {
        this.selecionaLojaRepository = selecionaLojaRepository;
    }

    @Override
    public List<SelecionaLoja> getAllLojas() {
        return selecionaLojaRepository.findAll();
    }

    @Override
    public Optional<SelecionaLoja> getLojaById(String idLoja) {
        Optional<SelecionaLoja> lojaOptional = selecionaLojaRepository.findByIdLoja(idLoja);
        if (lojaOptional.isPresent()) {
            return lojaOptional;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Loja não encontrada com o ID: " + idLoja);
        }
    }

    @Override
    @Transactional
    public void deleteLojaById(String idLoja) {
        Optional<SelecionaLoja> lojaOptional = selecionaLojaRepository.findByIdLoja(idLoja);
        if (lojaOptional.isPresent()) {
            selecionaLojaRepository.deleteByIdLoja(idLoja);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Loja não encontrada com o ID: " + idLoja);
        }
    }

    @Override
    @Transactional
    public SelecionaLoja saveLoja(SelecionaLoja selecionaLoja) {
        return selecionaLojaRepository.save(selecionaLoja);
    }
}
