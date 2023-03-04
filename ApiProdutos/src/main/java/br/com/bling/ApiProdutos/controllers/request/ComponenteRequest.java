package br.com.bling.ApiProdutos.controllers.request;

import lombok.Data;

@Data
public class ComponenteRequest {

    public String nome;

    public String codigo;
    public String quantidade;
}
