package br.com.bling.ApiProdutosFornecedores.controllers;

import br.com.bling.ApiProdutosFornecedores.controllers.request.RespostaRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.response.RespostaResponse;
import br.com.bling.ApiProdutosFornecedores.service.ProdutoFornecedorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

class ProdutoFornecedorControllerTest {
    @Mock
    RestTemplate restTemplate;
    @Mock
    ProdutoFornecedorService produtoFornecedorService;
    @InjectMocks
    ProdutoFornecedorController produtoFornecedorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        when(produtoFornecedorService.getAllProducts()).thenReturn(new RespostaResponse());

        RespostaResponse result = produtoFornecedorController.getAllProducts();
        Assertions.assertEquals(new RespostaResponse(), result);
    }

    @Test
    void testGetProducId() {
        when(produtoFornecedorService.getProducId(anyString())).thenReturn(new RespostaResponse());

        RespostaResponse result = produtoFornecedorController.getProducId("idProdutoFornecedor");
        Assertions.assertEquals(new RespostaResponse(), result);
    }

    @Test
    void testCreateProduct() {
        when(produtoFornecedorService.createProduct(anyString())).thenReturn(new RespostaRequest());

        RespostaRequest result = produtoFornecedorController.createProduct("xml");
        Assertions.assertEquals(new RespostaRequest(), result);
    }

    @Test
    void testUpdateProduct() {
        when(produtoFornecedorService.updateProduct(anyString(), anyString())).thenReturn(new RespostaRequest());

        RespostaRequest result = produtoFornecedorController.updateProduct("xml", "idProdutoFornecedor");
        Assertions.assertEquals(new RespostaRequest(), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme