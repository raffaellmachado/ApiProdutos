package br.com.bling.ApiCategoria.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoriaResponse {

    public String id;
    public String descricao;
    public int idCategoriaPai = 0;
}