package br.com.bling.ApiProdutosFornecedores.controllers.request;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ProdutoFornecedor2Request {

    public String id;
    @NotEmpty
    @Max(value = 20, message = "Identificador único do produto")
    public int idProduto;
    @NotEmpty
    @Max(value = 20, message = "Identificador único do contato (fornecedor)")
    public double idFornecedor;
    @Max(value = 120, message = "Descrição do produto no fornecedor")
    public String produtoDescricao;
    @Max(value = 60, message = "Código do produto no fornecedor")
    public String produtoCodigo;
    @Digits(integer = 17, fraction = 10)
    @Size(message = "Preço de compra do produto no fornecedor")
    public double precoCompra;
    @Digits(integer = 17, fraction = 10)
    @Size(message = "Preço de custo do produto no fornecedor")
    public double precoCusto;
    @Max(value = 3, message = "Garantia do produto no fornecedor")
    public int produtoGarantia;
    @Max(value = 1, message = "Principal fornecedor do produto")
    public int padrao;
}
