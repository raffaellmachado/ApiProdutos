package br.com.bling.ApiDeposito.controllers.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;

@Data
public class RetornoResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Depositos> depositos;

    @Data
    public static class Depositos {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public DepositoResponse deposito;
    }
}
