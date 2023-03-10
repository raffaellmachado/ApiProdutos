package br.com.bling.ApiProdutosFornecedores.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoFornecedorRequest {

    @JsonProperty("id")
    public String id;
    @JsonProperty("idProdutoFornecedor")
    @Max(value = 20, message = "Identificador único do produto")
    public String idProdutoFornecedor;
    @JsonProperty("idFornecedor")
    @Max(value = 20, message = "Identificador único do contato (fornecedor)")
    public Integer idFornecedor;
    @JsonProperty("produtoDescricao")
    @Max(value = 120, message = "Descrição do produto no fornecedor")
    public String produtoDescricao;
    @JsonProperty("produtoCodigo")
    @Max(value = 60, message = "Código do produto no fornecedor")
    public String produtoCodigo;
    @JsonProperty("precoCompra")
    @Digits(integer = 17, fraction = 10)
    @Size(message = "Preço de compra do produto no fornecedor")
    public BigDecimal precoCompra;
    @JsonProperty("precoCusto")
    @Digits(integer = 17, fraction = 10)
    @Size(message = "Preço de custo do produto no fornecedor")
    public BigDecimal precoCusto;
    @JsonProperty("produtoGarantia")
    @Max(value = 3, message = "Garantia do produto no fornecedor")
    public Integer produtoGarantia;
    @JsonProperty("padrao")
    @Max(value = 1, message = "Principal fornecedor do produto")
    public Integer padrao;
}
