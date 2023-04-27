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
public class DadosEtiquetaRequest {

    @JsonProperty("nome")
    public String nome;

    @JsonProperty("endereco")
    public String endereco;

    @JsonProperty("numero")
    public String numero;

    @JsonProperty("complemento")
    public String complemento;

    @JsonProperty("municipio")
    public String municipio;

    @JsonProperty("uf")
    public String uf;

    @JsonProperty("cep")
    public String cep;

    @JsonProperty("bairro")
    public String bairro;
}
