package br.com.bling.ApiProdutos.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class ProdutoLoja {

    @JsonProperty("preco")
    private Preco preco;

    @JsonProperty("categoria")
    private List<Categoria> categoria;
}