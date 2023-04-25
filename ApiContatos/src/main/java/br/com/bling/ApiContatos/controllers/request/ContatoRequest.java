package br.com.bling.ApiContatos.controllers.request;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "TB_CONTATO_REQUEST")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContatoRequest {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    public Long idBd;

    @Id
    @JsonProperty("id")
    public Long id;

    @NotBlank
    @Size(max = 120, message = "Nome do contato")
    @JsonProperty("nome")
    public String nome;

    @Size(max = 30, message = "Nome fantasia do contato")
    @JsonProperty("fantasia")
    public String fantasia;

    @NotBlank
    @Size(max = 1, message = "Tipo do contato")
    @JsonProperty("tipoPessoa")
    public String tipoPessoa;

    @NotNull
    @Digits(integer = 1, fraction = 0, message = "1 - Contribuinte do ICMS, 2 - Contribuinte isento do ICMS ou 9 - Não contribuinte")
    @JsonProperty("contribuinte")
    public Integer contribuinte;

    @NotBlank(message = "CPF ou CNPJ do contato não pode estar em branco")
    @Size(max = 18, message = "CPF ou CNPJ do contato deve ter no máximo 18 caracteres")
    @JsonProperty("cpf_cnpj")
    public String cpf_cnpj;


//    @CNPJ
//    @CPF
//    @NotBlank
//    @Size(max = 18, message = "CPF ou CNPJ do contato")
//    @JsonProperty("cnpj")
//    public String cnpj;

    @Size(max = 18, message = "RG ou Inscrição Estadual do cliente")
    @JsonProperty("ie_rg")
    public String ie_rg;

    @Size(max = 50, message = "Endereço do Cliente")
    @JsonProperty("endereco")
    public String endereco;

    @Size(max = 10, message = "Número do endereço do cliente")
    @JsonProperty("numero")
    public String numero;

    @Size(max = 100, message = "Complemento do endereço do cliente")
    @JsonProperty("complemento")
    public String complemento;

    @Size(max = 30, message = "Bairro do cliente")
    @JsonProperty("bairro")
    public String bairro;

    @Size(max = 10, message = "CEP do cliente")
    @JsonProperty("cep")
    public String cep;

    @Size(max = 30, message = "Cidade do cliente")
    @JsonProperty("cidade")
    public String cidade;

    @Size(max = 2, message = "Sigla do estado do cliente")
    @JsonProperty("uf")
    public String uf;

    @Size(max = 40, message = "Telefone do cliente")
    @JsonProperty("fone")
    public String fone;

    @Size(max = 40, message = "Celular do cliente")
    @JsonProperty("celular")
    public String celular;

    @Size(max = 100, message = "E-mail do cliente")
    @JsonProperty("email")
    public String email;

    @Size(max = 80, message = "E-mail para envio da NFe")
    @JsonProperty("emailNfe")
    public String emailNfe;

    @Size(max = 100, message = "Informações do contato")
    @JsonProperty("informacaoContato")
    public String informacaoContato;

    @Digits(integer = 11, fraction = 2, message = "O limite de crédito deve ter no máximo 11 dígitos inteiros e 2 decimais")
    @DecimalMax(value = "9999999.99", message = "O limite de crédito deve ser no máximo 9999999.99")
    @DecimalMin(value = "0.00", message = "O limite de crédito deve ser no mínimo 0.00")
    @Column(precision = 11, scale = 2)
    @JsonProperty("limiteCredito")
    public BigDecimal limiteCredito;

    @Size(max = 50, message = "País de origem do cliente estrangeiro")
    @JsonProperty("paisOrigem")
    public String paisOrigem;

    @Size(max = 15, message = "Código do contato")
    @JsonProperty("codigo")
    public String codigo;

    @Size(max = 40, message = "site do contato")
    @JsonProperty("site")
    public String site;

    @JsonProperty("obs")
    public String obs;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contatoRequest")
    @JsonProperty("tiposContato")
    @JsonManagedReference // Anotação para indicar que esta é a ponta "gerenciada" da relação
    public List<TiposContatoRequest> tiposContato = new ArrayList<>();

    @JsonIgnore
    public String flag;
}