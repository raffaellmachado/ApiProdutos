package br.com.bling.ApiProdutosFornecedores.service;

import br.com.bling.ApiProdutosFornecedores.controllers.response.Resposta;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProdutoFornecedorService {

    public Resposta getAllProducts();

    public Resposta getProductByCode(@PathVariable String id);

    public Resposta createProduct(@RequestBody String xml);

    public Resposta updateProduct(@RequestBody String xml, @PathVariable String id);
}
