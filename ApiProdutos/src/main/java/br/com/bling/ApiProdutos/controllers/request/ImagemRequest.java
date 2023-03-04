package br.com.bling.ApiProdutos.controllers.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ImagemRequest {
    @Size(message = "URL da imagem")
    public String url;

}
