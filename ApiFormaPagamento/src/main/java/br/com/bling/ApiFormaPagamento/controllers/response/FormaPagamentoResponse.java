package br.com.bling.ApiFormaPagamento.controllers.response;

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
public class FormaPagamentoResponse {

    @JsonProperty("id")
    public String id;

    @JsonProperty("descricao")
    public String descricao;

    @JsonProperty("codigoFiscal")
    public String codigoFiscal;

    @JsonProperty("padrao")
    public String padrao;

    @JsonProperty("situacao")
    public String situacao;

    @JsonProperty("fixa")
    public String fixa;
}
