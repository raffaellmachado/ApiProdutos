package br.com.bling.ApiProdutos.controllers.request;

import lombok.Data;


@Data
public class PrecoRequest {
	public double preco;
	public double precoPromocional;
}
