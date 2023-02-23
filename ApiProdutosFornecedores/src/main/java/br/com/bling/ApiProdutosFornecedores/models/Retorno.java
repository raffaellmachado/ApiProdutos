package br.com.bling.ApiProdutosFornecedores.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Retorno {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<Produtosfornecedore> produtosfornecedores;

    @Data
    public static class Produtosfornecedore {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public Produtofornecedores produtofornecedores;
    }
}
