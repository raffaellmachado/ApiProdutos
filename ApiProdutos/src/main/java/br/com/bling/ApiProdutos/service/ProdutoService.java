package br.com.bling.ApiProdutos.service;

import br.com.bling.ApiProdutos.controllers.request.JsonRequest;
import br.com.bling.ApiProdutos.controllers.response.JsonResponse;
import br.com.bling.ApiProdutos.controllers.response.ProdutoResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface ProdutoService {

//    public JsonResponse getAllProducts();

    public List<ProdutoResponse> getAllProducts();

    public JsonResponse getProductByCode(@PathVariable("codigo") String codigo);

    public JsonResponse getProductByCodeSupplier(@PathVariable("codigoFabricante") String codigoFabricante, @PathVariable("idFabricante") String idFabricante);

    public void deleteProductByCode(@PathVariable("codigo") String codigo);

    public JsonRequest createProduct(@RequestBody String xmlProdutos);

    public JsonRequest updateProduct(@RequestBody @Valid String xmlProdutos, @PathVariable("codigo") String codigo);
}
