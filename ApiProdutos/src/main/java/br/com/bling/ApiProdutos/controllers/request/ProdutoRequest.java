package br.com.bling.ApiProdutos.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequest {

    public String id;
    @Max(value = 60, message = "Código do produto")
    public String codigo;
    @Max(value = 4, message = "Código de item de serviço (06, 21 e 22)")
    public Integer codigoItem;
    @NotEmpty
    @Max(value = 120, message = "Descrição do produto")
    public String descricao;
    @Size(max = 1, message = "Tipo ('S' para serviço, 'P' para produto e 'N' para serviço de nota 06, 21 e 22)")
    public String tipo;
    @Size(message = "Situação do produto (Ativo ou Inativo)")
    public String situacao;
    @Size(message = "Descrição curta do produto")
    public String descricaoCurta;
    @Size(message = "Descrição complementar do produto")
    public String descricaoComplementar;
    @Max(value = 6, message = "Tipo de unidade do produto")
    public String un;
    @DecimalMin("0")
    @Digits(integer = 17, fraction = 10)
    @Size(message = "Valor unitário do produto")
    public BigDecimal vlr_unit;
    @Digits(integer = 17, fraction = 10)
    @Size(message = "Preço de custo do produto")
    public BigDecimal preco_custo;
    @Digits(integer = 11, fraction = 3)
    @Size(message = "Peso bruto do produto")
    public BigDecimal peso_bruto;
    @Digits(integer = 11, fraction = 3)
    @Size(message = "Peso líquido do produto")
    public BigDecimal peso_liq;
    @Max(value = 10, message = "NCM do produto")
    public String class_fiscal;
    @Max(value = 40, message = "Marca do produto")
    public String marca;
    @Max(value = 7, message = "CEST do produto")
    public String cest;
    @Max(value = 1, message = "Origem do produto")
    public String origem;
    @Max(value = 11, message = "Identificador do grupo do produto")
    public BigDecimal idGrupoProduto;
    @Size(message = "Condição do produto (Não especificado, Novo, Recondicionado ou Usado)")
    public String condicao = "Não especificado";
    @Max(value = 1, message = "Frete grátis (S para Sim ou N para Não)")
    public String freteGratis;
    @Max(value = 100, message = "Link do produto na loja virtual, marketplace, catálago etc.")
    public String linkExterno;
    @Size(message = "Observações do produto")
    public String observacoes;
    @Max(value = 1, message = "Tipo de produção do produto (T para Terceiros ou P para Própria)")
    public String producao;
    @Size(message = "Unidade de medida do produto (Metros, Centímetros ou Milímetro)")
    public String unidadeMedida;
    @Max(value = 1, message = "Data de validade do produto")
    @DateTimeFormat(pattern = "dd/mm/YYYY")
    public String dataValidade;
    @Size(message = "Descrição do fornecedor")
    public String descricaoFornecedor;
    @Max(value = 11, message = "Id do fornecedor (pode ser obtido no GET de contatos)")
    public BigDecimal idFabricante;
    @Size(message = "Código do produto no fornecedor")
    public String codigoFabricante;
    @Max(value = 14, message = "GTIN / EAN do produto")
    public String gtin;
    @Max(value = 14, message = "GTIN / EAN tributário da menor unidade comercializada")
    public String gtinEmbalagem;
    @Max(value = 15, message = "Largura do produto com embalagem")
    public String largura;
    @Max(value = 15, message = "Altura do produto com embalagem")
    public String altura;
    @Max(value = 15, message = "Profundidade do produto com embalagem")
    public String profundidade;
    @Digits(integer = 11, fraction = 2)
    @Size(message = "Estoque mínimo do produto")
    public BigDecimal estoqueMinimo;
    @Digits(integer = 11, fraction = 2)
    @Size(message = "Estoque máximo do produto")
    public BigDecimal estoqueMaximo;

    public Integer estoque;

    @Digits(integer = 11, fraction = 2)
    @Size(message = "Quantidade de itens por caixa")
    public BigDecimal itensPorCaixa;
    @Max(value = 2, message = "Quantidade de volumes do produto")
    public BigDecimal volumes;
    @Max(value = 100, message = "Url do vídeo do produto, utilizado na exportação do produto para lojas virtuais")
    public String urlVideo;
    @Max(value = 50, message = "Localização do produto")
    public String localizacao;
    @Max(value = 4, message = "Quantidade de dias para o processo de distribuição em que a mercadoria recebida é redirecionada ao consumidor final sem uma armazenagem prévia.")
    public BigDecimal crossdocking;
    @Max(value = 3, message = "Garantia do produto, deve ser informada em meses.")
    public Integer garantia;
    @Max(value = 2, message = "Código do tipo do item no SPED")
    public Integer spedTipoItem;
    @Size(max = 11, message = "Quantidade de itens por caixa")
    public BigDecimal idCategoria;
    public DepositoRequest deposito;
    public VariacoesRequest variacoes;
    public ArrayList<ImagemRequest> imagens;
    public CamposCustomizadosRequest camposCustomizados;
    public EstruturaRequest estrutura;
}