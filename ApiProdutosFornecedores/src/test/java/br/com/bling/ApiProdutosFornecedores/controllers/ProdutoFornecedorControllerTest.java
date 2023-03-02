package br.com.bling.ApiProdutosFornecedores.controllers;

import br.com.bling.ApiProdutosFornecedores.controllers.request.ProdutoFornecedor2Request;
import br.com.bling.ApiProdutosFornecedores.controllers.request.RespostaRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.request.RetornoRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.response.*;
import br.com.bling.ApiProdutosFornecedores.exceptions.IdProdutoFornecedorNaoEncontradoException;
import br.com.bling.ApiProdutosFornecedores.exceptions.ProdutoFornecedorCadastroException;
import br.com.bling.ApiProdutosFornecedores.exceptions.ProdutoFornecedorListaNaoEncontradoException;
import br.com.bling.ApiProdutosFornecedores.service.ProdutoFornecedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        // Cria uma resposta simulada do serviço
        RetornoResponse.Produtosfornecedores produtosfornecedores1 = new RetornoResponse.Produtosfornecedores();
        produtosfornecedores1.produtofornecedores = new ProdutofornecedoreResponse();
        produtosfornecedores1.produtofornecedores.idProduto = "16023092137";
        produtosfornecedores1.produtofornecedores.fornecedores = new ArrayList<>();
        produtosfornecedores1.produtofornecedores.fornecedores.add(new FornecedoreResponse());
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor = new ProdutoFornecedor2Response();
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.idProdutoFornecedor = "478963346";
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.idFornecedor = "0";
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.produtoDescricao = "Descricao do fornecedor";
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.produtoCodigo = "123";
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.precoCompra = "0.0000000000";
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.precoCusto = "1.2300000000";
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.produtoGarantia = "4";
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.padrao = "1";

        RetornoResponse.Produtosfornecedores produtosfornecedores2 = new RetornoResponse.Produtosfornecedores();
        produtosfornecedores2.produtofornecedores = new ProdutofornecedoreResponse();
        produtosfornecedores2.produtofornecedores.idProduto = "16023092137";
        produtosfornecedores2.produtofornecedores.fornecedores = new ArrayList<>();
        produtosfornecedores2.produtofornecedores.fornecedores.add(new FornecedoreResponse());
        produtosfornecedores2.produtofornecedores.fornecedores.get(0).produtoFornecedor = new ProdutoFornecedor2Response();
        produtosfornecedores2.produtofornecedores.fornecedores.get(0).produtoFornecedor.idProdutoFornecedor = "478963347";
        produtosfornecedores2.produtofornecedores.fornecedores.get(0).produtoFornecedor.idFornecedor = "1";
        produtosfornecedores2.produtofornecedores.fornecedores.get(0).produtoFornecedor.produtoDescricao = "Descricao do fornecedor 2";
        produtosfornecedores2.produtofornecedores.fornecedores.get(0).produtoFornecedor.produtoCodigo = "456";
        produtosfornecedores2.produtofornecedores.fornecedores.get(0).produtoFornecedor.precoCompra = "1.0000000000";
        produtosfornecedores2.produtofornecedores.fornecedores.get(0).produtoFornecedor.precoCusto = "2.4600000000";
        produtosfornecedores2.produtofornecedores.fornecedores.get(0).produtoFornecedor.produtoGarantia = "8";
        produtosfornecedores2.produtofornecedores.fornecedores.get(0).produtoFornecedor.padrao = "2";

        RetornoResponse retorno = new RetornoResponse();
        retorno.produtosfornecedores = new ArrayList<>();
        retorno.produtosfornecedores.add(produtosfornecedores1);
        retorno.produtosfornecedores.add(produtosfornecedores2);

        RespostaResponse resposta = new RespostaResponse();
        resposta.retorno = retorno;

        // Configura o comportamento do serviço simulado
        when(produtoFornecedorService.getAllProducts()).thenReturn(resposta);

        // Chama o método sendo testado
        RespostaResponse result = this.produtoFornecedorController.getAllProducts();

        // Verifica se o serviço simulado foi chamado corretamente e se o resultado foi o esperado
        verify(produtoFornecedorService).getAllProducts();
        assertEquals(resposta, result);
    }

    @Test
    void testGetAllProductsException() {
        when(produtoFornecedorService.getAllProducts()).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(ProdutoFornecedorListaNaoEncontradoException.class, () -> {
            produtoFornecedorController.getAllProducts();
        });

        // Verifica se o serviço foi chamado
        verify(produtoFornecedorService).getAllProducts();
    }

    @Test
    void testGetProducId() {
        String idProdutoFornecedor = "123";

        RetornoResponse.Produtosfornecedores produtosfornecedores1 = new RetornoResponse.Produtosfornecedores();
        produtosfornecedores1.produtofornecedores = new ProdutofornecedoreResponse();
        produtosfornecedores1.produtofornecedores.idProduto = "16023092137";
        produtosfornecedores1.produtofornecedores.fornecedores = new ArrayList<>();
        produtosfornecedores1.produtofornecedores.fornecedores.add(new FornecedoreResponse());
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor = new ProdutoFornecedor2Response();
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.idProdutoFornecedor = idProdutoFornecedor;
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.idFornecedor = "0";
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.produtoDescricao = "Descricao do fornecedor";
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.produtoCodigo = "123";
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.precoCompra = "0.0000000000";
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.precoCusto = "1.2300000000";
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.produtoGarantia = "4";
        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.padrao = "1";

        RetornoResponse retorno = new RetornoResponse();
        retorno.produtosfornecedores = new ArrayList<>();
        retorno.produtosfornecedores.add(produtosfornecedores1);

        RespostaResponse resposta = new RespostaResponse();
        resposta.setRetorno(retorno);

        // Configura o comportamento do serviço simulado
        when(produtoFornecedorService.getProducId(idProdutoFornecedor)).thenReturn(resposta);

        // Chama o método sendo testado
        RespostaResponse result = this.produtoFornecedorController.getProducId(idProdutoFornecedor);

        // Verifica se o serviço simulado foi chamado corretamente e se o resultado foi o esperado
        verify(produtoFornecedorService).getProducId(idProdutoFornecedor);
        assertEquals(resposta, result);
    }

    @Test
    void testGetProducIdException() {
        String idProdutoFornecedor = "123";
        when(produtoFornecedorService.getProducId(idProdutoFornecedor)).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(IdProdutoFornecedorNaoEncontradoException.class, () -> {
            produtoFornecedorController.getProducId(idProdutoFornecedor);
        });

        // Verifica se o serviço foi chamado
        verify(produtoFornecedorService).getProducId(idProdutoFornecedor);
    }


    @Test
    void testCreateProduct() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<produtoFornecedor>\n" +
                "   <idProduto>16023124986</idProduto>\n" +
                "   <idFornecedor>16054055910</idFornecedor>\n" +
                "   <produtoDescricao>Fralda Descartável 54P</produtoDescricao>\n" +
                "   <produtoCodigo>6706322</produtoCodigo>\n" +
                "   <precoCompra>10.6300000000</precoCompra>\n" +
                "   <precoCusto>13.5000000000</precoCusto>\n" +
                "   <produtoGarantia>36</produtoGarantia>\n" +
                "   <padrao>0</padrao>\n" +
                "</produtoFornecedor>";

        // Simula a resposta da chamada para o serviço de categoria
        RespostaRequest resposta = new RespostaRequest();
        RetornoRequest retorno = new RetornoRequest();

        ArrayList<RetornoRequest.Produtosfornecedore> produtosfornecedores = new ArrayList<>();
        RetornoRequest.Produtosfornecedore produtoFornecedor = new RetornoRequest.Produtosfornecedore();

        produtoFornecedor.produtoFornecedor = new ProdutoFornecedor2Request();
        produtoFornecedor.produtoFornecedor.id = "01";
        produtoFornecedor.produtoFornecedor.idProdutoFornecedor = "16023092137";
        produtoFornecedor.produtoFornecedor.idFornecedor = "478963346";
        produtoFornecedor.produtoFornecedor.produtoDescricao = "Descricao do fornecedor";
        produtoFornecedor.produtoFornecedor.produtoCodigo = "159";
        produtoFornecedor.produtoFornecedor.precoCompra = "0.0000000000";
        produtoFornecedor.produtoFornecedor.precoCusto = "1.2300000000";
        produtoFornecedor.produtoFornecedor.produtoGarantia = "4";
        produtoFornecedor.produtoFornecedor.padrao = "1";

        produtosfornecedores.add(produtoFornecedor);
        retorno.setProdutosfornecedores(produtosfornecedores);
        resposta.setRetorno(retorno);

        when(produtoFornecedorService.createProduct(xml)).thenReturn(resposta);

        RespostaRequest result = this.produtoFornecedorController.createProduct(xml);
        verify(produtoFornecedorService).createProduct(xml);
        assertEquals(resposta, result);
    }

    @Test
    void testCreateProductException() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<produtoFornecedor>\n" +
                "   <idProduto>16023124986</idProduto>\n" +
                "   <idFornecedor>16054055910</idFornecedor>\n" +
                "   <produtoDescricao>Fralda Descartável 54P</produtoDescricao>\n" +
                "   <produtoCodigo>6706322</produtoCodigo>\n" +
                "   <precoCompra>10.6300000000</precoCompra>\n" +
                "   <precoCusto>13.5000000000</precoCusto>\n" +
                "   <produtoGarantia>36</produtoGarantia>\n" +
                "   <padrao>0</padrao>\n" +
                "</produtoFornecedor>";

        // Cria um mock do serviço que retorna null
        when(produtoFornecedorService.createProduct(xml)).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(ProdutoFornecedorCadastroException.class, () -> {
            produtoFornecedorController.createProduct(xml);
        });

        // Verifica se o serviço foi chamado
        verify(produtoFornecedorService).createProduct(xml);
    }


    @Test
    void testUpdateProduct() {
//        when(produtoFornecedorService.updateProduct(anyString(), anyString())).thenReturn(new RespostaRequest());
//
//        RespostaRequest result = produtoFornecedorController.updateProduct("xml", "idProdutoFornecedor");
//        Assertions.assertEquals(new RespostaRequest(), result);
    }
}