package br.com.bling.ApiProdutos.controllers.response;

import lombok.Data;

@Data
public class CategoriaResponse {

    public String id;
    public String descricao;
    public String idCategoriaPai;
}

