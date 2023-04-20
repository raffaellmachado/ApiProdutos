package br.com.bling.ApiProdutosFornecedores.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_PRODUTO_FORNECEDORES_RESPONSE")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoFornecedorResponse {


    @Id
    @Column(name = "id_produto_fornecedor")
    @JsonProperty("idProdutoFornecedor")
    public Integer idProdutoFornecedor;

    @JsonProperty("idFornecedor")
    public Integer idFornecedor;

    @Column(name = "produto_descricao")
    @JsonProperty("produtoDescricao")
    public String produtoDescricao;

    @Column(name = "produto_codigo")
    @JsonProperty("produtoCodigo")
    public String produtoCodigo;

    @Column(name = "preco_compra")
    @JsonProperty("precoCompra")
    public String precoCompra;

    @Column(name = "preco_custo")
    @JsonProperty("precoCusto")
    public String precoCusto;

    @Column(name = "produto_garantia")
    @JsonProperty("produtoGarantia")
    public String produtoGarantia;

    @Column(name = "padrao")
    @JsonProperty("padrao")
    public String padrao;
}
