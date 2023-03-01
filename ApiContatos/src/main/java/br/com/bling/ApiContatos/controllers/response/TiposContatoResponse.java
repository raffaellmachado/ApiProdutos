package br.com.bling.ApiContatos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TiposContatoResponse {
    @JsonProperty("tipoContato")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public String tipoContato;
}
