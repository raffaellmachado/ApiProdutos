package br.com.bling.ApiProdutos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Retorno {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<Produtos> produtos;

    @Data
    public static class Produtos {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public Produto produto;
    }
}
