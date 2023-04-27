package br.com.bling.ApiPedidos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemRequest {

    @JsonProperty("codigo")
    public String codigo;

    @JsonProperty("descricao")
    public String descricao;

    @JsonProperty("un")
    public String un;

    @JsonProperty("qtde")
    public BigDecimal qtde;

    @JsonProperty("vlr_unit")
    public BigDecimal vlr_unit;

    @JsonProperty("vlr_desconto")
    public BigDecimal vlr_desconto;
}
