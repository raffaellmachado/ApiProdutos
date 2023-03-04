package br.com.bling.ApiProdutos.controllers.request;

import lombok.Data;

import java.util.List;
@Data
public class EstruturaRequest {

    public String tipoEstoque;
    public String lancarEstoque;
    public List<ComponenteRequest> componente;
}
