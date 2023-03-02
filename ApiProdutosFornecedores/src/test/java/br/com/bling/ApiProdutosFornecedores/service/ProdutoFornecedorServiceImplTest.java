package br.com.bling.ApiProdutosFornecedores.service;

import br.com.bling.ApiProdutosFornecedores.controllers.request.RespostaRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.response.RespostaResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;

class ProdutoFornecedorServiceImplTest {
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    ProdutoFornecedorServiceImpl produtoFornecedorServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtosfornecedores\":[{\"produtofornecedores\":{\"idProduto\":16023092137,\"fornecedores\":[{\"produtoFornecedor\":{\"idProdutoFornecedor\":\"478963346\",\"idFornecedor\":\"0\",\"produtoDescricao\":\"Descrição do fornecedor\",\"produtoCodigo\":\"123\",\"precoCompra\":\"0.0000000000\",\"precoCusto\":\"1.2300000000\",\"produtoGarantia\":\"4\",\"padrao\":\"1\"}}]}}]}}";
        Mockito.when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaResponse
        RespostaResponse result = produtoFornecedorServiceImpl.getAllProducts();

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

    @Test
    void testGetProducId() {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtosfornecedores\":[{\"produtofornecedores\":{\"idProduto\":16023092137,\"fornecedores\":[{\"produtoFornecedor\":{\"idProdutoFornecedor\":\"478963346\",\"idFornecedor\":\"0\",\"produtoDescricao\":\"Descrição do fornecedor\",\"produtoCodigo\":\"123\",\"precoCompra\":\"0.0000000000\",\"precoCusto\":\"1.2300000000\",\"produtoGarantia\":\"4\",\"padrao\":\"1\"}}]}}]}}";
        Mockito.when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaResponse
        RespostaResponse result = produtoFornecedorServiceImpl.getProducId("idProdutoFornecedor");
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

    @Test
    void testCreateProduct() {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtosfornecedores\":[{\"produtofornecedores\":{\"idProduto\":16023092137,\"fornecedores\":[{\"produtoFornecedor\":{\"idProdutoFornecedor\":\"478963346\",\"idFornecedor\":\"0\",\"produtoDescricao\":\"Descrição do fornecedor\",\"produtoCodigo\":\"123\",\"precoCompra\":\"0.0000000000\",\"precoCusto\":\"1.2300000000\",\"produtoGarantia\":\"4\",\"padrao\":\"1\"}}]}}]}}";
        Mockito.when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        RespostaRequest result = produtoFornecedorServiceImpl.createProduct("xml");

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        Assertions.assertEquals(1, result.getRetorno().getProdutosfornecedores().size());
        Assertions.assertEquals("16023092137", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getIdFornecedor());
        Assertions.assertEquals("478963346", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getId());
        Assertions.assertEquals("0", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getIdProduto());
        Assertions.assertEquals("Descrição do fornecedor", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getIdFornecedor());
        Assertions.assertEquals("123", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getProdutoDescricao());
        Assertions.assertEquals("0.0000000000", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getProdutoDescricao());
        Assertions.assertEquals("1.2300000000", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getProdutoCodigo());
        Assertions.assertEquals("4", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getPrecoCompra());
        Assertions.assertEquals("1", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getPrecoCusto());
        Assertions.assertEquals("1", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getProdutoGarantia());
        Assertions.assertEquals("1", result.getRetorno().getProdutosfornecedores().get(0).getProdutoFornecedor().getPadrao());

        System.out.println("POST: " + result);
    }


    @Test
    void testUpdateProduct() {
        RespostaRequest result = produtoFornecedorServiceImpl.updateProduct("xml", "idProdutoFornecedor");
        Assertions.assertEquals(new RespostaRequest(), result);
    }
}