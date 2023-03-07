package br.com.bling.ApiProdutosFornecedores.service;

import br.com.bling.ApiProdutosFornecedores.controllers.response.JsonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProdutoFornecedorService {

    public JsonResponse getAllProducts();

    public JsonResponse getProducId(@PathVariable("idProdutoFornecedor") String idProdutoFornecedor);

    public Object createProduct(@RequestBody String xmlProdutoFornecedor);

    public Object updateProduct(@RequestBody String xmlProdutoFornecedor, @PathVariable("idProdutoFornecedor") String idProdutoFornecedor);
}
