package br.com.bling.ApiCategoria.controllers.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Data
public class CategoriaRequest {

    @Max(value = 50, message = "Descrição da categoria")
    public String descricao;
    @Max(value =  11, message = "ID da categoria pai")
    public int idCategoriaPai = 0;

}
