package br.com.bling.ApiContatos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Retorno {

    public ArrayList<Contato> contatos;

    @Data
    public static class Contato {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public Contato2 contato;
    }

}
