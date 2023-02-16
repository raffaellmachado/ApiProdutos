package br.com.bling.ApiProdutos.controllers.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@Data
public class CategoriaRequest {

    @Size(max = 50, message = "Maximo 50 caracteres")
    public String descricao;
    @Size(max = 11, message = "Maximo 11 caracteres")
    public int idCategoriaPai = 0;

}
