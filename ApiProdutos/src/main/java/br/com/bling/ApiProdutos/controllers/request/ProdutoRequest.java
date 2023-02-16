package br.com.bling.ApiProdutos.controllers.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ProdutoRequest {

    @Size(max = 60, message = "Código do produto")
    private String codigo;
    @Size(max = 4, message = "Código de item de serviço (06, 21 e 22)")
    private int codigoItem;
    @Size(max = 120, message = "Descrição do produto")
    private String descricao;
    @Size(message = "Tipo ('S' para serviço, 'P' para produto e 'N' para serviço de nota 06, 21 e 22)")
    private String tipo = "P";
    @Size(message = "Situação do produto (Ativo ou Inativo)")
    private String situacao = "Ativo";
    @Size(message = "Descrição curta do produto")
    private String descricaoCurta;
    @Size(message = "Descrição complementar do produto")
    private String descricaoComplementar;
    @Size(max = 6, message = "Tipo de unidade do produto")
    private String un;
    @Digits(integer = 17, fraction = 10)
    @Size(message = "Valor unitário do produto")
    private double vlr_unit;
    @Digits(integer = 17, fraction = 10)
    @Size(message = "Preço de custo do produto")
    private double preco_custo;
    @Digits(integer = 11, fraction = 3)
    @Size(message = "Peso bruto do produto")
    private double peso_bruto;
    @Digits(integer = 11, fraction = 3)
    @Size(message = "Peso líquido do produto")
    private double peso_liq;
    @Size(max = 10, message = "NCM do produto")
    private String class_fiscal;
    @Size(max = 40, message = "Marca do produto")
    private String marca;
    @Size(max = 7, message = "CEST do produto")
    private String cest;
    @Size(max = 1, message = "Origem do produto")
    private String origem = "9";
    @Size(max = 11, message = "Identificador do grupo do produto")
    private double idGrupoProduto = 0;
    @Size(message = "Condição do produto (Não especificado, Novo, Recondicionado ou Usado)")
    private String condicao = "Não especificado";
    @Size(max = 1, message = "Frete grátis (S para Sim ou N para Não)")
    private String freteGratis = "N";
    @Size(max = 100, message = "Link do produto na loja virtual, marketplace, catálago etc.")
    private String linkExterno;
    @Size(message = "Observações do produto")
    private String observacoes;
    @Size(max = 1, message = "Tipo de produção do produto (T para Terceiros ou P para Própria)")
    private String producao = "P";
    @Size(message = "Unidade de medida do produto (Metros, Centímetros ou Milímetro)")
    private String unidadeMedida = "Centímetros";
    @Size(max = 1, message = "Data de validade do produto")
    @DateTimeFormat(pattern = "dd/mm/YYYY")
    private Date dataValidade;
    @Size(message = "Descrição do fornecedor")
    private String descricaoFornecedor;
    @Size(max = 11, message = "Id do fornecedor (pode ser obtido no GET de contatos)")
    private double idFabricante;
    @Size(message = "Código do produto no fornecedor")
    private String codigoFabricante;

    class Deposito {
        @Size(max = 11, message = "Código identificador do depósito")
        private double id;
        @Digits(integer = 11, fraction = 4)
        @Size(message = "Estoque atual do produto no depósito")
        private double estoque;
    }
    @Size(max = 14, message = "GTIN / EAN do produto")
    private String gtin;
    @Size(max = 14, message = "GTIN / EAN tributário da menor unidade comercializada")
    private String gtinEmbalagem;
    @Size(max = 15, message = "Largura do produto com embalagem")
    private String largura;
    @Size(max = 15, message = "Altura do produto com embalagem")
    private String altura;
    @Size(max = 15, message = "Profundidade do produto com embalagem")
    private String profundidade;
    @Digits(integer = 11, fraction = 2)
    @Size(message = "Estoque mínimo do produto")
    private double estoqueMinimo;
    @Digits(integer = 11, fraction = 2)
    @Size(message = "Estoque máximo do produto")
    private double estoqueMaximo;
    @Digits(integer = 11, fraction = 2)
    @Size(message = "Quantidade de itens por caixa")
    private double itensPorCaixa;
    @Size(max = 2, message = "Quantidade de volumes do produto")
    private double volumes = 0;
    @Size(max = 100, message = "Url do vídeo do produto, utilizado na exportação do produto para lojas virtuais")
    private String urlVideo;
    @Size(max = 50, message = "Localização do produto")
    private String localizacao;
    @Size(max = 4, message = "Quantidade de dias para o processo de distribuição em que a mercadoria recebida é redirecionada ao consumidor final sem uma armazenagem prévia.")
    private double crossdocking;
    @Size(max = 3, message = "Garantia do produto, deve ser informada em meses.")
    private Integer garantia;
    @Size(max = 2, message = "Código do tipo do item no SPED")
    private Integer spedTipoItem;

    class Variacoes {
        @Size(max = 120, message = "nome da variação")
        private String nome;
        @Size(max = 60, message = "código da variação")
        private String	codigo;
        @Digits(integer = 17, fraction = 10) // DECIMAL(17,10)
        @Size(message = "valor unitário da variação")
        private double	vlr_unit;
        @Size(max = 1, message = "Utilizar informações do produto pai (S para Sim ou N para Não)")
        private String clonarDadosPai = "N";
        @Digits(integer = 11, fraction = 4) //	DECIMAL(11,4)
        @Size(message = "estoque da variação")
        private double estoque;

        class Deposito {
            @Size(max = 11, message = "Código identificador do depósito")
            private double id;
            @Digits(integer = 11, fraction = 4) //	DECIMAL(11,4)
            @Size(message = "Estoque atual da variação no depósito")
            private double estoque;
        }
    }

    class Imagens {
        @Size(message = "URL da imagem")
        private String url;
    }
}