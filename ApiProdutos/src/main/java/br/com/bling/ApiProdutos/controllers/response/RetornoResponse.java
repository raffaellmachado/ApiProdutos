package br.com.bling.ApiProdutos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class RetornoResponse {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<Produtos> produtos;

    @Data
    public static class Produtos {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public ProdutoResponse produto;
    }
}
