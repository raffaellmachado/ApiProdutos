package br.com.bling.ApiProdutosFornecedores.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class Fornecedore {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ProdutoFornecedor produtoFornecedor;
}
