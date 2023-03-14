package br.com.bling.ApiDepositos.services;

import br.com.bling.ApiDepositos.controllers.request.JsonRequest;
import br.com.bling.ApiDepositos.controllers.response.JsonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface DepositoService {

    public JsonResponse getAllDeposit();

    public JsonResponse getDepositById(@PathVariable("idDeposito") String idDeposito);

    public JsonRequest createDeposit(@RequestBody String xmlDeposito);

    public JsonRequest updateDeposit(@RequestBody String xmlDeposito, @PathVariable("idDeposito") String idDeposito);

}
