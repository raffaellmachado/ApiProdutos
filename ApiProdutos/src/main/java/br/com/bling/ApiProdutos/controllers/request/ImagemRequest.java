package br.com.bling.ApiProdutos.controllers.request;

import lombok.Data;

@Data
public class ImagemRequest {

    public String link;
    public String validade;
    public String tipoArmazenamento;

}
