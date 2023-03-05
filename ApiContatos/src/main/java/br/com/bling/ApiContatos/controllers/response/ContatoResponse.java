package br.com.bling.ApiContatos.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContatoResponse implements Serializable {

	public String id;
	public String codigo;
	public String nome;
	public String fantasia;
	public String tipo;
	public String cnpj;
	public String cpf_cnpj;
	public String ie_rg;
	public String endereco;
	public String numero;
	public String bairro;
	public String cep;
	public String cidade;
	public String complemento;
	public String uf;
	public String fone;
	public String email;
	public String situacao;
	public String contribuinte;
	public String site;
	public String celular;
	public String dataAlteracao;
	public String dataInclusao;
	public String sexo;
	public String clienteDesde;
	public String limiteCredito;
	public String dataNascimento;
	public String informacoesContato;
	@JsonProperty("tiposContato")
	public List<TiposContatoResponse> tiposContato;
}