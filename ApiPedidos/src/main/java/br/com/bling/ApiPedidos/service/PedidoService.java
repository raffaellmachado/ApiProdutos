package br.com.bling.ApiPedidos.service;

import br.com.bling.ApiPedidos.controllers.response.JsonResponse;

public interface PedidoService {

    JsonResponse getAllPedido();
//
//    JsonResponse getCategoryByIdPedido(@PathVariable("idPedido") String idPedido);
//
//    JsonRequest createPedido(@RequestBody String xmlPedido);
//
//    JsonRequest updatePedido(@RequestBody String xmlPedido, @PathVariable("idPedido") String idPedido) throws ParserConfigurationException, IOException, SAXException;
}