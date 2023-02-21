package br.com.bling.ApiDeposito.models;

import lombok.Data;
import java.util.List;

@Data
public class Retorno {

    public List<Deposito> depositos;

    @Data
    public static class Deposito {
        public Deposito2 deposito;
    }
}
