package br.com.bling.ApiProdutos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComponenteRequest {

    @Size(max = 120, message = "valor unitário da variação")
    public String nome;
    @Size(max = 60, message = "valor unitário da variação")
    public String codigo;
    @Digits(integer = 11, fraction = 4)
    @Size(message = "valor unitário da variação")
    public BigDecimal quantidade;
}