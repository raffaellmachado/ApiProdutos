package br.com.bling.ApiProdutos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
public class VariacoesRequest {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public List<VariacaoRequest> variacao;
}
