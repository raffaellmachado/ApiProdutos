package br.com.bling.ApiProdutosFornecedores.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoFornecedor2Response {

    public String idProdutoFornecedor;
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Object idFornecedor;
    public String produtoDescricao;
    public String produtoCodigo;
    public String precoCompra;
    public String precoCusto;
    public String produtoGarantia;
    public String padrao;
}
