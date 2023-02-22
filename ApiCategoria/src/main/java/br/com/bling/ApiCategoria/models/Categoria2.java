package br.com.bling.ApiCategoria.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Categoria2 {

    @JsonProperty("id")
    public String id;
    @JsonProperty("descricao")
    public String descricao;
    @JsonProperty("idCategoriaPai")
    public int idCategoriaPai;
}