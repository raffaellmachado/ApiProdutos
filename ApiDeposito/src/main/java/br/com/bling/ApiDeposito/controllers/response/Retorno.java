package br.com.bling.ApiDeposito.controllers.response;

import lombok.Data;
import java.util.List;

@Data
public class Retorno {

    public List<Depositos> depositos;

    @Data
    public static class Depositos {
        public Deposito deposito;
    }
}
