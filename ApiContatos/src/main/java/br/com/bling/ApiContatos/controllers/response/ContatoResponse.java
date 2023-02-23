package br.com.bling.ApiContatos.controllers.response;

import br.com.bling.ApiContatos.models.TiposContato;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class ContatoResponse {
    public String nome;
    public String fantasia;
    public String tipoPessoa;
    public int contribuinte;
    public String cpf_cnpj;
    public String ie_rg;
    public String endereco;
    public int numero;
    public String complemento;
    public String bairro;
    public String cep;
    public String cidade;
    public String uf;
    public String fone;
    public String celular;
    public String email;
    public String emailNfe;
    public String informacaoContato;
    public double limiteCredito;
    public TiposContato tipos_contatos;
}
