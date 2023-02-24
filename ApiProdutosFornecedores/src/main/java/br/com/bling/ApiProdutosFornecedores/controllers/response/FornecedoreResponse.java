package br.com.bling.ApiProdutosFornecedores.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class FornecedoreResponse {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ProdutoFornecedor2Response produtoFornecedor;
}
