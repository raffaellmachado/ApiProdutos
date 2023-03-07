package br.com.bling.ApiProdutos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepositoRequest {

    @Max(value = 11, message = "Código identificador do depósito")
    public BigDecimal id;
    @Digits(integer = 11, fraction = 4) //	DECIMAL(11,4)
    @Size(message = "Estoque atual da variação no depósito")
    public BigDecimal estoque;
}

