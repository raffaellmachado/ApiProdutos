package br.com.bling.ApiProdutos.service;

import br.com.bling.ApiProdutos.models.Produto;
import br.com.bling.ApiProdutos.models.Resposta;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProdutoService {

    public Resposta getAllProducts();

    public Resposta getProductByCode(String codigo);

    public Resposta getProductByCodeSupplier(String codigo, String nomeFornecedor);

    public void deleteProductByCode(String codigo);

    public String createProduct(@RequestBody String xml);
/*
    public ProdutoRequest createProduct(ProdutoRequest produtoRequest);
*/


}
