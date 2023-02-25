package br.com.bling.ApiProdutos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class VariacoResponse {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public VariacaoResponse variacao;
}
