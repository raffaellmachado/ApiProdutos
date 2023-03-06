package br.com.bling.ApiDeposito.exceptions;

import lombok.Data;

import java.util.Map;
@Data
public class ErrosRequest {

    private Map<String, String> erros;

    public Map<String, String> getErros() {
        return erros;
    }

}
