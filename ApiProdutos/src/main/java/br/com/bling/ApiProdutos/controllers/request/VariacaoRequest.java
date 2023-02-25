package br.com.bling.ApiProdutos.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class VariacaoRequest {
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
