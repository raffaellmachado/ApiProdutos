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
public class FormaPagamentoRequest {

    @JsonProperty("id")
    public String id;

    @JsonProperty("descricao")
    public String descricao;

    @JsonProperty("codigoFiscal")
    public BigDecimal codigoFiscal;

    @JsonProperty("condicao")
    public String condicao;

    @JsonProperty("destino")
    public BigDecimal destino;

    @JsonProperty("padrao")
    public BigDecimal padrao;

    @JsonProperty("situacao")
    public BigDecimal situacao;

    @JsonProperty("dadoscartao")
    public DadosCartaoRequest dadoscartao;

    @JsonProperty("dadostaxas")
    public DadosTaxasRequest dadostaxas;
}
