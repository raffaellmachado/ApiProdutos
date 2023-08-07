package br.com.bling.ApiContatos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
//@Entity
//@Table(name = "TB_CONTATO_RESPONSE")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContatoResponse {

//	@Id
	@JsonProperty("id")
	public Long id;

	@JsonProperty("codigo")
	public String codigo;

	@JsonProperty("nome")
	public String nome;

	@JsonProperty("fantasia")
	public String fantasia;

	@JsonProperty("tipo")
	public String tipo;

	@JsonProperty("cnpj")
	public String cnpj;

//	@JsonProperty("cpf_cnpj")
//	public String cpf_cnpj;

	@JsonProperty("ie_rg")
	public String ie_rg;

	@JsonProperty("endereco")
	public String endereco;

	@JsonProperty("numero")
	public String numero;

	@JsonProperty("bairro")
	public String bairro;

	@JsonProperty("cep")
	public String cep;

	@JsonProperty("cidade")
	public String cidade;

	@JsonProperty("complemento")
	public String complemento;

	@JsonProperty("uf")
	public String uf;

	@JsonProperty("fone")
	public String fone;

	@JsonProperty("email")
	public String email;

	@JsonProperty("situacao")
	public String situacao;

	@JsonProperty("contribuinte")
	public String contribuinte;

	@JsonProperty("site")
	public String site;

	@JsonProperty("celular")
	public String celular;

	@JsonProperty("dataAlteracao")
	public String dataAlteracao;

	@JsonProperty("dataInclusao")
	public String dataInclusao;

	@JsonProperty("sexo")
	public String sexo;

	@JsonProperty("clienteDesde")
	public String clienteDesde;

	@JsonProperty("limiteCredito")
	public String limiteCredito;

	@JsonProperty("dataNascimento")
	public String dataNascimento;

	@JsonProperty("informacoesContato")
	public String informacoesContato;

//	@ToString.Exclude
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contatoResponse")
	@JsonProperty("tiposContato")
//	@JsonManagedReference // Anotação para indicar que esta é a ponta "gerenciada" da relação
	public List<TiposContatoResponse> tiposContato = new ArrayList<>();

}
