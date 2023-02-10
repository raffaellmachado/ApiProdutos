package br.com.bling.ApiProdutos.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
@Table(name = "TB_PRODUTO")
@Entity 
*/
@NoArgsConstructor
@Getter
@Setter
public class Produto {

//	@Id
	@JsonProperty("id")
	private String id;
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
	private Object imageThumbnail;

	@JsonProperty("nomeFornecedor")
	private String nomeFornecedor;

	@JsonProperty("marca")
	private String marca;

	@JsonProperty("classFiscal")
	private String classFiscal;

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
	private int itensPorCaixa;

	@JsonProperty("volumes")
	private int volumes;

	@JsonProperty("urlVideo")
	private String urlVideo;

	@JsonProperty("localizacao")
	private String localizacao;

	@JsonProperty("crossdocking")
	private String crossdocking;

	@JsonProperty("garantia")
	private int garantia;

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
	private ProdutoLoja produtoLoja;

	
/*	
	public String getCodigo() {
		return codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getUnidade() {
		return unidade;
	}

	public double getPreco() {
		return preco;
	}

	public double getPrecoCusto() {
		return precoCusto;
	}

	public double getPesoLiq() {
		return pesoLiq;
	}

	public double getPesoBruto() {
		return pesoBruto;
	}

	public double getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public Object getEstoqueMaximo() {
		return estoqueMaximo;
	}

	public String getGtin() {
		return gtin;
	}

	public String getGtinEmbalagem() {
		return gtinEmbalagem;
	}

	public String getDescricaoCurta() {
		return descricaoCurta;
	}

	public String getDescricaoComplementar() {
		return descricaoComplementar;
	}

	public int getLarguraProduto() {
		return larguraProduto;
	}

	public int getAlturaProduto() {
		return alturaProduto;
	}

	public int getProfundidadeProduto() {
		return profundidadeProduto;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public String getDataInclusao() {
		return dataInclusao;
	}

	public String getDataAlteracao() {
		return dataAlteracao;
	}

	public Object getImageThumbnail() {
		return imageThumbnail;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public String getMarca() {
		return marca;
	}

	public String getClassFiscal() {
		return classFiscal;
	}

	public String getCest() {
		return cest;
	}

	public String getOrigem() {
		return origem;
	}

	public String getIdGrupoProduto() {
		return idGrupoProduto;
	}

	public String getLinkExterno() {
		return linkExterno;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public String getGrupoProduto() {
		return grupoProduto;
	}

	public int getItensPorCaixa() {
		return itensPorCaixa;
	}

	public int getVolumes() {
		return volumes;
	}

	public String getUrlVideo() {
		return urlVideo;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public String getCrossdocking() {
		return crossdocking;
	}

	public int getGarantia() {
		return garantia;
	}

	public String getCondicao() {
		return condicao;
	}

	public String getFreteGratis() {
		return freteGratis;
	}

	public String getProducao() {
		return producao;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public String getDescricaoFornecedor() {
		return descricaoFornecedor;
	}

	public List<Imagem> getImagem() {
		return imagem;
	}

	public ProdutoLoja getProdutoLoja() {
		return produtoLoja;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public void setPesoLiq(double pesoLiq) {
		this.pesoLiq = pesoLiq;
	}

	public void setPesoBruto(double pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public void setEstoqueMinimo(double estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public void setEstoqueMaximo(Object estoqueMaximo) {
		this.estoqueMaximo = estoqueMaximo;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public void setGtinEmbalagem(String gtinEmbalagem) {
		this.gtinEmbalagem = gtinEmbalagem;
	}

	public void setDescricaoCurta(String descricaoCurta) {
		this.descricaoCurta = descricaoCurta;
	}

	public void setDescricaoComplementar(String descricaoComplementar) {
		this.descricaoComplementar = descricaoComplementar;
	}

	public void setLarguraProduto(int larguraProduto) {
		this.larguraProduto = larguraProduto;
	}

	public void setAlturaProduto(int alturaProduto) {
		this.alturaProduto = alturaProduto;
	}

	public void setProfundidadeProduto(int profundidadeProduto) {
		this.profundidadeProduto = profundidadeProduto;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public void setDataInclusao(String dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public void setDataAlteracao(String dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public void setImageThumbnail(Object imageThumbnail) {
		this.imageThumbnail = imageThumbnail;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setClassFiscal(String classFiscal) {
		this.classFiscal = classFiscal;
	}

	public void setCest(String cest) {
		this.cest = cest;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public void setIdGrupoProduto(String idGrupoProduto) {
		this.idGrupoProduto = idGrupoProduto;
	}

	public void setLinkExterno(String linkExterno) {
		this.linkExterno = linkExterno;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public void setGrupoProduto(String grupoProduto) {
		this.grupoProduto = grupoProduto;
	}

	public void setItensPorCaixa(int itensPorCaixa) {
		this.itensPorCaixa = itensPorCaixa;
	}

	public void setVolumes(int volumes) {
		this.volumes = volumes;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public void setCrossdocking(String crossdocking) {
		this.crossdocking = crossdocking;
	}

	public void setGarantia(int garantia) {
		this.garantia = garantia;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public void setFreteGratis(String freteGratis) {
		this.freteGratis = freteGratis;
	}

	public void setProducao(String producao) {
		this.producao = producao;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public void setDescricaoFornecedor(String descricaoFornecedor) {
		this.descricaoFornecedor = descricaoFornecedor;
	}

	public void setImagem(List<Imagem> imagem) {
		this.imagem = imagem;
	}

	public void setProdutoLoja(ProdutoLoja produtoLoja) {
		this.produtoLoja = produtoLoja;
	}
*/

/*	-- Exemplo com Banco de Dados.
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	private String nome;
	private String quantidade;
	private String valor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
*/
}