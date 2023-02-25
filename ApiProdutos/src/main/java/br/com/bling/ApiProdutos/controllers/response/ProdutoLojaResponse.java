package br.com.bling.ApiProdutos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ProdutoLojaResponse {

    public PrecoResponse preco;
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<CategoriaResponse> categoria;
}