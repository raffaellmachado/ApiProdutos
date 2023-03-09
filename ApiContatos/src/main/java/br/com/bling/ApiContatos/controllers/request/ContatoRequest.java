package br.com.bling.ApiContatos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContatoRequest {

    @JsonProperty("id")
    public String id;

    @NotEmpty
    @Max(value = 120, message = "Nome do contato")
    @JsonProperty("nome")
    public String nome;

    @Max(value = 30, message = "Nome fantasia do contato")
    @JsonProperty("fantasia")
    public String fantasia;

    @NotEmpty
    @Max(value = 1, message = "Tipo do contato")
    @JsonProperty("tipoPessoa")
    public String tipoPessoa;

    @NotEmpty
    @Max(value = 1, message = "1 - Contribuinte do ICMS, 2 - Contribuinte isento do ICMS ou 9 - Não contribuinte")
    @JsonProperty("contribuinte")
    public Integer contribuinte;

    @CNPJ
    @CPF
    @NotEmpty
    @Max(value = 18, message = "CPF ou CNPJ do contato")
    @JsonProperty("cpf_cnpj")
    public String cpf_cnpj;

    @CNPJ
    @CPF
    @NotEmpty
    @Max(value = 18, message = "CPF ou CNPJ do contato")
    @JsonProperty("cnpj")
    public String cnpj;

    @Max(value = 18, message = "RG ou Inscrição Estadual do cliente")
    @JsonProperty("ie_rg")
    public String ie_rg;

    @Max(value = 50, message = "Endereço do Cliente")
    @JsonProperty("endereco")
    public String endereco;

    @Max(value = 10, message = "Número do endereço do cliente")
    @JsonProperty("numero")
    public String numero;

    @Max(value = 100, message = "Complemento do endereço do cliente")
    @JsonProperty("complemento")
    public String complemento;

    @Max(value = 30, message = "Bairro do cliente")
    @JsonProperty("bairro")
    public String bairro;

    @Max(value = 10, message = "CEP do cliente")
    @JsonProperty("cep")
    public String cep;

    @Max(value = 30, message = "Cidade do cliente")
    @JsonProperty("cidade")
    public String cidade;

    @Max(value = 2, message = "Sigla do estado do cliente")
    @JsonProperty("uf")
    public String uf;

    @Max(value = 40, message = "Telefone do cliente")
    @JsonProperty("fone")
    public String fone;

    @Max(value = 40, message = "Celular do cliente")
    @JsonProperty("celular")
    public String celular;

    @Max(value = 100, message = "E-mail do cliente")
    @JsonProperty("email")
    public String email;

    @Max(value = 80, message = "E-mail para envio da NFe")
    @JsonProperty("emailNfe")
    public String emailNfe;

    @Max(value = 100, message = "Informações do contato")
    @JsonProperty("informacaoContato")
    public String informacaoContato;

    @Digits(integer = 11, fraction = 2)
    @Max(value = 50, message = "Limite de credito do cliente")
    @JsonProperty("limiteCredito")
    public BigDecimal limiteCredito;

    @Max(value = 50, message = "País de origem do cliente estrangeiro")
    @JsonProperty("paisOrigem")
    public String paisOrigem;

    @Max(value = 15, message = "Código do contato")
    @JsonProperty("codigo")
    public String codigo;

    @Max(value = 40, message = "site do contato")
    @JsonProperty("site")
    public String site;

    @JsonProperty("obs")
    public String obs;
    @JsonProperty("tiposContato")
    public List<TiposContatosRequest> tiposContato;
}