package br.com.bling.ApiProdutosFornecedores.service;

import br.com.bling.ApiProdutosFornecedores.controllers.request.JsonRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.response.JsonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProdutoFornecedorService {

    public JsonResponse getAllProducts();

    public JsonResponse getProducId(@PathVariable("idProdutoFornecedor") String idProdutoFornecedor);

    public JsonRequest createProduct(@RequestBody String xmlProdutoFornecedor);

    public JsonRequest updateProduct(@RequestBody String xmlProdutoFornecedor, @PathVariable("idProdutoFornecedor") String idProdutoFornecedor);
}
