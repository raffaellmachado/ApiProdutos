package br.com.bling.ApiCategorias.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoriaResponse {
    @JsonProperty("id")
    public String id;
    @JsonProperty("descricao")
    public String descricao;
    @JsonProperty("idCategoriaPai")
    public String idCategoriaPai;
}