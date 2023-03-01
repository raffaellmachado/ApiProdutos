package br.com.bling.ApiDeposito.controllers;

import br.com.bling.ApiDeposito.controllers.request.RespostaRequest;
import br.com.bling.ApiDeposito.controllers.response.DepositoResponse;
import br.com.bling.ApiDeposito.controllers.response.RespostaResponse;
import br.com.bling.ApiDeposito.controllers.response.RetornoResponse;
import br.com.bling.ApiDeposito.services.DepositoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DepositoControllerTest {
    @Mock
    DepositoService depositoService;
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    DepositoController depositoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCategoria() throws Exception {

        // Deposito teste 01
        RetornoResponse.Depositos deposito1 = new RetornoResponse.Depositos();
        deposito1.deposito = new DepositoResponse();
        deposito1.deposito.id = "1";
        deposito1.deposito.descricao = "Deposito 1";
        deposito1.deposito.situacao = "1";
        deposito1.deposito.depositoPadrao = false ;
        deposito1.deposito.desconsiderarSaldo = false;

        // Deposito teste 02
        RetornoResponse.Depositos deposito2 = new RetornoResponse.Depositos();
        deposito2.deposito = new DepositoResponse();
        deposito2.deposito.id = "2";
        deposito2.deposito.descricao = "Deposito 2";
        deposito2.deposito.situacao = "1";
        deposito2.deposito.depositoPadrao = true;
        deposito2.deposito.desconsiderarSaldo = false;

        RetornoResponse retorno = new RetornoResponse();
        retorno.depositos = new ArrayList<>();
        retorno.depositos.add(deposito1);
        retorno.depositos.add(deposito2);

        RespostaResponse resposta = new RespostaResponse();
        resposta.retorno = retorno;

        // Configura o comportamento do serviço simulado
        when(depositoService.getAllDeposit()).thenReturn(resposta);

        // Chama o método sendo testado
        RespostaResponse result = depositoController.getCategoria();

        // Verifica se o serviço simulado foi chamado corretamente e se o resultado foi o esperado
        verify(depositoService).getAllDeposit();
        assertEquals(resposta, result);
    }

    @Test
    void testGetDepositByIdDeposit() {
        String idDeposito = "123";

        RespostaResponse resposta = new RespostaResponse();
        RetornoResponse retorno = new RetornoResponse();

        ArrayList<RetornoResponse.Depositos> depositos = new ArrayList<>();
        RetornoResponse.Depositos deposito = new RetornoResponse.Depositos();

        deposito.deposito = new DepositoResponse();
        deposito.deposito.id = idDeposito;
        deposito.deposito.situacao = "1";
        deposito.deposito.descricao = "Deposito 1";
        deposito.deposito.depositoPadrao = false;
        deposito.deposito.desconsiderarSaldo = true;

        depositos.add(deposito);
        retorno.setDepositos(depositos);
        resposta.setRetorno(retorno);

        Mockito.when(depositoService.getDepositByIdDeposit(idDeposito)).thenReturn(resposta);

        RespostaResponse result = depositoController.getDepositByIdDeposit(idDeposito);
        Assertions.assertEquals(resposta, result);
    }

    @Test
    void testCreateDeposit() {
//        when(depositoService.createDeposit(anyString())).thenReturn(new RespostaRequest());
//
//        RespostaRequest result = depositoController.createDeposit("xml");
//        Assertions.assertEquals(new RespostaRequest(), result);
    }

    @Test
    void testUpdateCategory() {
//        when(depositoService.updateDeposit(anyString(), anyString())).thenReturn(new RespostaRequest());
//
//        RespostaRequest result = depositoController.updateCategory("xml", "idDeposito");
//        Assertions.assertEquals(new RespostaRequest(), result);
    }
}