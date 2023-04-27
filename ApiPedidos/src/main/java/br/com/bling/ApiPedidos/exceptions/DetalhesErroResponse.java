package br.com.bling.ApiPedidos.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetalhesErroResponse {

    @JsonProperty("cod")
    private int codigo;

    @JsonProperty("msg")
    private String mensagem;

    public DetalhesErroResponse(int codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }
}