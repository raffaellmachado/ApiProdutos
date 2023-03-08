package br.com.bling.ApiDeposito.controllers;

import br.com.bling.ApiDeposito.controllers.request.DepositoRequest;
import br.com.bling.ApiDeposito.controllers.request.JsonRequest;
import br.com.bling.ApiDeposito.controllers.request.RetornoRequest;
import br.com.bling.ApiDeposito.controllers.response.DepositoResponse;
import br.com.bling.ApiDeposito.controllers.response.JsonResponse;
import br.com.bling.ApiDeposito.controllers.response.RetornoResponse;
import br.com.bling.ApiDeposito.exceptions.DepositoIdDepositoException;
import br.com.bling.ApiDeposito.exceptions.DepositoListaException;
import br.com.bling.ApiDeposito.services.DepositoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

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
    void testGetDeposit() throws Exception {

        // Deposito teste 01
        RetornoResponse.Depositos deposito1 = new RetornoResponse.Depositos();
        deposito1.deposito = new DepositoResponse();
        deposito1.deposito.setId("1");
        deposito1.deposito.setDescricao("Deposito 1");
        deposito1.deposito.setSituacao("A");
        deposito1.deposito.setDepositoPadrao(false);
        deposito1.deposito.setDesconsiderarSaldo(false);

        // Deposito teste 02
        RetornoResponse.Depositos deposito2 = new RetornoResponse.Depositos();
        deposito2.deposito = new DepositoResponse();
        deposito2.deposito.setId("2");
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
        JsonResponse result = depositoController.getCategoria().getBody();

        // Verifica se o serviço simulado foi chamado corretamente e se o resultado foi o esperado
        verify(depositoService).getAllDeposit();
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCAR A LISTA DE DEPOSITOS A ENTRAR NO EXCEPTION".
     */
    @Test
    void testGetAllDepositException() {
        String idProdutoFornecedor = "123";
        when(depositoService.getAllDeposit()).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(DepositoListaException.class, () -> {
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
        String idDeposito = "783698524";

        JsonResponse resposta = new JsonResponse();
        RetornoResponse retorno = new RetornoResponse();

        ArrayList<RetornoResponse.Depositos> depositos = new ArrayList<>();
        RetornoResponse.Depositos deposito = new RetornoResponse.Depositos();

        deposito.deposito = new DepositoResponse();
        deposito.deposito.setId(idDeposito);
        deposito.deposito.setDescricao("Deposito 1");
        deposito.deposito.setSituacao("A");
        deposito.deposito.setDepositoPadrao(false);
        deposito.deposito.setDesconsiderarSaldo(true);

        depositos.add(deposito);
        retorno.setDepositos(depositos);
        resposta.setRetorno(retorno);

        Mockito.when(depositoService.getDepositByIdDeposit(idDeposito)).thenReturn(resposta);

        JsonResponse result = depositoController.getDepositByIdDeposit(idDeposito).getBody();
        Assertions.assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCA DEPOSITO PELO IDDEPOSITO A ENTRAR NO EXCEPTION".
     */
    @Test
    void testGetDepositByIdDepositException() {
        String idDeposito = "783698524";
        when(depositoService.getDepositByIdDeposit(idDeposito)).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(DepositoIdDepositoException.class, () -> {
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
        JsonRequest resposta = new JsonRequest();
        RetornoRequest retorno = new RetornoRequest();

        ArrayList<ArrayList<RetornoRequest.Deposito>> depositos = new ArrayList<>();
        ArrayList<RetornoRequest.Deposito> depositosList = new ArrayList<>();
        RetornoRequest.Deposito deposito = new RetornoRequest.Deposito();

        deposito.deposito = new DepositoRequest();
        deposito.deposito.setId("01");
        deposito.deposito.setDescricao("Deposito Padrão");
        deposito.deposito.setSituacao("A");
        deposito.deposito.setDepositoPadrao(true);
        deposito.deposito.setDesconsiderarSaldo(true);

        depositosList.add(deposito);
        depositos.add(depositosList);
        retorno.setDepositos(depositos);
        resposta.setRetorno(retorno);

        when(depositoService.createDeposit(xml)).thenReturn(resposta);

        JsonRequest result = (JsonRequest) depositoController.createDeposit(xml).getBody();
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - POST "FORÇA O METODO DE DEPOSITO A ENTRAR NO EXCEPTION".
     */
    @Test
    void testCreateDepositException_1() {
        // Cria o XML de deposito a ser enviado na requisição
        String xml = "<depositos>\n" +
                "     <deposito>\n" +
                "          <descricao>Deposito 1</descricao>\n" +
                "      </deposito>\n" +
                "   </depositos>";

        // Cria um mock do serviço que retorna null
        when(depositoService.createDeposit(xml)).thenReturn(null);

        // Chama o método sendo testado e espera a exceção correta
        ResponseEntity<?> response = depositoController.createDeposit(xml);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Verifica se o serviço foi chamado
        verify(depositoService).createDeposit(xml);
    }

    @Test
    void testCreateDepositException_2() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<categorias>\n" +
                "     <categoria>\n" +
                "          <descricao>Calçado</descricao>\n" +
                "          <idCategoriaPai>0</idCategoriaPai>\n" +
                "      </categoria>\n" +
                "   </categorias>";

        // Cria um mock do serviço que lança uma HttpStatusCodeException
        when(depositoService.createDeposit(xml)).thenThrow(new HttpStatusCodeException(HttpStatus.NOT_FOUND) {
        });

        // Chama o método sendo testado e verifica se a resposta é a esperada
        ResponseEntity<?> response = depositoController.createDeposit(xml);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(new JsonRequest(), response.getBody());

        // Verifica se o serviço foi chamado
        verify(depositoService).createDeposit(xml);
    }

    @Test
    void testCreateDepositException_3() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<categorias>\n" +
                "     <categoria>\n" +
                "          <descricao>Calçado</descricao>\n" +
                "          <idCategoriaPai>0</idCategoriaPai>\n" +
                "      </categoria>\n" +
                "   </categorias>";

        // Cria um mock do serviço que lança uma exceção
        when(depositoService.createDeposit(xml)).thenThrow(new RuntimeException());

        // Chama o método sendo testado e espera a exceção correta
        ResponseEntity<?> response = depositoController.createDeposit(xml);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verifica se o serviço foi chamado
        verify(depositoService).createDeposit(xml);
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

        // Cria um mock do serviço que retorna null
        when(depositoService.updateDeposit(xml, idDeposito)).thenReturn(null);

        // Chama o método sendo testado e espera a exceção correta
        ResponseEntity<?> response = depositoController.updateDeposit(xml, idDeposito);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Verifica se o serviço foi chamado
        verify(depositoService).updateDeposit(xml, idDeposito);
    }


    /**
     * TESTE CONTROLLER - PUT "FORÇA O METODO DE ATUALIZAR DEPOSITO A ENTRAR NO EXCEPTION".
     */
    @Test
    void testUpdateDepositException_1() {
        String idCategoria = "159357";
        String xml = "<categorias>\n" +
                "     <categoria>\n" +
                "          <descricao>Calçado</descricao>\n" +
                "          <idCategoriaPai>0</idCategoriaPai>\n" +
                "      </categoria>\n" +
                "   </categorias>";

        // Cria um mock do serviço que retorna null
        when(depositoService.updateDeposit(xml, idCategoria)).thenReturn(null);

        // Chama o método sendo testado e espera a exceção correta
        ResponseEntity<?> response = depositoController.updateDeposit(xml, idCategoria);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Verifica se o serviço foi chamado
        verify(depositoService).updateDeposit(xml, idCategoria);
    }

    @Test
    void testUpdateDepositException_2() {
        String idCategoria = "159357";
        String xml = "<categorias>\n" +
                "     <categoria>\n" +
                "          <descricao>Calçado</descricao>\n" +
                "          <idCategoriaPai>0</idCategoriaPai>\n" +
                "      </categoria>\n" +
                "   </categorias>";

        // Cria um mock do serviço que lança uma HttpStatusCodeException
        when(depositoService.updateDeposit(xml, idCategoria)).thenThrow(new HttpStatusCodeException(HttpStatus.NOT_FOUND) {
        });

        // Chama o método sendo testado e verifica se a resposta é a esperada
        ResponseEntity<?> response = depositoController.updateDeposit(xml, idCategoria);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(new JsonRequest(), response.getBody());

        // Verifica se o serviço foi chamado
        verify(depositoService).updateDeposit(xml, idCategoria);
    }

    @Test
    void testUpdateDepositException_3() {
        String idCategoria = "159357";
        String xml = "<categorias>\n" +
                "     <categoria>\n" +
                "          <descricao>Calçado</descricao>\n" +
                "          <idCategoriaPai>0</idCategoriaPai>\n" +
                "      </categoria>\n" +
                "   </categorias>";

        // Cria um mock do serviço que lança uma exceção
        when(depositoService.updateDeposit(xml, idCategoria)).thenThrow(new RuntimeException());

        // Chama o método sendo testado e espera a exceção correta
        ResponseEntity<?> response = depositoController.updateDeposit(xml, idCategoria);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verifica se o serviço foi chamado
        verify(depositoService).updateDeposit(xml, idCategoria);
    }
}