package br.com.bling.ApiDeposito.services;


import br.com.bling.ApiDeposito.controllers.response.JsonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface DepositoService {

    public JsonResponse getAllDeposit();

    public JsonResponse getDepositByIdDeposit(@PathVariable String idDeposito);

    public Object createDeposit(@RequestBody String xml);

    public Object updateDeposit(String xml, String idDeposito);

}
