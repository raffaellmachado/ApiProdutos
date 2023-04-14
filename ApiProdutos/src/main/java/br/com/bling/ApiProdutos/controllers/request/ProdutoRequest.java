package br.com.bling.ApiProdutos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_PRODUTO_REQUEST")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoRequest {

    @Id
    @JsonProperty("id")
    public String id;

    @Max(value = 60, message = "Código do produto")
    @JsonProperty("codigo")
    public String codigo;

    @Max(value = 4, message = "Código de item de serviço (06, 21 e 22)")
    @JsonProperty("codigoItem")
    public Integer codigoItem;

    @NotEmpty
    @Max(value = 120, message = "Descrição do produto")
    @JsonProperty("descricao")
    public String descricao;

    @Size(max = 1, message = "Tipo ('S' para serviço, 'P' para produto e 'N' para serviço de nota 06, 21 e 22)")
    @JsonProperty("tipo")
    public String tipo;

    @Size(message = "Situação do produto (Ativo ou Inativo)")
    @JsonProperty("situacao")
    public String situacao;

    @Size(message = "Descrição curta do produto")
    @JsonProperty("descricaoCurta")
    public String descricaoCurta;

    @Size(message = "Descrição complementar do produto")
    @JsonProperty("descricaoComplementar")
    public String descricaoComplementar;

    @Max(value = 6, message = "Tipo de unidade do produto")
    @JsonProperty("un")
    public String un;

    @DecimalMin("0")
    @Digits(integer = 17, fraction = 10)
    @Size(message = "Valor unitário do produto")
    @JsonProperty("vlr_unit")
    public BigDecimal vlr_unit;

    @Digits(integer = 17, fraction = 10)
    @Size(message = "Preço de custo do produto")
    @JsonProperty("preco_custo")
    public BigDecimal preco_custo;

    @Digits(integer = 11, fraction = 3)
    @Size(message = "Peso bruto do produto")
    @JsonProperty("peso_bruto")
    public BigDecimal peso_bruto;

    @Digits(integer = 11, fraction = 3)
    @Size(message = "Peso líquido do produto")
    @JsonProperty("peso_liq")
    public BigDecimal peso_liq;

    @Max(value = 10, message = "NCM do produto")
    @JsonProperty("class_fiscal")
    public String class_fiscal;

    @Max(value = 40, message = "Marca do produto")
    @JsonProperty("marca")
    public String marca;

    @Max(value = 7, message = "CEST do produto")
    @JsonProperty("cest")
    public String cest;

    @Max(value = 1, message = "Origem do produto")
    @JsonProperty("origem")
    public String origem;

    @Max(value = 11, message = "Identificador do grupo do produto")
    @JsonProperty("idGrupoProduto")
    public BigDecimal idGrupoProduto;

    @Size(message = "Condição do produto (Não especificado, Novo, Recondicionado ou Usado)")
    @JsonProperty("condicao")
    public String condicao = "Não especificado";

    @Max(value = 1, message = "Frete grátis (S para Sim ou N para Não)")
    @JsonProperty("freteGratis")
    public String freteGratis;

    @Max(value = 100, message = "Link do produto na loja virtual, marketplace, catálago etc.")
    @JsonProperty("linkExterno")
    public String linkExterno;

    @Size(message = "Observações do produto")
    @JsonProperty("observacoes")
    public String observacoes;

    @Max(value = 1, message = "Tipo de produção do produto (T para Terceiros ou P para Própria)")
    @JsonProperty("producao")
    public String producao;

    @Size(message = "Unidade de medida do produto (Metros, Centímetros ou Milímetro)")
    @JsonProperty("unidadeMedida")
    public String unidadeMedida;

    @Max(value = 1, message = "Data de validade do produto")
    @DateTimeFormat(pattern = "dd/mm/YYYY")
    @JsonProperty("dataValidade")
    public String dataValidade;

    @Size(message = "Descrição do fornecedor")
    @JsonProperty("descricaoFornecedor")
    public String descricaoFornecedor;

    @Max(value = 11, message = "Id do fornecedor (pode ser obtido no GET de contatos)")
    @JsonProperty("idFabricante")
    public BigDecimal idFabricante;

    @Size(message = "Código do produto no fornecedor")
    @JsonProperty("codigoFabricante")
    public String codigoFabricante;

    @Max(value = 14, message = "GTIN / EAN do produto")
    @JsonProperty("gtin")
    public String gtin;

    @Max(value = 14, message = "GTIN / EAN tributário da menor unidade comercializada")
    @JsonProperty("gtinEmbalagem")
    public String gtinEmbalagem;

    @Max(value = 15, message = "Largura do produto com embalagem")
    @JsonProperty("largura")
    public String largura;

    @Max(value = 15, message = "Altura do produto com embalagem")
    @JsonProperty("altura")
    public String altura;

    @Max(value = 15, message = "Profundidade do produto com embalagem")
    @JsonProperty("profundidade")
    public String profundidade;

    @Digits(integer = 11, fraction = 2)
    @Size(message = "Estoque mínimo do produto")
    @JsonProperty("estoqueMinimo")
    public BigDecimal estoqueMinimo;

    @Digits(integer = 11, fraction = 2)
    @Size(message = "Estoque máximo do produto")
    @JsonProperty("estoqueMaximo")
    public BigDecimal estoqueMaximo;

    @JsonProperty("estoque")
    public Integer estoque;

    @Digits(integer = 11, fraction = 2)
    @Size(message = "Quantidade de itens por caixa")
    @JsonProperty("itensPorCaixa")
    public BigDecimal itensPorCaixa;

    @Max(value = 2, message = "Quantidade de volumes do produto")
    @JsonProperty("volumes")
    public BigDecimal volumes;

    @Max(value = 100, message = "Url do vídeo do produto, utilizado na exportação do produto para lojas virtuais")
    @JsonProperty("urlVideo")
    public String urlVideo;

    @Max(value = 50, message = "Localização do produto")
    @JsonProperty("localizacao")
    public String localizacao;

    @Max(value = 4, message = "Quantidade de dias para o processo de distribuição em que a mercadoria recebida é redirecionada ao consumidor final sem uma armazenagem prévia.")
    @JsonProperty("crossdocking")
    public BigDecimal crossdocking;

    @Max(value = 3, message = "Garantia do produto, deve ser informada em meses.")
    @JsonProperty("garantia")
    public Integer garantia;

    @Max(value = 2, message = "Código do tipo do item no SPED")
    @JsonProperty("spedTipoItem")
    public Integer spedTipoItem;

    @Size(max = 11, message = "Quantidade de itens por caixa")
    @JsonProperty("idCategoria")
    public BigDecimal idCategoria;

//    @JsonProperty("deposito")
//    public DepositoRequest deposito;
//
//    @JsonProperty("variacoes")
//    public VariacoesRequest variacoes;
//
//    @JsonProperty("imagens")
//    public ArrayList<ImagemRequest> imagens;
//
//    @JsonProperty("camposCustomizados")
//    public CamposCustomizadosRequest camposCustomizados;
//
//    @JsonProperty("estrutura")
//    public EstruturaRequest estrutura;
}