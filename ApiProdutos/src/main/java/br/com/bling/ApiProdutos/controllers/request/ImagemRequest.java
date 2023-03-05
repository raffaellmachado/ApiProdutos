package br.com.bling.ApiProdutos.controllers.request;

import lombok.*;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagemRequest {
    @Size(message = "URL da imagem")
    public String url;

}
