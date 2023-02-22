package br.com.bling.ApiProdutos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class Deposito {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Deposito deposito;

}
