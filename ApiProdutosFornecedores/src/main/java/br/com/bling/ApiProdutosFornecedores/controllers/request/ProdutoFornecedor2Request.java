package br.com.bling.ApiProdutosFornecedores.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoFornecedor2Request {

    public String id;

    @Max(value = 20, message = "Identificador único do produto")
    public String idProdutoFornecedor;
    @Max(value = 20, message = "Identificador único do contato (fornecedor)")
    public Integer idFornecedor;
    @Max(value = 120, message = "Descrição do produto no fornecedor")
    public String produtoDescricao;
    @Max(value = 60, message = "Código do produto no fornecedor")
    public String produtoCodigo;
    @Digits(integer = 17, fraction = 10)
    @Size(message = "Preço de compra do produto no fornecedor")
    public BigDecimal precoCompra;
    @Digits(integer = 17, fraction = 10)
    @Size(message = "Preço de custo do produto no fornecedor")
    public BigDecimal precoCusto;
    @Max(value = 3, message = "Garantia do produto no fornecedor")
    public Integer produtoGarantia;
    @Max(value = 1, message = "Principal fornecedor do produto")
    public Integer padrao;
}
