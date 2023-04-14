package br.com.bling.ApiProdutosFornecedores.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_PRODUTO_FORNECEDOR_II_RESPONSE")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoFornecedorResponse {

    @Id
    @JsonProperty("idProdutoFornecedor")
    public Integer idProdutoFornecedor;

    @JsonProperty("idFornecedor")
    public Integer idFornecedor;

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
