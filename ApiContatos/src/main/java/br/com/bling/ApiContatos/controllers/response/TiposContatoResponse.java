package br.com.bling.ApiContatos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class TiposContatoResponse {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public TipoContatoResponse tipoContato;
}
