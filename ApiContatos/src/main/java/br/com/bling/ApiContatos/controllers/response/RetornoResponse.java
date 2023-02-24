package br.com.bling.ApiContatos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class RetornoResponse {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<Contatos> contatos;

    @Data
    public static class Contatos {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public ContatoResponse contato;
    }

}
