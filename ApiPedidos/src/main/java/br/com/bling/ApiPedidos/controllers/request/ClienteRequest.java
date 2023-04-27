package br.com.bling.ApiPedidos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteRequest {

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("nome")
    public String nome;

    @JsonProperty("tipoPessoa")
    public String tipoPessoa;

    @JsonProperty("cpf_cnpj")
    public String cpf_cnpj;

    @JsonProperty("ie")
    public String ie;

    @JsonProperty("rg")
    public String rg;

    @JsonProperty("contribuinte")
    public String contribuinte;

    @JsonProperty("endereco")
    public String endereco;

    @JsonProperty("numero")
    public String numero;

    @JsonProperty("complemento")
    public String complemento;

    @JsonProperty("bairro")
    public String bairro;

    @JsonProperty("cep")
    public String cep;

    @JsonProperty("cidade")
    public String cidade;

    @JsonProperty("uf")
    public String uf;

    @JsonProperty("fone")
    public String fone;

    @JsonProperty("celular")
    public String celular;

    @JsonProperty("email")
    public String email;
}
