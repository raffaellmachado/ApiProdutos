package br.com.bling.ApiContatos.controllers.response;

import br.com.bling.ApiContatos.models.Contato2;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class RetornoResponse {

    public ArrayList<Contato> contatos;

    @Data
    public static class Contato {

        public ContatoResponse contato;
    }

}
