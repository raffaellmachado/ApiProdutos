package br.com.bling.ApiProdutos.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponse {

    public String id;

    public String descricao;
    public String idCategoriaPai;
}

