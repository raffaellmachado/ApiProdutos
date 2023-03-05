package br.com.bling.ApiContatos.controllers.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetornoRequest {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public ArrayList<ArrayList<Contatos>> contatos;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Contatos {

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public ContatoRequest contato;
    }

}
