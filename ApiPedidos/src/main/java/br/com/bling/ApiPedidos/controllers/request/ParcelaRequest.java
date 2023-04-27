package br.com.bling.ApiPedidos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParcelaRequest {

    @JsonProperty("dias")
    public BigInteger dias;

    @JsonProperty("data")
    public Date data;

    @JsonProperty("vlr")
    public BigDecimal vlr;

    @JsonProperty("obs")
    public String obs;

    @JsonProperty("forma_pagamento")
    public FormaPagamentoRequest forma_pagamento;
}
