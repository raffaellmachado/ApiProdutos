package br.com.bling.ApiDeposito.service;


import br.com.bling.ApiDeposito.models.Resposta;

public interface DepositoService {

    public Resposta getAllDeposit();

    public Resposta getDepositByIdDeposit(String idDeposito);

    public String createDeposit(String xml);

}
