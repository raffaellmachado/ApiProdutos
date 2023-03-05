package br.com.bling.ApiProdutos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariacoesRequest {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public List<VariacaoRequest> variacao;
}
