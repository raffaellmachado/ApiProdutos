package br.com.bling.ApiProdutos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class Deposito2 {

    @JsonProperty("id")
    public int id;
    @JsonProperty("descricao")
    public String descricao;
    @JsonProperty("situacao")
    public String situacao;
    @JsonProperty("depositoPadrao")
    public String depositoPadrao;
    @JsonProperty("desconsiderarSaldo")
    public String desconsiderarSaldo;

}
