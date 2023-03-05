package br.com.bling.ApiProdutosFornecedores.service;

import br.com.bling.ApiProdutosFornecedores.controllers.request.JsonRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.response.JsonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProdutoFornecedorService {

    public JsonResponse getAllProducts();

    public JsonResponse getProducId(@PathVariable String idProdutoFornecedor);

    public JsonRequest createProduct(@RequestBody String xml);

    public JsonRequest updateProduct(@RequestBody String xml, @PathVariable String idProdutoFornecedor);
}
