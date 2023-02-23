package br.com.bling.ApiContatos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Retorno {

    public ArrayList<Contato> contatos;

    @Data
    public static class Contato {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public Contato2 contato;
    }

}
