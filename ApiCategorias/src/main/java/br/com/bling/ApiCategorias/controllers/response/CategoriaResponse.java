package br.com.bling.ApiCategorias.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_CATEGORIA")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoriaResponse {

    @Id
    @JsonProperty("id")
    public String id;

    @JsonProperty("descricao")
    public String descricao;

    @JsonProperty("idCategoriaPai")
    public String idCategoriaPai;
}