package br.com.bling.ApiProdutosFornecedores.service;

import br.com.bling.ApiProdutosFornecedores.controllers.request.JsonRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.response.JsonResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

class ProdutoFornecedorServiceImplTest {
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    ProdutoFornecedorServiceImpl produtoFornecedorServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * TESTE SERVICE - GET "BUSCAR A LISTA DE PRODUTOS FORNECEDORES CADASTRADOS NO BLING".
     */
    @Test
    void testGetAllProducts() {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtosfornecedores\":[{\"produtofornecedores\":{\"idProduto\":16023092137,\"fornecedores\":[{\"produtoFornecedor\":{\"idProdutoFornecedor\":\"478963346\",\"idFornecedor\":\"0\",\"produtoDescricao\":\"Descrição do fornecedor\",\"produtoCodigo\":\"123\",\"precoCompra\":\"0.0000000000\",\"precoCusto\":\"1.2300000000\",\"produtoGarantia\":\"4\",\"padrao\":\"1\"}}]}}]}}";
        ResponseEntity<String> responseEntity = ResponseEntity.ok(jsonResponse);
        Mockito.when(restTemplate.exchange(anyString(), ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(HttpEntity.class), eq(String.class))).thenReturn(responseEntity);

        // Chama o método que deve converter a resposta em um objeto RespostaResponse
        JsonResponse result = produtoFornecedorServiceImpl.getAllProducts();

        // Verifica se a lista de categorias foi corretamente convertida a partir da resposta da API
        Assertions.assertEquals(1, result.getRetorno().getProdutosfornecedores().size());
        Assertions.assertEquals("16023092137", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getIdProduto());
        Assertions.assertEquals("478963346", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getIdProdutoFornecedor());
        Assertions.assertEquals("0", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getIdFornecedor());
        Assertions.assertEquals("Descrição do fornecedor", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getProdutoDescricao());
        Assertions.assertEquals("123", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getProdutoCodigo());
        Assertions.assertEquals("0.0000000000", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getPrecoCompra());
        Assertions.assertEquals("1.2300000000", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getPrecoCusto());
        Assertions.assertEquals("4", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getProdutoGarantia());
        Assertions.assertEquals("1", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getPadrao());

        System.out.println("GET LIST: " + result);
    }

    /**
     * TESTE SERVICE - GET "BUSCA PRODUTO FORNECEDOR PELO IDPRODUTOFORNECEDOR".
     */
    @Test
    void testGetProducId() {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtosfornecedores\":[{\"produtofornecedores\":{\"idProduto\":16023092137,\"fornecedores\":[{\"produtoFornecedor\":{\"idProdutoFornecedor\":\"478963346\",\"idFornecedor\":\"0\",\"produtoDescricao\":\"Descrição do fornecedor\",\"produtoCodigo\":\"123\",\"precoCompra\":\"0.0000000000\",\"precoCusto\":\"1.2300000000\",\"produtoGarantia\":\"4\",\"padrao\":\"1\"}}]}}]}}";
        ResponseEntity<String> responseEntity = ResponseEntity.ok(jsonResponse);
        Mockito.when(restTemplate.exchange(anyString(), ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(HttpEntity.class), eq(String.class))).thenReturn(responseEntity);

        // Chama o método que deve converter a resposta em um objeto RespostaResponse
        JsonResponse result = produtoFornecedorServiceImpl.getProducId("idProdutoFornecedor");
        Assertions.assertEquals(1, result.getRetorno().getProdutosfornecedores().size());
        Assertions.assertEquals("16023092137", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getIdProduto());
        Assertions.assertEquals("478963346", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getIdProdutoFornecedor());
        Assertions.assertEquals("0", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getIdFornecedor());
        Assertions.assertEquals("Descrição do fornecedor", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getProdutoDescricao());
        Assertions.assertEquals("123", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getProdutoCodigo());
        Assertions.assertEquals("0.0000000000", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getPrecoCompra());
        Assertions.assertEquals("1.2300000000", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getPrecoCusto());
        Assertions.assertEquals("4", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getProdutoGarantia());
        Assertions.assertEquals("1", result.getRetorno().getProdutosfornecedores().get(0).getProdutofornecedores().getFornecedores().get(0).getProdutoFornecedor().getPadrao());

        System.out.println("GET ID: " + result);
    }

    /**
     * TESTE SERVICE - POST "CADASTRA UMA NOVO PRODUTO FORNECEDOR UTILIZANDO XML/JSON".
     */
    @Test
    void testCreateProduct() {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtosfornecedores\":[{\"produtoFornecedor\":{\"idProdutoFornecedor\":\"16023092137\",\"idFornecedor\":\"478963346\",\"produtoDescricao\":\"Descrição do fornecedor\",\"produtoCodigo\":\"123\",\"precoCompra\":\"0.0\",\"precoCusto\":\"1.23\",\"produtoGarantia\":\"4\",\"padrao\":\"1\"}}]}}]}}";
        ResponseEntity<String> responseEntity = ResponseEntity.ok(jsonResponse);
        Mockito.when(restTemplate.exchange(anyString(), ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(HttpEntity.class), eq(String.class))).thenReturn(responseEntity);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        JsonRequest result = (JsonRequest) produtoFornecedorServiceImpl.createProduct("xml");

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        Assertions.assertEquals("16023092137", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getIdProdutoFornecedor());
        Assertions.assertEquals(478963346, result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getIdFornecedor());
        Assertions.assertEquals("Descrição do fornecedor", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getProdutoDescricao());
        Assertions.assertEquals("123", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getProdutoCodigo());
        Assertions.assertEquals(BigDecimal.valueOf(0.0), result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getPrecoCompra());
        Assertions.assertEquals(BigDecimal.valueOf(1.23), result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getPrecoCusto());
        Assertions.assertEquals(4, result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getProdutoGarantia());
        Assertions.assertEquals(1, result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getPadrao());

        System.out.println("POST: " + result);
    }

    @Test
    void testUpdateProduct() {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtosfornecedores\":[{\"produtoFornecedor\":{\"idProdutoFornecedor\":\"16023092137\",\"idFornecedor\":\"478963346\",\"produtoDescricao\":\"Descrição do fornecedor\",\"produtoCodigo\":\"123\",\"precoCompra\":\"0.0\",\"precoCusto\":\"1.23\",\"produtoGarantia\":\"4\",\"padrao\":\"1\"}}]}}]}}";
        ResponseEntity<String> responseEntity = ResponseEntity.ok(jsonResponse);
        Mockito.when(restTemplate.exchange(anyString(), ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(HttpEntity.class), eq(String.class))).thenReturn(responseEntity);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        JsonRequest result = (JsonRequest) produtoFornecedorServiceImpl.updateProduct("xml", "idProdutoFornecedor");

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        Assertions.assertEquals("16023092137", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getIdProdutoFornecedor());
        Assertions.assertEquals(478963346, result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getIdFornecedor());
        Assertions.assertEquals("Descrição do fornecedor", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getProdutoDescricao());
        Assertions.assertEquals("123", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getProdutoCodigo());
        Assertions.assertEquals(BigDecimal.valueOf(0.0), result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getPrecoCompra());
        Assertions.assertEquals(BigDecimal.valueOf(1.23), result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getPrecoCusto());
        Assertions.assertEquals(4, result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getProdutoGarantia());
        Assertions.assertEquals(1, result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getPadrao());

        System.out.println("POST: " + result);
    }
}