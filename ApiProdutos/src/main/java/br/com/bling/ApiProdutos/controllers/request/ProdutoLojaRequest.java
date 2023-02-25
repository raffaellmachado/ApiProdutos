package br.com.bling.ApiProdutos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ProdutoLojaRequest {

    public PrecoRequest preco;
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<CategoriaRequest> categoria;
}