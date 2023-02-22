package br.com.bling.ApiProdutos.service;

import br.com.bling.ApiProdutos.models.Resposta;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProdutoService {

    public Resposta getAllProducts();

    public Resposta getProductByCode(@PathVariable String codigo);

    public Resposta getProductByCodeSupplier(@PathVariable String codigo, @PathVariable String nomeFornecedor);

    public void deleteProductByCode(@PathVariable String codigo);

    public Resposta createProduct(@RequestBody String xml);

    public Resposta updateProduct(@RequestBody String xml, @PathVariable String codigo);
}
