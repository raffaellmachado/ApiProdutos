package br.com.bling.ApiPedidos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IntermediadorRequest {

    @JsonProperty("cnpj")
    public String cnpj;

    @JsonProperty("nomeUsuario")
    public String nomeUsuario;

    @JsonProperty("cnpjInstituicaoPagamento")
    public String cnpjInstituicaoPagamento;
}
