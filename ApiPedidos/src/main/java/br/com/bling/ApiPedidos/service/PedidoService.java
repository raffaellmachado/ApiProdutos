package br.com.bling.ApiPedidos.service;

import br.com.bling.ApiPedidos.controllers.request.JsonRequest;
import br.com.bling.ApiPedidos.controllers.response.JsonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface PedidoService {

    JsonResponse getAllPedido();

    JsonResponse getPedidoByIdPedido(@PathVariable("numero") String numero);

    JsonRequest createPedido(@RequestBody String xmlPedido);

    JsonRequest updatePedido(@RequestBody String xmlPedido, @PathVariable("numero") String numero);
}