package br.com.bling.ApiProdutosFornecedores.controllers.response;

import br.com.bling.ApiProdutosFornecedores.controllers.request.ProdutoFornecedorResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Retorno {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<Produtosfornecedores> produtosfornecedores;

    @Data
    public static class Produtosfornecedores {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public Produtofornecedor produtofornecedores;
    }
}
