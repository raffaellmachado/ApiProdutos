package br.com.bling.ApiProdutos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Categoria2 {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("descricao")
    private String descricao;

	@JsonProperty("idCategoriaPai")
    private String idCategoriaPai;

}

