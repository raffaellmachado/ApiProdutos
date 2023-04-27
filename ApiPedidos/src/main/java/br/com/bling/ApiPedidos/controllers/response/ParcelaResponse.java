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
public class ParcelaResponse {

    @JsonProperty("idLancamento")
    public String idLancamento;

    @JsonProperty("valor")
    public String valor;

    @JsonProperty("dataVencimento")
    public String dataVencimento;

    @JsonProperty("obs")
    public String obs;

    @JsonProperty("destino")
    public String destino;

    @JsonProperty("forma_pagamento")
    public FormaPagamentoResponse forma_pagamento;
}
