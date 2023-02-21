package br.com.bling.ApiCategoria.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Data
public  class Retorno {
    @JsonProperty("categorias")
    public List<Categoria> categorias;

    @Data
    public static class Categoria {
        public Categoria2 categoria;
    }
}
