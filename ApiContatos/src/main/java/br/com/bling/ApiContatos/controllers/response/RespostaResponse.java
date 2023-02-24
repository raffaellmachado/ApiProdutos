package br.com.bling.ApiContatos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RespostaResponse {

	@JsonProperty("retorno")
	@JsonIgnoreProperties(ignoreUnknown = true)
	public RetornoResponse retorno;
}
