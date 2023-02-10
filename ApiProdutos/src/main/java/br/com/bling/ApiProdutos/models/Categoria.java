package br.com.bling.ApiProdutos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//@Entity
public class Categoria {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("descricao")
    private String descricao;
	
	@JsonProperty("idCategoriaPai")
    private String idCategoriaPai;

/*
    public Categoria(String id, String descricao, String idCategoriaPai) {

		this.id = id;
		this.descricao = descricao;
		this.idCategoriaPai = idCategoriaPai;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdCategoriaPai() {
		return idCategoriaPai;
	}

	public void setIdCategoriaPai(String idCategoriaPai) {
		this.idCategoriaPai = idCategoriaPai;
	}
*/
}

