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
public class ParcelasResponse {

    @JsonProperty("parcela")
    public ParcelaResponse parcela;

}
