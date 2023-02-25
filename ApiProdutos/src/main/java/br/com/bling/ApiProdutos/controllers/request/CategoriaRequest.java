package br.com.bling.ApiProdutos.controllers.request;

import lombok.Data;

@Data
public class CategoriaRequest {

    public String id;
    public String descricao;
    public String idCategoriaPai;
}

