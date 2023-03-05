package br.com.bling.ApiProdutos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EstruturaRequest {
    @Size(max = 1, message = "valor unitário da variação")
    public String tipoEstoque;
    @Size(max = 1, message = "valor unitário da variação")
    public String lancarEstoque;
    public List<ComponenteRequest> componente;
}
