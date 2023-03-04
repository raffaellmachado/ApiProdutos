package br.com.bling.ApiProdutos.controllers.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CamposCustomizadosRequest {
    @Size(message = "Alias do campo customizado")
    public String alias;
}
