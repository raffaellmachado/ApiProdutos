package br.com.bling.ApiDeposito.models;

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
