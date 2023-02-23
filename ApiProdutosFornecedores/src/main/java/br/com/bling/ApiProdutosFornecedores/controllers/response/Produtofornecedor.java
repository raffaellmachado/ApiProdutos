package br.com.bling.ApiProdutosFornecedores.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Produtofornecedor {
	@JsonIgnoreProperties(ignoreUnknown = true)
	public Object idProduto;
	@JsonIgnoreProperties(ignoreUnknown = true)
	public ArrayList<Fornecedore> fornecedores;

}


