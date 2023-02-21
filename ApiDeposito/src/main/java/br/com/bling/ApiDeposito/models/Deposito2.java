package br.com.bling.ApiDeposito.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class Deposito2 {


    public String id;
    public String descricao;
    public String situacao;
    public String depositoPadrao;
    public String desconsiderarSaldo;

}
