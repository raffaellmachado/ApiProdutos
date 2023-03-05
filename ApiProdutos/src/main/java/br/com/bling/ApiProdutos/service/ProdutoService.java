package br.com.bling.ApiProdutos.service;

import br.com.bling.ApiProdutos.controllers.request.JsonRequest;
import br.com.bling.ApiProdutos.controllers.response.JsonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ProdutoService {

    public JsonResponse getAllProducts();

    public JsonResponse getProductByCode(@PathVariable String codigo);

    public JsonResponse getProductByCodeSupplier(@PathVariable String codigo, @PathVariable String codigoFabricante);

    public void deleteProductByCode(@PathVariable String codigo);

    public JsonRequest createProduct(@RequestBody String xml);

    public JsonRequest updateProduct(@RequestBody @Valid String xml, @PathVariable String codigo);
}
