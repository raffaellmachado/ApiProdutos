package br.com.bling.ApiProdutosFornecedores.service;

import br.com.bling.ApiProdutosFornecedores.controllers.request.RespostaRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.response.Resposta;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProdutoFornecedorService {

    public Resposta getAllProducts();

    public Resposta getProductByCode(@PathVariable String id);

    public RespostaRequest createProduct(@RequestBody String xml);

    public Resposta updateProduct(@RequestBody String xml, @PathVariable String id);
}
