package br.com.bling.ApiProdutos.service;

import br.com.bling.ApiProdutos.models.Resposta;

public interface CategoriaService {

    public Resposta getCategory();

    public Resposta getCategoryByIdCategoria(String idCategoria);

    public String createCategory(String xml);

}
