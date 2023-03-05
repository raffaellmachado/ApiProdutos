package br.com.bling.ApiContatos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetornoResponse {
    @JsonProperty("contatos")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<Contatos> contatos;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Contatos {
        @JsonProperty("contato")
        @JsonIgnoreProperties(ignoreUnknown = true)
        public ContatoResponse contato;
    }

}
