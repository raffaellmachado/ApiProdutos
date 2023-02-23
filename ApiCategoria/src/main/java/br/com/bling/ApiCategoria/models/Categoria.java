package br.com.bling.ApiCategoria.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Categoria {

    public String id;
    public String descricao;
    public int idCategoriaPai;
}