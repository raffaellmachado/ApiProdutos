package br.com.bling.ApiProdutos.controllers.request;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;
@Data
public class EstruturaRequest {
    @Size(max = 1, message = "valor unitário da variação")
    public String tipoEstoque;
    @Size(max = 1, message = "valor unitário da variação")
    public String lancarEstoque;
    public List<ComponenteRequest> componente;
}
