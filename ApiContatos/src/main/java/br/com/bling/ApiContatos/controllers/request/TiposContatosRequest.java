package br.com.bling.ApiContatos.controllers.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TiposContatosRequest {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<TipoContatoRequest> tipo_contato;
}
