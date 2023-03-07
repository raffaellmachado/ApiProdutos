package br.com.bling.ApiProdutosFornecedores.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RetornoRequest {

    @JsonProperty("produtosfornecedores")
    public ArrayList<Produtosfornecedore> produtosfornecedores;

    @Data
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Produtosfornecedore {

        @JsonProperty("produtoFornecedor")
        public ProdutoFornecedor2Request produtoFornecedor;
    }
}
