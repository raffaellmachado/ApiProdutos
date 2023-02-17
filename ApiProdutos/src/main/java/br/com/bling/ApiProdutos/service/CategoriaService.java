package br.com.bling.ApiProdutos.service;

import br.com.bling.ApiProdutos.models.Resposta;
import br.com.bling.ApiProdutos.models.Retorno;

import java.util.List;

public interface CategoriaService {

    public Resposta getCategory();

    public Resposta getCategoryByIdCategoria(String idCategoria);

    public String createCategory(String xml);

}
