package br.com.bling.ApiCategoria.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoriaRequest {

    public String id;
    @NotEmpty(message = "Campo Obrigatorio")
    @Max(value = 50, message = "Descrição da categoria")
    public String descricao;
    @Max(value =  11, message = "ID da categoria pai")
    public String idCategoriaPai = "0";
}
