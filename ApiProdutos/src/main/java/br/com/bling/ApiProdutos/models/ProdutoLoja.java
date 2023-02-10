package br.com.bling.ApiProdutos.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//@Entity
public class ProdutoLoja {
	
	@JsonProperty("preco")
    private Preco preco;
	
	@JsonProperty("categoria")
    private List<Categoria> categoria;
	
/*
    public ProdutoLoja(Preco preco, List<Categoria> categoria) {
		this.preco = preco;
		this.categoria = categoria;
	}


	public Preco getPreco() {
		return preco;
	}


	public void setPreco(Preco preco) {
		this.preco = preco;
	}


	public List<Categoria> getCategoria() {
		return categoria;
	}


	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}
*/
}
