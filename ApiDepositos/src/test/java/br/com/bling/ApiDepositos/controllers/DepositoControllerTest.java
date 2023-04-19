package br.com.bling.ApiDepositos.controllers;

import br.com.bling.ApiDepositos.controllers.request.DepositoRequest;
import br.com.bling.ApiDepositos.controllers.request.JsonRequest;
import br.com.bling.ApiDepositos.controllers.request.RetornoRequest;
import br.com.bling.ApiDepositos.controllers.response.DepositoResponse;
import br.com.bling.ApiDepositos.controllers.response.JsonResponse;
import br.com.bling.ApiDepositos.controllers.response.RetornoResponse;
import br.com.bling.ApiDepositos.exceptions.ApiDepositoException;
import br.com.bling.ApiDepositos.exceptions.DetalhesErroResponse;
import br.com.bling.ApiDepositos.exceptions.ErroResponse;
import br.com.bling.ApiDepositos.services.DepositoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DepositoControllerTest {
    @Mock
    DepositoService depositoService;

    @InjectMocks
    DepositoController depositoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * TESTE CONTROLLER - GET "BUSCAR A LISTA DE DEPOSITOS CADASTRADOS NO BLING".
     */
    @Test
    void testGetDeposit() {

        // Deposito teste 01
        RetornoResponse.Depositos deposito1 = new RetornoResponse.Depositos();
        deposito1.deposito = new DepositoResponse();
        deposito1.deposito.setId(Long.valueOf("1"));
        deposito1.deposito.setDescricao("Deposito 1");
        deposito1.deposito.setSituacao("A");
        deposito1.deposito.setDepositoPadrao(false);
        deposito1.deposito.setDesconsiderarSaldo(false);

        // Deposito teste 02
        RetornoResponse.Depositos deposito2 = new RetornoResponse.Depositos();
        deposito2.deposito = new DepositoResponse();
        deposito2.deposito.setId(Long.valueOf("2"));
        deposito2.deposito.setDescricao("Deposito 2");
        deposito2.deposito.setSituacao("A");
        deposito2.deposito.setDepositoPadrao(true);
        deposito2.deposito.setDesconsiderarSaldo(false);

        RetornoResponse retorno = new RetornoResponse();
        retorno.depositos = new ArrayList<>();
        retorno.depositos.add(deposito1);
        retorno.depositos.add(deposito2);

        JsonResponse resposta = new JsonResponse();
        resposta.retorno = retorno;

        // Configura o comportamento do serviço simulado
        when(depositoService.getAllDeposit()).thenReturn(resposta);

        // Chama o método sendo testado
        JsonResponse result = depositoController.getAllDeposit().getBody();

        // Verifica se o serviço simulado foi chamado corretamente e se o resultado foi o esperado
        verify(depositoService).getAllDeposit();
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCAR A LISTA DE DEPOSITOS A ENTRAR NO EXCEPTION".
     */
    @Test
    void testGetAllDepositException() {
        JsonResponse jsonResponse = new JsonResponse();
        RetornoResponse retornoResponse = new RetornoResponse();

        retornoResponse.setDepositos(null);
        retornoResponse.setErros(null);
        jsonResponse.setRetorno(retornoResponse);

        when(depositoService.getAllDeposit()).thenReturn(jsonResponse);

        assertThrows(ApiDepositoException.class, () -> depositoController.getAllDeposit());
    }

    /**
     * TESTE CONTROLLER - GET "BUSCA DEPOSITO PELO IDDEPOSITO".
     */
    @Test
    void testGetDepositById() {
        String idDeposito = "783698524";

        JsonResponse resposta = new JsonResponse();
        RetornoResponse retorno = new RetornoResponse();

        ArrayList<RetornoResponse.Depositos> depositos = new ArrayList<>();
        RetornoResponse.Depositos deposito = new RetornoResponse.Depositos();

        deposito.deposito = new DepositoResponse();
        deposito.deposito.setId(Long.valueOf(idDeposito));
        deposito.deposito.setDescricao("Deposito 1");
        deposito.deposito.setSituacao("A");
        deposito.deposito.setDepositoPadrao(false);
        deposito.deposito.setDesconsiderarSaldo(true);

        depositos.add(deposito);
        retorno.setDepositos(depositos);
        resposta.setRetorno(retorno);

        Mockito.when(depositoService.getDepositById(idDeposito)).thenReturn(resposta);

        JsonResponse result = depositoController.getDepositById(idDeposito).getBody();
        Assertions.assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCA DEPOSITO PELO IDDEPOSITO A ENTRAR NO EXCEPTION".
     */
    @Test
    void testGetDepositByIdException() {
        String idDeposito = "783698524";
        JsonResponse jsonResponse = new JsonResponse();
        RetornoResponse retornoResponse = new RetornoResponse();

        retornoResponse.setDepositos(null);
        retornoResponse.setErros(null);
        jsonResponse.setRetorno(retornoResponse);

        when(depositoService.getDepositById(idDeposito)).thenReturn(jsonResponse);

        assertThrows(ApiDepositoException.class, () -> depositoController.getDepositById(idDeposito));
    }

    /**
     * TESTE CONTROLLER - POST "CADASTRA UMA NOVO DEPOSITO UTILIZANDO XML/JSON".
     */
    @Test
    void testCreateDeposit() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<depositos>\n" +
                "    <deposito>\n" +
                "        <descricao>Deposito padrão</descricao>\n" +
                "        <situacao>A</situacao>\n" +
                "        <depositoPadrao>true</depositoPadrao>\n" +
                "        <desconsiderarSaldo>true</desconsiderarSaldo>\n" +
                "    </deposito>\n" +
                "</depositos>";

        // Simula a resposta da chamada para o serviço de categoria
        JsonRequest resposta = new JsonRequest();
        RetornoRequest retorno = new RetornoRequest();

        ArrayList<ArrayList<RetornoRequest.Deposito>> depositos = new ArrayList<>();
        ArrayList<RetornoRequest.Deposito> depositosList = new ArrayList<>();
        RetornoRequest.Deposito deposito = new RetornoRequest.Deposito();

        deposito.deposito = new DepositoRequest();
        deposito.deposito.setId(Long.valueOf("01"));
        deposito.deposito.setDescricao("Deposito Padrão");
        deposito.deposito.setSituacao("A");
        deposito.deposito.setDepositoPadrao(true);
        deposito.deposito.setDesconsiderarSaldo(true);

        depositosList.add(deposito);
        depositos.add(depositosList);
        retorno.setDepositos(depositos);
        resposta.setRetorno(retorno);

        when(depositoService.createDeposit(xml)).thenReturn(resposta);

        JsonRequest result = depositoController.createDeposit(xml).getBody();
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - POST "FORÇA O METODO DE DEPOSITO A ENTRAR NO EXCEPTION".
     */
    @Test
    void testCreateDepositException() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<categorias>\n" +
                "     <categoria>\n" +
                "          <descricao>Calçado</descricao>\n" +
                "          <idCategoriaPai>0</idCategoriaPai>\n" +
                "      </categoria>\n" +
                "   </categorias>";

        JsonRequest jsonRequest = new JsonRequest();
        RetornoRequest retornoRequest = new RetornoRequest();

        retornoRequest.setDepositos(null);
        retornoRequest.setErros(null);
        jsonRequest.setRetorno(retornoRequest);

        when(depositoService.createDeposit(xml)).thenReturn(jsonRequest);

        assertThrows(ApiDepositoException.class, () -> depositoController.createDeposit(xml));
    }

    /**
     * TESTE CONTROLLER - PUT "ATUALIZA UM DEPOSITO UTILIZANDO XML/JSON".
     */
    @Test
    void testUpdateDeposit() {
        // Cria o XML de deposito a ser enviado na requisição
        String idDeposito = "158365";
        String xml = "<depositos>\n" +
                "     <deposito>\n" +
                "          <descricao>Deposito 1</descricao>\n" +
                "      </deposito>\n" +
                "   </depositos>";

        JsonRequest resposta = new JsonRequest();
        RetornoRequest retorno = new RetornoRequest();

        ArrayList<ArrayList<RetornoRequest.Deposito>> depositos = new ArrayList<>();
        ArrayList<RetornoRequest.Deposito> depositosList = new ArrayList<>();
        RetornoRequest.Deposito deposito = new RetornoRequest.Deposito();

        deposito.deposito = new DepositoRequest();
        deposito.deposito.setId(Long.valueOf(idDeposito));
        deposito.deposito.setDescricao("Deposito Padrão");
        deposito.deposito.setSituacao("A");
        deposito.deposito.setDepositoPadrao(true);
        deposito.deposito.setDesconsiderarSaldo(true);

        depositosList.add(deposito);
        depositos.add(depositosList);
        retorno.setDepositos(depositos);
        resposta.setRetorno(retorno);

        when(depositoService.updateDeposit(xml, idDeposito)).thenReturn(resposta);

        JsonRequest result = depositoController.updateDeposit(xml, idDeposito).getBody();
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - PUT "FORÇA O METODO DE ATUALIZAR DEPOSITO A ENTRAR NO EXCEPTION".
     */
    @Test
    void testUpdateDepositException() {
        String idCategoria = "159357";
        String xml = "<categorias>\n" +
                "     <categoria>\n" +
                "          <descricao>Calçado</descricao>\n" +
                "          <idCategoriaPai>0</idCategoriaPai>\n" +
                "      </categoria>\n" +
                "   </categorias>";

        JsonRequest jsonRequest = new JsonRequest();
        RetornoRequest retornoRequest = new RetornoRequest();

        retornoRequest.setDepositos(null);
        retornoRequest.setErros(null);
        jsonRequest.setRetorno(retornoRequest);

        when(depositoService.updateDeposit(xml, idCategoria)).thenReturn(jsonRequest);

        assertThrows(ApiDepositoException.class, () -> depositoController.updateDeposit(xml, idCategoria));
    }

    /**
     * TESTE CONTROLLER - GET "TESTE DO ERROS MAPEADOS QUE RETORNA DA API EXTERNA".
     */
    @Test
    void testErroResponse() throws JsonProcessingException {
        int codigoErro = 404;
        String mensagemErro = "A informação desejada não foi encontrada.";

        DetalhesErroResponse detalhesErro = new DetalhesErroResponse(codigoErro, mensagemErro);
        ErroResponse erroResponse = new ErroResponse();
        erroResponse.setErro(detalhesErro);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(erroResponse);

        ErroResponse erroResponseDeserialized = objectMapper.readValue(json, ErroResponse.class);

        assertEquals(erroResponse, erroResponseDeserialized);
    }
}
