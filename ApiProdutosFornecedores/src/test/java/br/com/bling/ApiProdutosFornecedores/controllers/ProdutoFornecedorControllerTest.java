package br.com.bling.ApiProdutosFornecedores.controllers;

import br.com.bling.ApiProdutosFornecedores.service.ProdutoFornecedorService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

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

    /**
     * TESTE CONTROLLER - GET "BUSCAR A LISTA DE PRODUTOS FORNECEDORES CADASTRADOS NO BLING".
     */
//    @Test
//    void testGetAllProducts() {
//        // Cria uma resposta simulada do serviço
//        RetornoResponse.Produtosfornecedores produtosfornecedores1 = new RetornoResponse.Produtosfornecedores();
//        produtosfornecedores1.produtofornecedores = new ProdutoFornecedoresResponse();
//        produtosfornecedores1.produtofornecedores.setIdProduto("16023092137");
//
//        produtosfornecedores1.produtofornecedores.fornecedores = new ArrayList<>();
//        produtosfornecedores1.produtofornecedores.fornecedores.add(new FornecedoresResponse());
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor = new ProdutoFornecedorResponse();
//
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setIdProdutoFornecedor("478963346");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setIdFornecedor("0");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setProdutoDescricao("Descricao do fornecedor 1");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setProdutoCodigo("123");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setPrecoCompra("0.0000000000");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setPrecoCusto("1.2300000000");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setProdutoGarantia("4");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setPadrao("1");
//
//        RetornoResponse.Produtosfornecedores produtosfornecedores2 = new RetornoResponse.Produtosfornecedores();
//        produtosfornecedores2.produtofornecedores = new ProdutoFornecedoresResponse();
//        produtosfornecedores2.produtofornecedores.idProduto = "16023092137";
//
//        produtosfornecedores2.produtofornecedores.fornecedores = new ArrayList<>();
//        produtosfornecedores2.produtofornecedores.fornecedores.add(new FornecedoresResponse());
//        produtosfornecedores2.produtofornecedores.fornecedores.get(0).produtoFornecedor = new ProdutoFornecedorResponse();
//
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setIdProdutoFornecedor("478963346");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setIdFornecedor("0");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setProdutoDescricao("Descricao do fornecedor 2");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setProdutoCodigo("123");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setPrecoCompra("0.0000000000");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setPrecoCusto("1.2300000000");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setProdutoGarantia("6");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setPadrao("2");
//
//
//        RetornoResponse retorno = new RetornoResponse();
//        retorno.produtosfornecedores = new ArrayList<>();
//        retorno.produtosfornecedores.add(produtosfornecedores1);
//        retorno.produtosfornecedores.add(produtosfornecedores2);
//
//        JsonResponse resposta = new JsonResponse();
//        resposta.retorno = retorno;
//
//        // Configura o comportamento do serviço simulado
//        when(produtoFornecedorService.getAllProducts()).thenReturn(resposta);
//
//        // Chama o método sendo testado
//        JsonResponse result = this.produtoFornecedorController.getAllProducts().getBody();
//
//        // Verifica se o serviço simulado foi chamado corretamente e se o resultado foi o esperado
//        verify(produtoFornecedorService).getAllProducts();
//        assertEquals(resposta, result);
//    }
//
//    /**
//     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCAR A LISTA DE PRODUTOS FORNECEDORES A ENTRAR NO EXCEPTION".
//     */
//    @Test
//    void testGetAllProductsException() {
//        JsonResponse jsonResponse = new JsonResponse();
//        RetornoResponse retornoResponse = new RetornoResponse();
//
//        retornoResponse.setProdutosfornecedores(null);
//        retornoResponse.setErros(null);
//        jsonResponse.setRetorno(retornoResponse);
//
//        when(produtoFornecedorService.getAllProducts()).thenReturn(jsonResponse);
//
//        // Act
//        assertThrows(ApiProdutoFornecedorException.class, () -> produtoFornecedorController.getAllProducts());
//    }
//
//    /**
//     * TESTE CONTROLLER - GET "BUSCA PRODUTO FORNECEDOR PELO IDPRODUTOFORNECEDOR".
//     */
//    @Test
//    void testGetProducId() {
//        String idProdutoFornecedor = "159357";
//
//        RetornoResponse.Produtosfornecedores produtosfornecedores1 = new RetornoResponse.Produtosfornecedores();
//        produtosfornecedores1.produtofornecedores = new ProdutoFornecedoresResponse();
//        produtosfornecedores1.produtofornecedores.idProduto = "16023092137";
//
//        produtosfornecedores1.produtofornecedores.fornecedores = new ArrayList<>();
//        produtosfornecedores1.produtofornecedores.fornecedores.add(new FornecedoresResponse());
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor = new ProdutoFornecedorResponse();
//
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setIdProdutoFornecedor(idProdutoFornecedor);
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setIdProdutoFornecedor("478963346");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setIdFornecedor("0");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setProdutoDescricao("Descricao do fornecedor 1");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setProdutoCodigo("123");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setPrecoCompra("0.0000000000");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setPrecoCusto("1.2300000000");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setProdutoGarantia("4");
//        produtosfornecedores1.produtofornecedores.fornecedores.get(0).produtoFornecedor.setPadrao("1");
//
//        RetornoResponse retorno = new RetornoResponse();
//        retorno.produtosfornecedores = new ArrayList<>();
//        retorno.produtosfornecedores.add(produtosfornecedores1);
//
//        JsonResponse resposta = new JsonResponse();
//        resposta.setRetorno(retorno);
//
//        // Configura o comportamento do serviço simulado
//        when(produtoFornecedorService.getProducId(idProdutoFornecedor)).thenReturn(resposta);
//
//        // Chama o método sendo testado
//        JsonResponse result = (JsonResponse) this.produtoFornecedorController.getProducId(idProdutoFornecedor).getBody();
//
//        // Verifica se o serviço simulado foi chamado corretamente e se o resultado foi o esperado
//        verify(produtoFornecedorService).getProducId(idProdutoFornecedor);
//        assertEquals(resposta, result);
//    }
//
//    /**
//     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCA PRODUTO FORNECEDOR PELO IDPRODUTOFORNECEDOR A ENTRAR NO EXCEPTION".
//     */
//    @Test
//    void testGetProducIdException() {
//        String idProdutoFornecedor = "753159";
//        JsonResponse jsonResponse = new JsonResponse();
//        RetornoResponse retornoResponse = new RetornoResponse();
//
//        retornoResponse.setProdutosfornecedores(null);
//        retornoResponse.setErros(null);
//        jsonResponse.setRetorno(retornoResponse);
//
//        when(produtoFornecedorService.getProducId(idProdutoFornecedor)).thenReturn(jsonResponse);
//
//        // Act
//        assertThrows(ApiProdutoFornecedorException.class, () -> produtoFornecedorController.getProducId(idProdutoFornecedor));
//    }
//
//    /**
//     * TESTE CONTROLLER - POST "CADASTRA UMA NOVO PRODUTO FORNECEDOR UTILIZANDO XML/JSON".
//     */
//    @Test
//    void testCreateProduct() {
//        // Cria o XML de categoria a ser enviado na requisição
//        String xml = "<produtoFornecedor>\n" +
//                "   <idProduto>16023124986</idProduto>\n" +
//                "   <idFornecedor>16054055910</idFornecedor>\n" +
//                "   <produtoDescricao>Fralda Descartável 54P</produtoDescricao>\n" +
//                "   <produtoCodigo>6706322</produtoCodigo>\n" +
//                "   <precoCompra>10.6300000000</precoCompra>\n" +
//                "   <precoCusto>13.5000000000</precoCusto>\n" +
//                "   <produtoGarantia>36</produtoGarantia>\n" +
//                "   <padrao>0</padrao>\n" +
//                "</produtoFornecedor>";
//
//        // Simula a resposta da chamada para o serviço de categoria
//        JsonRequest resposta = new JsonRequest();
//        RetornoRequest retorno = new RetornoRequest();
//
//        ArrayList<RetornoRequest.Produtosfornecedore> produtosfornecedores = new ArrayList<>();
//        RetornoRequest.Produtosfornecedore produtoFornecedor = new RetornoRequest.Produtosfornecedore();
//
//        produtoFornecedor.produtoFornecedor = new ProdutoFornecedorRequest();
//        produtoFornecedor.produtoFornecedor.id = "01";
//        produtoFornecedor.produtoFornecedor.setIdProdutoFornecedor("16023092137");
//        produtoFornecedor.produtoFornecedor.setIdFornecedor(478963346);
//        produtoFornecedor.produtoFornecedor.setProdutoDescricao("Descricao do fornecedor");
//        produtoFornecedor.produtoFornecedor.setProdutoCodigo("159");
//        produtoFornecedor.produtoFornecedor.setPrecoCompra(BigDecimal.valueOf(0.0000000000));
//        produtoFornecedor.produtoFornecedor.setPrecoCusto(BigDecimal.valueOf(1.2300000000));
//        produtoFornecedor.produtoFornecedor.setProdutoGarantia(4);
//        produtoFornecedor.produtoFornecedor.setPadrao(1);
//
//        produtosfornecedores.add(produtoFornecedor);
//        retorno.setProdutosfornecedores(produtosfornecedores);
//        resposta.setRetorno(retorno);
//
//        when(produtoFornecedorService.createProduct(xml)).thenReturn(resposta);
//
//        JsonRequest result = (JsonRequest) this.produtoFornecedorController.createProduct(xml).getBody();
//        verify(produtoFornecedorService).createProduct(xml);
//        assertEquals(resposta, result);
//    }
//
//    /**
//     * TESTE CONTROLLER - POST "FORÇA O METODO DE CADASTRO DE PRODUTO FORNECEDOR A ENTRAR NO EXCEPTION".
//     */
//    @Test
//    void testUpdateDepositException() {
//        String idCategoria = "159357";
//        String xml = "<categorias>\n" +
//                "     <categoria>\n" +
//                "          <descricao>Calçado</descricao>\n" +
//                "          <idCategoriaPai>0</idCategoriaPai>\n" +
//                "      </categoria>\n" +
//                "   </categorias>";
//
//        JsonRequest jsonRequest = new JsonRequest();
//        RetornoRequest retornoRequest = new RetornoRequest();
//
//        retornoRequest.setProdutosfornecedores(null);
//        retornoRequest.setErros(null);
//        jsonRequest.setRetorno(retornoRequest);
//
//        when(produtoFornecedorService.createProduct(xml)).thenReturn(jsonRequest);
//
//        assertThrows(ApiProdutoFornecedorException.class, () -> produtoFornecedorController.createProduct(xml));
//    }
//
//    /**
//     * TESTE CONTROLLER - PUT "ATUALIZA UM PRODUTO FORNECEDOR UTILIZANDO XML/JSON".
//     */
//    @Test
//    void testUpdateProduct() {
//        // Cria o XML de categoria a ser enviado na requisição
//        String idProdutoFornecedor = "1593560";
//        String xml = "<produtoFornecedor>\n" +
//                "   <idProduto>16023124986</idProduto>\n" +
//                "   <idFornecedor>16054055910</idFornecedor>\n" +
//                "   <produtoDescricao>Fralda Descartável 54P</produtoDescricao>\n" +
//                "   <produtoCodigo>6706322</produtoCodigo>\n" +
//                "   <precoCompra>10.6300000000</precoCompra>\n" +
//                "   <precoCusto>13.5000000000</precoCusto>\n" +
//                "   <produtoGarantia>36</produtoGarantia>\n" +
//                "   <padrao>0</padrao>\n" +
//                "</produtoFornecedor>";
//
//        // Simula a resposta da chamada para o serviço de categoria
//        JsonRequest resposta = new JsonRequest();
//        RetornoRequest retorno = new RetornoRequest();
//
//        ArrayList<RetornoRequest.Produtosfornecedore> produtosfornecedores = new ArrayList<>();
//        RetornoRequest.Produtosfornecedore produtoFornecedor = new RetornoRequest.Produtosfornecedore();
//
//        produtoFornecedor.produtoFornecedor = new ProdutoFornecedorRequest();
//        produtoFornecedor.produtoFornecedor.setId("01");
//        produtoFornecedor.produtoFornecedor.setIdProdutoFornecedor(idProdutoFornecedor);
//        produtoFornecedor.produtoFornecedor.setIdFornecedor(478963346);
//        produtoFornecedor.produtoFornecedor.setProdutoDescricao("Descricao do fornecedor");
//        produtoFornecedor.produtoFornecedor.setProdutoCodigo("159");
//        produtoFornecedor.produtoFornecedor.setPrecoCompra(BigDecimal.valueOf(0.0000000000));
//        produtoFornecedor.produtoFornecedor.setPrecoCusto(BigDecimal.valueOf(1.2300000000));
//        produtoFornecedor.produtoFornecedor.setProdutoGarantia(4);
//        produtoFornecedor.produtoFornecedor.setPadrao(1);
//
//        produtosfornecedores.add(produtoFornecedor);
//        retorno.setProdutosfornecedores(produtosfornecedores);
//        resposta.setRetorno(retorno);
//
//        when(produtoFornecedorService.updateProduct(xml, idProdutoFornecedor)).thenReturn(resposta);
//
//        JsonRequest result = (JsonRequest) this.produtoFornecedorController.updateProduct(xml, idProdutoFornecedor).getBody();
//        verify(produtoFornecedorService).updateProduct(xml, idProdutoFornecedor);
//        assertEquals(resposta, result);
//    }
//
//    /**
//     * TESTE CONTROLLER - PUT "FORÇA O METODO DE ATUALIZAR PRODUTO FORNECEDOR A ENTRAR NO EXCEPTION".
//     */
//    @Test
//    void testUpdatePrductException() {
//        String idProdutoFornecedor = "159357";
//        String xml = "<categorias>\n" +
//                "     <categoria>\n" +
//                "          <descricao>Calçado</descricao>\n" +
//                "          <idCategoriaPai>0</idCategoriaPai>\n" +
//                "      </categoria>\n" +
//                "   </categorias>";
//
//        JsonRequest jsonRequest = new JsonRequest();
//        RetornoRequest retornoRequest = new RetornoRequest();
//
//        retornoRequest.setProdutosfornecedores(null);
//        retornoRequest.setErros(null);
//        jsonRequest.setRetorno(retornoRequest);
//
//        when(produtoFornecedorService.updateProduct(xml, idProdutoFornecedor)).thenReturn(jsonRequest);
//
//        assertThrows(ApiProdutoFornecedorException.class, () -> produtoFornecedorController.updateProduct(xml, idProdutoFornecedor));
//    }
//
//    /**
//     * TESTE CONTROLLER - GET "TESTE DO ERROS MAPEADOS QUE RETORNA DA API EXTERNA".
//     */
//    @Test
//    void testErroResponse() throws JsonProcessingException {
//        int codigoErro = 404;
//        String mensagemErro = "A informação desejada não foi encontrada.";
//
//        DetalhesErroResponse detalhesErro = new DetalhesErroResponse(codigoErro, mensagemErro);
//        ErroResponse erroResponse = new ErroResponse();
//        erroResponse.setErro(detalhesErro);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(erroResponse);
//
//        ErroResponse erroResponseDeserialized = objectMapper.readValue(json, ErroResponse.class);
//
//        assertEquals(erroResponse, erroResponseDeserialized);
//    }
}