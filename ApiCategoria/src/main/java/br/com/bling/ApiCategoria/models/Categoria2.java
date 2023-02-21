package br.com.bling.ApiCategoria.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Categoria2 {

    public String id;
    @NotBlank
    public String descricao;
    public String idCategoriaPai;

}

