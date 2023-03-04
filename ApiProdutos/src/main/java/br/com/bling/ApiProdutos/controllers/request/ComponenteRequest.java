package br.com.bling.ApiProdutos.controllers.request;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ComponenteRequest {
    @Size(max = 120, message = "valor unitário da variação")
    public String nome;
    @Size(max = 60, message = "valor unitário da variação")
    public String codigo;
    @Digits(integer = 11, fraction = 4)
    @Size(message = "valor unitário da variação")
    public BigDecimal quantidade;
}
