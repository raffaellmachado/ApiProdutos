package br.com.bling.ApiCategoria.controllers.request;

import br.com.bling.ApiCategoria.controllers.response.Categoria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class RetornoRequest {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<ArrayList<Categorias>> categorias;

    @Data
    public static class Categorias {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public Categoria categoria;
    }
}