package br.com.bling.ApiProdutos.controllers.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoriaRequest {

    public String descricao;
    public int idCategoriaPai;
}
