package br.com.bling.ApiProdutos.service;

import br.com.bling.ApiProdutos.controllers.request.RespostaRequest;
import br.com.bling.ApiProdutos.controllers.response.RespostaResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProdutoService {

    public RespostaResponse getAllProducts();

    public RespostaResponse getProductByCode(@PathVariable String codigo);

    public RespostaResponse getProductByCodeSupplier(@PathVariable String codigo, @PathVariable String idFornecedor);

    public void deleteProductByCode(@PathVariable String codigo);

    public RespostaRequest createProduct(@RequestBody String xml);

    public RespostaRequest updateProduct(@RequestBody String xml, @PathVariable String codigo);
}
