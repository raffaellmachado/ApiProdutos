package br.com.bling.ApiProdutos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class Variaco {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Variacao variacao;
}
