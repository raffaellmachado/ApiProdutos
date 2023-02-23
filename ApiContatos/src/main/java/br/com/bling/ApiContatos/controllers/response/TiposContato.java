package br.com.bling.ApiContatos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class TiposContato {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public TipoContato tipoContato;
}
