package br.com.bling.ApiCategoria.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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