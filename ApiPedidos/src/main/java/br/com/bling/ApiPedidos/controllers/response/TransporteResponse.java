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
public class TransporteResponse {

    @JsonProperty("tipo_frete")
    public String tipo_frete;

    @JsonProperty("qtde_volumes")
    public String qtde_volumes;

    @JsonProperty("peso_bruto")
    public String peso_bruto;

}
