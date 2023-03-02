package br.com.bling.ApiDeposito.controllers;

import br.com.bling.ApiDeposito.controllers.request.DepositoRequest;
import br.com.bling.ApiDeposito.controllers.request.RespostaRequest;
import br.com.bling.ApiDeposito.controllers.request.RetornoRequest;
import br.com.bling.ApiDeposito.controllers.response.DepositoResponse;
import br.com.bling.ApiDeposito.controllers.response.RespostaResponse;
import br.com.bling.ApiDeposito.controllers.response.RetornoResponse;
import br.com.bling.ApiDeposito.exceptions.DepositoCadastroException;
import br.com.bling.ApiDeposito.exceptions.DepositoIdDepositoNaoEncontradoException;
import br.com.bling.ApiDeposito.exceptions.DepositoListaNaoEncontradoException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    /**
     * TESTE CONTROLLER - GET "BUSCAR A LISTA DE DEPOSITOS CADASTRADOS NO BLING".
     */
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

    /**
     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCAR A LISTA DE DEPOSITOS A ENTRAR NO EXCEPTION".
     */
    @Test
    void testGetAllCategoryException() {
        String idProdutoFornecedor = "123";
        when(depositoService.getAllDeposit()).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(DepositoListaNaoEncontradoException.class, () -> {
            depositoController.getCategoria();
        });

        // Verifica se o serviço foi chamado
        verify(depositoService).getAllDeposit();
    }

    /**
     * TESTE CONTROLLER - GET "BUSCA DEPOSITO PELO IDDEPOSITO".
     */
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

    /**
     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCA DEPOSITO PELO IDDEPOSITO A ENTRAR NO EXCEPTION".
     */
    @Test
    void testGetCategoryByIdCategoryException() {
        String idDeposito = "123";
        when(depositoService.getDepositByIdDeposit(idDeposito)).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(DepositoIdDepositoNaoEncontradoException.class, () -> {
            depositoController.getDepositByIdDeposit(idDeposito);
        });

        // Verifica se o serviço foi chamado
        verify(depositoService).getDepositByIdDeposit(idDeposito);
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
        RespostaRequest resposta = new RespostaRequest();
        RetornoRequest retorno = new RetornoRequest();

        ArrayList<ArrayList<RetornoRequest.Deposito>> depositos = new ArrayList<>();
        ArrayList<RetornoRequest.Deposito> depositosList = new ArrayList<>();
        RetornoRequest.Deposito deposito = new RetornoRequest.Deposito();

        deposito.deposito = new DepositoRequest();
        deposito.deposito.id = "01";
        deposito.deposito.descricao = "Deposito padrão";
        deposito.deposito.situacao = "A";
        deposito.deposito.depositoPadrao = true;
        deposito.deposito.desconsiderarSaldo = true;

        depositosList.add(deposito);
        depositos.add(depositosList);
        retorno.setDepositos(depositos);
        resposta.setRetorno(retorno);

        when(depositoService.createDeposit(xml)).thenReturn(resposta);

        RespostaRequest result = depositoController.createDeposit(xml);
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - POST "FORÇA O METODO DE DEPOSITO A ENTRAR NO EXCEPTION".
     */
    @Test
    void testCreateProductException() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<categorias>\n" +
                "     <categoria>\n" +
                "          <descricao>Calçado</descricao>\n" +
                "          <idCategoriaPai>0</idCategoriaPai>\n" +
                "      </categoria>\n" +
                "   </categorias>";


        // Cria um mock do serviço que retorna null
        when(depositoService.createDeposit(xml)).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(DepositoCadastroException.class, () -> {
            depositoController.createDeposit(xml);
        });

        // Verifica se o serviço foi chamado
        verify(depositoService).createDeposit(xml);
    }

    @Test
    void testUpdateCategory() {
//        when(depositoService.updateDeposit(anyString(), anyString())).thenReturn(new RespostaRequest());
//
//        RespostaRequest result = depositoController.updateCategory("xml", "idDeposito");
//        Assertions.assertEquals(new RespostaRequest(), result);
    }
}