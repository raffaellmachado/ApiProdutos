package br.com.bling.ApiProdutosFornecedores.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_PRODUTO_FORNECEDOR_RESPONSE")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoFornecedoresResponse {

	@Id
	@Column(name = "id_produto")
	@JsonProperty("idProduto")
	public Long idProduto;

	@JsonProperty("fornecedores")
	public ArrayList<FornecedoresResponse> fornecedores;

}


