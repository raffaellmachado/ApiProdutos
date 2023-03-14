package br.com.bling.ApiCategorias.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoriaRequest {

    @JsonProperty("id")
    public String id;
    @NotEmpty(message = "Campo Obrigatorio")
    @Max(value = 50, message = "Descrição da categoria")
    @JsonProperty("descricao")
    public String descricao;
    @Max(value =  11, message = "ID da categoria pai")
    @JsonProperty("idCategoriaPai")
    public Integer idCategoriaPai = 0;
}
