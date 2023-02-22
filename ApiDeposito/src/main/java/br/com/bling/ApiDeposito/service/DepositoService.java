package br.com.bling.ApiDeposito.service;


import br.com.bling.ApiDeposito.models.Resposta;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface DepositoService {

    public Resposta getAllDeposit();

    public Resposta getDepositByIdDeposit(@PathVariable String idDeposito);

    public String createDeposit(@RequestBody String xml);

    public String updateDeposit(@RequestBody String xml, @PathVariable String idDeposito);

}
