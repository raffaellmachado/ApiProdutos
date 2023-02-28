package br.com.bling.ApiContatos.controllers.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class RetornoRequest {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public Contatos contatos;

    @Data
    public static class Contatos {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public ContatoRequest contato;
    }

}
