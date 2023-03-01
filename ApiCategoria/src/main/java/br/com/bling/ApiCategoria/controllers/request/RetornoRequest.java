package br.com.bling.ApiCategoria.controllers.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class RetornoRequest {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public ArrayList<ArrayList<Categorias>> categorias;

    @Data
    public static class Categorias {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public CategoriaRequest categoria;
    }
}