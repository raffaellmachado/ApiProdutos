package br.com.bling.ApiProdutos.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CamposCustomizadosRequest {
    @Size(message = "Alias do campo customizado")
    public String alias;
}
