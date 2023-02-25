package br.com.bling.ApiProdutos.controllers.request;

import br.com.bling.ApiProdutos.controllers.response.ProdutoResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class RetornoRequest {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<Produtos> produtos;

    @Data
    public static class Produtos {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public ProdutoResponse produto;
    }
}
