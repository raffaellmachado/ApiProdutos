package br.com.bling.ApiProdutosFornecedores.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class RetornoResponse {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<Produtosfornecedores> produtosfornecedores;

    @Data
    public static class Produtosfornecedores {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public ProdutofornecedorResponse produtofornecedores;
    }
}
