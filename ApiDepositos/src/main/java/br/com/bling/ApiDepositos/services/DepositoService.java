package br.com.bling.ApiDepositos.services;

import br.com.bling.ApiDepositos.controllers.request.JsonRequest;
import br.com.bling.ApiDepositos.controllers.response.JsonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface DepositoService {

    JsonResponse getAllDeposit();

    JsonResponse getDepositById(@PathVariable("idDeposito") String idDeposito);

    JsonRequest createDeposit(@RequestBody String xmlDeposito);

    JsonRequest updateDeposit(@RequestBody String xmlDeposito, @PathVariable("idDeposito") String idDeposito);

}
