package br.com.bling.ApiProdutos.controllers.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.Length;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ProdutoRequest {

    private String codigo;

    private String descricao;

    private String tipo;

    private String situacao;

    private String descricaoCurta;

    private String descricaoComplementar;

    private String un;

    private double vlr_unit;

    private double preco_custo;

    private double peso_bruto;

    private double peso_liq;

    private String class_fiscal;

    private String marca;

    private String cest;

    private String origem;

    private double idGrupoProduto;

    private String condicao;

    private String freteGratis;

    private String linkExterno;

    private String observacoes;

    private String producao;

    private String unidadeMedida;

    @DateTimeFormat(pattern = "dd/mm/YYYY")
    private Date dataValidade;

    private String descricaoFornecedor;

    private double idFabricante;

    private String codigoFabricante;

    private String estoque;

    private String deposito;

    class Deposito {
        private double id;
        private double estoque;
    }

    private String gtin;

    private String gtinEmbalagem;

    private String largura;

    private String altura;

    private String profundidade;

    private double estoqueMinimo;

    private double estoqueMaximo;

    private double itensPorCaixa;

    private double volumes;

    private String urlVideo;

    private String localizacao;

    private double crossdocking;

    private Integer garantia;

    private Integer spedTipoItem;

    class Imagens {
        private String url;
    }

    private String idCategoria;

    class Estrutura {
        private String tipoEstoque;
        private String lancarEstoque;

        class Componente {
            private String nome;
            private String codigo;
            private double quantidade;
        }
    }
}