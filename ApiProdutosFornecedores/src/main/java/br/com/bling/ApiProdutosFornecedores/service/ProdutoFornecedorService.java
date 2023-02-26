package br.com.bling.ApiProdutosFornecedores.service;

import br.com.bling.ApiProdutosFornecedores.controllers.request.RespostaRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.response.RespostaResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProdutoFornecedorService {

    public RespostaResponse getAllProducts();

    public RespostaResponse getProducId(@PathVariable String idProdutoFornecedor);

    public RespostaRequest createProduct(@RequestBody String xml);

    public RespostaRequest updateProduct(@RequestBody String xml, @PathVariable String idProdutoFornecedor);
}
