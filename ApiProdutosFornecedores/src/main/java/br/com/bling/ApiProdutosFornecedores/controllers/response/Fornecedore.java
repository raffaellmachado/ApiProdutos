package br.com.bling.ApiProdutosFornecedores.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class Fornecedore {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ProdutoFornecedor2 produtoFornecedor;
}
