package br.com.bling.ApiDeposito.services;


import br.com.bling.ApiDeposito.controllers.request.DepositoRequest;
import br.com.bling.ApiDeposito.controllers.request.RespostaRequest;
import br.com.bling.ApiDeposito.controllers.response.RespostaResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface DepositoService {

    public RespostaResponse getAllDeposit();

    public RespostaResponse getDepositByIdDeposit(@PathVariable String idDeposito);

    public RespostaRequest createDeposit(@RequestBody String xml);

    public String updateDeposit(@RequestBody String xml, @PathVariable String idDeposito);

}
