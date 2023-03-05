package br.com.bling.ApiProdutos.controllers.response;

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
public class ProdutoResponse {

	public String id;

	public String codigo;

	public String descricao;
	public String tipo;
	public String situacao;
	public String unidade;
	public String preco;
	public String precoCusto;
	public String descricaoCurta;
	public String descricaoComplementar;
	public String dataInclusao;
	public String dataAlteracao;
	public String imageThumbnail;
	public String urlVideo;
	public String nomeFornecedor;
	public String codigoFabricante;
	public String marca;
	public String class_fiscal;
	public String cest;
	public String origem;
	public String idGrupoProduto;
	public String linkExterno;
	public String observacoes;
	public String grupoProduto;
	public String garantia;
	public String descricaoFornecedor;
	public String idFabricante;
	public CategoriaResponse categoria;
	public String pesoLiq;
	public String pesoBruto;
	public String estoqueMinimo;
	public String estoqueMaximo;
	public String gtin;
	public String gtinEmbalagem;
	public String larguraProduto;
	public String alturaProduto;
	public String profundidadeProduto;
	public String unidadeMedida;
	public String itensPorCaixa;
	public String volumes;
	public String localizacao;
	public String crossdocking;
	public String condicao;
	public String freteGratis;
	public String producao;
	public String dataValidade;
	public String spedTipoItem;
}


