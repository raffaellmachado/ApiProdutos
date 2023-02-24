package br.com.bling.ApiProdutosFornecedores.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ProdutofornecedorResponse {
	@JsonIgnoreProperties(ignoreUnknown = true)
	public String idProduto;
	@JsonIgnoreProperties(ignoreUnknown = true)
	public ArrayList<FornecedoreResponse> fornecedores;

}


