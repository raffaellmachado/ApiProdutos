package br.com.bling.ApiContatos.controllers.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Max;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TipoContatoRequest {
    @Max(value = 50, message = "Nome do tipo do contato")
    public String descricao;
}
