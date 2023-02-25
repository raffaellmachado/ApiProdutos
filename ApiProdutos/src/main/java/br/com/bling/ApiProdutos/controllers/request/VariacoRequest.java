package br.com.bling.ApiProdutos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class VariacoRequest {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public VariacaoRequest variacao;
}
