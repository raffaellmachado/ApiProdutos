package br.com.bling.ApiProdutos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Deposito {

    @JsonProperty("id")
    private String id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("saldo")
    private String saldo;

}
