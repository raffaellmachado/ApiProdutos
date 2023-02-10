package br.com.bling.ApiProdutos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//@Entity
public class Imagem {
	
	@JsonProperty("link")
    private String link;
	
	@JsonProperty("validade")
    private String validade;
	
	@JsonProperty("tipoArmazenamento")
    private String tipoArmazenamento;
	
/*
    public Imagem(String link, String validade, String tipoArmazenamento) {
    	
		this.link = link;
		this.validade = validade;
		this.tipoArmazenamento = tipoArmazenamento;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public String getValidade() {
		return validade;
	}


	public void setValidade(String validade) {
		this.validade = validade;
	}


	public String getTipoArmazenamento() {
		return tipoArmazenamento;
	}


	public void setTipoArmazenamento(String tipoArmazenamento) {
		this.tipoArmazenamento = tipoArmazenamento;
	}

*/
}
