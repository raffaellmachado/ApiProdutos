package br.com.bling.ApiContatos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class RetornoRequest {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Contatos contatos;

    @Data
    public static class Contatos {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public ContatoRequest contato;
    }

}
