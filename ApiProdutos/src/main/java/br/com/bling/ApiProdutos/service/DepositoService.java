package br.com.bling.ApiProdutos.service;

import br.com.bling.ApiProdutos.models.Resposta;

public interface DepositoService {

    public Resposta getAllDeposit();

    public Resposta getDepositByIdDeposit(String idDeposito);

    public String createDeposit(String xml);

}
