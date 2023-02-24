package br.com.bling.ApiProdutosFornecedores.controllers.request;

import br.com.bling.ApiProdutosFornecedores.controllers.response.ProdutoFornecedor2;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class FornecedoreRequest {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ProdutoFornecedor2 produtoFornecedor;
}
