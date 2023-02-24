package br.com.bling.ApiProdutosFornecedores.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class RetornoRequest {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<Produtosfornecedore> produtosfornecedores;

    @Data
    public static class Produtosfornecedore {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public ProdutoFornecedor2Request produtoFornecedor;
    }
}
