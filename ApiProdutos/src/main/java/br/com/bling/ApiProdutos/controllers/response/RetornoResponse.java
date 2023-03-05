package br.com.bling.ApiProdutos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetornoResponse {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<Produtos> produtos;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Produtos {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public ProdutoResponse produto;
    }
}
