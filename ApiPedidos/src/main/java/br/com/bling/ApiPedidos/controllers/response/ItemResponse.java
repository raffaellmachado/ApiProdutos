package br.com.bling.ApiPedidos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@Entity
//@Table(name = "TB_CATEGORIA_RESPONSE")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemResponse {

    @JsonProperty("codigo")
    public String codigo;

    @JsonProperty("descricao")
    public String descricao;

    @JsonProperty("quantidade")
    public String quantidade;

    @JsonProperty("valorunidade")
    public String valorunidade;

    @JsonProperty("precocusto")
    public String precocusto;

    @JsonProperty("descontoItem")
    public String descontoItem;

    @JsonProperty("un")
    public String un;

    @JsonProperty("pesoBruto")
    public String pesoBruto;

    @JsonProperty("largura")
    public String largura;

    @JsonProperty("altura")
    public String altura;

    @JsonProperty("profundidade")
    public String profundidade;

    @JsonProperty("descricaoDetalhada")
    public String descricaoDetalhada;

    @JsonProperty("unidadeMedida")
    public String unidadeMedida;

    @JsonProperty("gtin")
    public String gtin;
}
