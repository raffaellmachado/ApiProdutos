package br.com.bling.ApiFormaPagamento.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
//@Entity
//@Table(name = "TB_CATEGORIA_RESPONSE")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosCartaoRequest {

    @JsonProperty("id")
    public String id;

    @JsonProperty("bandeira")
    public BigDecimal bandeira;

    @JsonProperty("tipointegracao")
    public BigDecimal tipointegracao;

    @JsonProperty("cnpjcredenciadora")
    public String cnpjcredenciadora;

    @JsonProperty("autoliquidacao")
    public BigDecimal autoliquidacao;


}
