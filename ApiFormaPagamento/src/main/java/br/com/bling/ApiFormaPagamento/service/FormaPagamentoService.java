package br.com.bling.ApiFormaPagamento.service;

import br.com.bling.ApiFormaPagamento.controllers.request.JsonRequest;
import br.com.bling.ApiFormaPagamento.controllers.response.JsonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface FormaPagamentoService {

    JsonResponse getAllFormaPagamento();

    JsonResponse getFormaPagamentoById(@PathVariable("id") String id);

    ResponseEntity<String> deleteFormaPagemento(@PathVariable("id") String id);

    JsonRequest createFormaPagamento(@RequestBody String xmlFormaPagamento);

    JsonRequest updateFormaPagamento(@RequestBody String xmlFormaPagamento, @PathVariable("id") String id);
}