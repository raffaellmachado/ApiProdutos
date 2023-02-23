package br.com.bling.ApiProdutosFornecedores.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Produtofornecedores {
	@JsonIgnoreProperties(ignoreUnknown = true)
	public Object idProduto;
	@JsonIgnoreProperties(ignoreUnknown = true)
	public ArrayList<Fornecedore> fornecedores;

}


