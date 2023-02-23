package br.com.bling.ApiProdutosFornecedores.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class ProdutoFornecedor {

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
