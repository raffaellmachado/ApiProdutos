package br.com.bling.ApiProdutos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class Resposta {
	@JsonProperty("retorno")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private Object retorno;

	class Retorno{
		@JsonProperty("retorno")
		@JsonIgnoreProperties(ignoreUnknown = true)
		private List<Produto> produtos;
		}
	}
