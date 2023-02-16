package br.com.bling.ApiProdutos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class Imagem {

    @JsonProperty("link")
    private String link;

    @JsonProperty("validade")
    private String validade;

    @JsonProperty("tipoArmazenamento")
    private String tipoArmazenamento;

}
