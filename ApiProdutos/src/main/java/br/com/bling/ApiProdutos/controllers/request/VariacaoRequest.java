package br.com.bling.ApiProdutos.controllers.request;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class VariacaoRequest {
    @NotEmpty
    @Max(value = 120, message = "nome da variação")
    public String nome;
    @Max(value = 60, message = "código da variação")
    public String codigo;
    @Digits(integer = 17, fraction = 10) // DECIMAL(17,10)
    @Size(message = "valor unitário da variação")
    public BigDecimal vlr_unit;
    @Max(value = 1, message = "Utilizar informações do produto pai (S para Sim ou N para Não)")
    public String clonarDadosPai;
    @Digits(integer = 11, fraction = 4) //	DECIMAL(11,4)
    @Size(message = "estoque da variação")
    public BigDecimal estoque;
    public DepositoRequest deposito;
    public String un;
}
