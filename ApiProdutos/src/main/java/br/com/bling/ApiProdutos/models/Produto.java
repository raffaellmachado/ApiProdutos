package br.com.bling.ApiProdutos.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class Produto {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("codigo")
	private String codigo;

	@JsonProperty("tipo")
	private String tipo;

	@JsonProperty("descricao")
	private String descricao;

	@JsonProperty("unidade")
	private String unidade;

	@JsonProperty("preco")
	private double preco;

	@JsonProperty("precoCusto")
	private double precoCusto;

	@JsonProperty("pesoLiq")
	private double pesoLiq;

	@JsonProperty("pesoBruto")
	private double pesoBruto;

	@JsonProperty("estoqueMinimo")
	private double estoqueMinimo;

	@JsonProperty("estoqueMaximo")
	private Object estoqueMaximo;

	@JsonProperty("gtin")
	private String gtin;

	@JsonProperty("gtinEmbalagem")
	private String gtinEmbalagem;

	@JsonProperty("descricaoCurta")
	private String descricaoCurta;

	@JsonProperty("descricaoComplementar")
	private String descricaoComplementar;

	@JsonProperty("larguraProduto")
	private int larguraProduto;

	@JsonProperty("alturaProduto")
	private int alturaProduto;

	@JsonProperty("profundidadeProduto")
	private int profundidadeProduto;

	@JsonProperty("unidadeMedida")
	private String unidadeMedida;

	@JsonProperty("dataInclusao")
	private String dataInclusao;

	@JsonProperty("dataAlteracao")
	private String dataAlteracao;

	@JsonProperty("imageThumbnail")
	private String imageThumbnail;

	@JsonProperty("nomeFornecedor")
	private String nomeFornecedor;

	@JsonProperty("marca")
	private String marca;

	@JsonProperty("class_fiscal")
	private String class_fiscal;

	@JsonProperty("cest")
	private String cest;

	@JsonProperty("origem")
	private String origem;

	@JsonProperty("idGrupoProduto")
	private String idGrupoProduto;

	@JsonProperty("linkExterno")
	private String linkExterno;

	@JsonProperty("observacoes")
	private String observacoes;

	@JsonProperty("grupoProduto")
	private String grupoProduto;

	@JsonProperty("itensPorCaixa")
	private String itensPorCaixa;

	@JsonProperty("volumes")
	private String volumes;

	@JsonProperty("urlVideo")
	private String urlVideo;

	@JsonProperty("localizacao")
	private String localizacao;

	@JsonProperty("crossdocking")
	private String crossdocking;

	@JsonProperty("garantia")
	private String garantia;

	@JsonProperty("condicao")
	private String condicao;

	@JsonProperty("freteGratis")
	private String freteGratis;

	@JsonProperty("producao")
	private String producao;

	@JsonProperty("dataValidade")
	private String dataValidade;

	@JsonProperty("descricaoFornecedor")
	private String descricaoFornecedor;

	@JsonProperty("imagem")
	private List<Imagem> imagem;

	@JsonProperty("produtoLoja")
	private List <ProdutoLoja> produtoLoja;

	@JsonProperty("codigopai")
	private String codigopai;

	@JsonProperty("estoqueAtual")
	private String estoqueAtual;

	@JsonProperty("deposito")
	private List <Deposito> deposito;

}