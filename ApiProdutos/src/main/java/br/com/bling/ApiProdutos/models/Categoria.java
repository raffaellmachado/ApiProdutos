package br.com.bling.ApiProdutos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class Categoria {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("descricao")
    private String descricao;

	@JsonProperty("idCategoriaPai")
    private String idCategoriaPai;

}

