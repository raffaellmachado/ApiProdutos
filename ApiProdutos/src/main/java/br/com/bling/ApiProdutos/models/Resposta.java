package br.com.bling.ApiProdutos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Resposta {
	@JsonProperty("retorno")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)
	private Object retorno;

	@Data
	public static class Retorno{
		@JsonProperty("retorno")
		@JsonInclude(JsonInclude.Include.NON_NULL)
		@JsonIgnoreProperties(ignoreUnknown = true)
		private List<Produto> produtos;
		private List<Categoria> categorias;
		private List<Deposito> depositos;
		}
/*
	class RetornoCategoria{
		@JsonProperty("RetornoCategoria")
		@JsonIgnoreProperties(ignoreUnknown = true)
		private List<Categoria> categorias;
	}

	class RetornoDeposito{
		@JsonProperty("RetornoDeposito")
		@JsonIgnoreProperties(ignoreUnknown = true)
		private List<Deposito> depositos;
	}
*/
}
