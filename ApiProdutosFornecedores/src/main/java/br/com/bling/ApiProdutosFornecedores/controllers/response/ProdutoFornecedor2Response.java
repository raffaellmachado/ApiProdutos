package br.com.bling.ApiProdutosFornecedores.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoFornecedor2Response {

    public String idProdutoFornecedor;

    public Object idFornecedor;
    public String produtoDescricao;
    public String produtoCodigo;
    public String precoCompra;
    public String precoCusto;
    public String produtoGarantia;
    public String padrao;
}
