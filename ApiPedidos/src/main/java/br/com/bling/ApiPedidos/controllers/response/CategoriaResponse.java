package br.com.bling.ApiPedidos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
//@Entity
//@Table(name = "TB_CATEGORIA_RESPONSE")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoriaResponse {

//    @Id
    @JsonProperty("id")
//    @Column(name = "ID")
    public Long id;

    @JsonProperty("descricao")
//    @Column(name = "DESCRICAO")
    public String descricao;

    @JsonProperty("idCategoriaPai")
//    @Column(name = "ID_CATEGORIA_PAI")
    public String idCategoriaPai;
}