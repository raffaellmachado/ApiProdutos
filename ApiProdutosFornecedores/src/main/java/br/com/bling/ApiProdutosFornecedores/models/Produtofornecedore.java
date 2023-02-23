package br.com.bling.ApiProdutosFornecedores.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
@Data
public class Produtofornecedore {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Object idProduto;
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ArrayList<Fornecedore> fornecedores;
    public class Fornecedore {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public Produtofornecedores produtoFornecedor;
    }

}
