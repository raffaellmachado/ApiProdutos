package br.com.bling.ApiContatos.controllers.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class RetornoResponse {
    @JsonProperty("contatos")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<Contatos> contatos;

    @Data
    public static class Contatos {
        @JsonProperty("contato")
        @JsonIgnoreProperties(ignoreUnknown = true)
        public ContatoResponse contato;
    }

}
