package br.com.bling.ApiProdutos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VariacaoRequest {

    @NotEmpty
    @Max(value = 120, message = "nome da variação")
    @JsonProperty("nome")
    public String nome;

    @Max(value = 60, message = "código da variação")
    @JsonProperty("codigo")
    public String codigo;

    @Digits(integer = 17, fraction = 10) // DECIMAL(17,10)
    @Size(message = "valor unitário da variação")
    @JsonProperty("vlr_unit")
    public BigDecimal vlr_unit;

    @Max(value = 1, message = "Utilizar informações do produto pai (S para Sim ou N para Não)")
    @JsonProperty("clonarDadosPai")
    public String clonarDadosPai;

    @Digits(integer = 11, fraction = 4) //	DECIMAL(11,4)
    @Size(message = "estoque da variação")
    @JsonProperty("estoque")
    public BigDecimal estoque;

    @JsonProperty("deposito")
    public DepositoRequest deposito;
    @JsonProperty("un")
    public String un;
}
