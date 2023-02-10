package br.com.bling.ApiProdutos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//@Entity
public class Preco {

	@JsonProperty("preco")
	private double preco;
	
	@JsonProperty("precoPromocional")
    private double precoPromocional;

	/*
    public Preco(double preco, double precoPromocional) {
		this.preco = preco;
		this.precoPromocional = precoPromocional;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public double getPrecoPromocional() {
		return precoPromocional;
	}

	public void setPrecoPromocional(double precoPromocional) {
		this.precoPromocional = precoPromocional;
	}
    */
}
