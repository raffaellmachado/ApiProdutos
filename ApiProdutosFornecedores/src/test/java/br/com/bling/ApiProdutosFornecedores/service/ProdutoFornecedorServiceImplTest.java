package br.com.bling.ApiProdutosFornecedores.service;

import br.com.bling.ApiProdutosFornecedores.controllers.request.RespostaRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.response.RespostaResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

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
        RespostaResponse result = produtoFornecedorServiceImpl.getAllProducts();
        Assertions.assertEquals(new RespostaResponse(), result);
    }

    @Test
    void testGetProducId() {
        RespostaResponse result = produtoFornecedorServiceImpl.getProducId("idProdutoFornecedor");
        Assertions.assertEquals(new RespostaResponse(), result);
    }

    @Test
    void testCreateProduct() {
        RespostaRequest result = produtoFornecedorServiceImpl.createProduct("xml");
        Assertions.assertEquals(new RespostaRequest(), result);
    }

    @Test
    void testUpdateProduct() {
        RespostaRequest result = produtoFornecedorServiceImpl.updateProduct("xml", "idProdutoFornecedor");
        Assertions.assertEquals(new RespostaRequest(), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme