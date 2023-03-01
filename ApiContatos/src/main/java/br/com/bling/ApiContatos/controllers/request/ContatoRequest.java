package br.com.bling.ApiContatos.controllers.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContatoRequest {

    public String id;
    @NotEmpty
    @Max(value = 120, message = "Nome do contato")
    public String nome;
    @Max(value = 30, message = "Nome fantasia do contato")
    public String fantasia;
    @NotEmpty
    @Max(value = 1, message = "Tipo do contato")
    public String tipoPessoa = "F";
    @NotEmpty
    @Max(value = 1, message = "1 - Contribuinte do ICMS, 2 - Contribuinte isento do ICMS ou 9 - Não contribuinte")
    public String contribuinte = "9";
    @CNPJ
    @CPF
    @NotEmpty
    @Max(value = 18, message = "CPF ou CNPJ do contato")
    public String cpf_cnpj;
    @CNPJ
    @CPF
    @NotEmpty
    @Max(value = 18, message = "CPF ou CNPJ do contato")
    public String cnpj;
    @Max(value = 18, message = "RG ou Inscrição Estadual do cliente")
    public String ie_rg;
    @Max(value = 50, message = "Endereço do Cliente")
    public String endereco;
    @Max(value = 10, message = "Número do endereço do cliente")
    public String numero;
    @Max(value = 100, message = "Complemento do endereço do cliente")
    public String complemento;
    @Max(value = 30, message = "Bairro do cliente")
    public String bairro;
    @Max(value = 10, message = "CEP do cliente")
    public String cep;
    @Max(value = 30, message = "Cidade do cliente")
    public String cidade;
    @Max(value = 2, message = "Sigla do estado do cliente")
    public String uf;
    @Max(value = 40, message = "Telefone do cliente")
    public String fone;
    @Max(value = 40, message = "Celular do cliente")
    public String celular;
    @Max(value = 100, message = "E-mail do cliente")
    public String email;
    @Max(value = 80, message = "E-mail para envio da NFe")
    public String emailNfe;
    @Max(value = 100, message = "Informações do contato")
    public String informacaoContato;
    @Digits(integer = 11, fraction = 2)
    @Max(value = 50, message = "Limite de credito do cliente")
    public String limiteCredito;
    @Max(value = 50, message = "País de origem do cliente estrangeiro")
    public String paisOrigem;
    @Max(value = 15, message = "Código do contato")
    public String codigo;
    @Max(value = 40, message = "site do contato")
    public String site;
    @Max(value = 100, message = "Informações do contato")
    public String obs;
    public Object TiposContatoResponse;
}