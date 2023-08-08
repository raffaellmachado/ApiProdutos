package br.com.bling.ApiPedidos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
//@Entity
//@Table(name = "TB_CATEGORIA_REQUEST")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoriaRequest {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    public Long idBd;

    @JsonProperty("id")
    public Long id;

    @NotBlank(message = "Descrição da categoria não pode estar em branco")
    @Size(max = 50, message = "Descrição da categoria deve ter no máximo 50 caracteres")
    @JsonProperty("descricao")
    public String descricao;

    @Max(value =  99999999999L, message = "ID da categoria pai deve ser no máximo 11 dígitos")
    @JsonProperty("idCategoriaPai")
    public Long idCategoriaPai;

    @JsonIgnore
    public String flag;
}
