package br.com.bling.ApiContatos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TiposContatosRequest {
    @JsonProperty("tipoContato")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public TipoContatoRequest tipoContato;
}
