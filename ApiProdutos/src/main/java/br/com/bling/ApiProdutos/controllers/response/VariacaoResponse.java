package br.com.bling.ApiProdutos.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class VariacaoResponse {
    public String nome;

    public String codigo;

    @Data
    public static class Deposito {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public Deposito2 deposito;

    }

    @Data
    public static class Deposito2 {

        public String id;
        public String nome;
        public String saldo;

    }
}
