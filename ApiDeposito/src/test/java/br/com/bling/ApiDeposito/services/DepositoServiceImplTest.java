package br.com.bling.ApiDeposito.services;

import br.com.bling.ApiDeposito.controllers.request.RespostaRequest;
import br.com.bling.ApiDeposito.controllers.response.RespostaResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

class DepositoServiceImplTest {
    @Mock
    RestTemplate restTemplate;

    @Mock
    DepositoService categoriaService;

    @InjectMocks
    DepositoServiceImpl depositoServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDeposit() throws Exception {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\": {\"depositos\": [{\"deposito\": {\"id\": \"01\", \"situacao\": \"Deposito 1\",\"depositoPadrao\":\"true\",\"desconsiderarSaldo\":\"false\"}}, {\"deposito\": {\"id\": \"02\", \"situacao\": \"Deposito 2\",\"depositoPadrao\":\"true\",\"desconsiderarSaldo\":\"false\"}}]}}";
        Mockito.when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaResponse
        RespostaResponse result = depositoServiceImpl.getAllDeposit();

        // Verifica se a lista de categorias foi corretamente convertida a partir da resposta da API
        Assertions.assertEquals(2, result.getRetorno().getDepositos().size());
        Assertions.assertEquals("01", result.getRetorno().getDepositos().get(0).getDeposito().getId());
        Assertions.assertEquals("Deposito 1", result.getRetorno().getDepositos().get(0).getDeposito().getSituacao());
        Assertions.assertEquals(true, result.getRetorno().getDepositos().get(0).getDeposito().depositoPadrao);
        Assertions.assertEquals(false, result.getRetorno().getDepositos().get(0).getDeposito().desconsiderarSaldo);

        Assertions.assertEquals("02", result.getRetorno().getDepositos().get(1).getDeposito().getId());
        Assertions.assertEquals("Deposito 2", result.getRetorno().getDepositos().get(1).getDeposito().getSituacao());
        Assertions.assertEquals(true, result.getRetorno().getDepositos().get(1).getDeposito().depositoPadrao);
        Assertions.assertEquals(false, result.getRetorno().getDepositos().get(1).getDeposito().desconsiderarSaldo);

        System.out.println("GET LIST: " + result);
    }

    @Test
    void testGetDepositByIdDeposit() throws Exception {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"depositos\":[{\"deposito\":{\"id\":\"007\",\"descricao\":\"Desfazimento\",\"situacao\":\"Inativo\",\"depositoPadrao\":\"true\",\"desconsiderarSaldo\":\"false\"}}]}}";
        Mockito.when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaResponse
        RespostaResponse result = depositoServiceImpl.getDepositByIdDeposit("idDeposito");

        // Verifica se a categoria foi corretamente convertida a partir da resposta da API
        Assertions.assertEquals(1, result.getRetorno().getDepositos().size());
        Assertions.assertEquals("007", result.getRetorno().getDepositos().get(0).getDeposito().getId());
        Assertions.assertEquals("Desfazimento", result.getRetorno().getDepositos().get(0).getDeposito().getDescricao());
        Assertions.assertEquals("Inativo", result.getRetorno().getDepositos().get(0).getDeposito().getSituacao());
        Assertions.assertEquals(true, result.getRetorno().getDepositos().get(0).getDeposito().depositoPadrao);
        Assertions.assertEquals(false, result.getRetorno().getDepositos().get(0).getDeposito().desconsiderarSaldo);



        System.out.println("GET ID: " + result);
    }

    @Test
    void testCreateDeposit() throws Exception {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"depositos\":[{\"deposito\":{\"id\":\"14886963547\",\"descricao\":\"Geral\",\"situacao\":\"Ativo\",\"depositoPadrao\":\"true\",\"desconsiderarSaldo\":\"false\"}}]}}";
        Mockito.when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        RespostaRequest result = depositoServiceImpl.createDeposit("xml");

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        Assertions.assertEquals("14886963547", result.getRetorno().getDepositos().get(0).get(0).getDeposito().getId());
        Assertions.assertEquals("Geral", result.getRetorno().getDepositos().get(0).get(0).getDeposito().getDescricao());
        Assertions.assertEquals("Ativo", result.getRetorno().getDepositos().get(0).get(0).getDeposito().getSituacao());
        Assertions.assertEquals(true, result.getRetorno().getDepositos().get(0).get(0).getDeposito().depositoPadrao);
        Assertions.assertEquals(false, result.getRetorno().getDepositos().get(0).get(0).getDeposito().desconsiderarSaldo);

        System.out.println("POST: " + result);
    }

    @Test
    void testUpdateDeposit() {
//        RespostaRequest result = depositoServiceImpl.updateDeposit("xml", "idDeposito");
//        Assertions.assertEquals(new RespostaRequest(), result);
    }
}