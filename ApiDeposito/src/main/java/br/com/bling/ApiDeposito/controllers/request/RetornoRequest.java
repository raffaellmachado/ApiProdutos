package br.com.bling.ApiDeposito.controllers.request;

import br.com.bling.ApiDeposito.controllers.response.DepositoResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RetornoRequest {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ArrayList<ArrayList<Deposito>> depositos;

    @Data
    public static class Deposito {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public DepositoRequest deposito;
    }
}
