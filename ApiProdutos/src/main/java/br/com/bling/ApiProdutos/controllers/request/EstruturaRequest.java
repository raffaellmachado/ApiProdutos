package br.com.bling.ApiProdutos.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstruturaRequest {
    @Size(max = 1, message = "valor unitário da variação")
    public String tipoEstoque;
    @Size(max = 1, message = "valor unitário da variação")
    public String lancarEstoque;
    public List<ComponenteRequest> componente;
}
