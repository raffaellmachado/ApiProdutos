package br.com.bling.ApiProdutos.models;

import lombok.Data;

@Data
public class Categoria {

    public String id;
    public String descricao;
    public String idCategoriaPai;
}

