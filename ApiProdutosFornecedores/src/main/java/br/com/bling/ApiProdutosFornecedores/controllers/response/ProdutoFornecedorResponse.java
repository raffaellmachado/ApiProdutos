package br.com.bling.ApiProdutosFornecedores.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoFornecedorResponse {

    @JsonProperty("idProdutoFornecedor")
    public String idProdutoFornecedor;
    @JsonProperty("idFornecedor")
    public Object idFornecedor;
    @JsonProperty("produtoDescricao")
    public String produtoDescricao;
    @JsonProperty("produtoCodigo")
    public String produtoCodigo;
    @JsonProperty("precoCompra")
    public String precoCompra;
    @JsonProperty("precoCusto")
    public String precoCusto;
    @JsonProperty("produtoGarantia")
    public String produtoGarantia;
    @JsonProperty("padrao")
    public String padrao;
}
