package br.com.bling.ApiCategoria.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Retorno {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<Categorias> categorias;

    @Data
    public static class Categorias {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public Categoria categoria;
    }
}