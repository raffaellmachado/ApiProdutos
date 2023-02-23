package br.com.bling.ApiProdutosFornecedores.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class ProdutoFornecedor2 {

    public String id;
    public int idProdutoFornecedor;
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Object idFornecedor;
    public String produtoDescricao;
    public String produtoCodigo;
    public String precoCompra;
    public String precoCusto;
    public int produtoGarantia;
    public int padrao;
}
