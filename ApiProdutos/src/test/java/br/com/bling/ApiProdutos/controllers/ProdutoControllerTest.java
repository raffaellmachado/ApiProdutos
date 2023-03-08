package br.com.bling.ApiProdutos.controllers;

import br.com.bling.ApiProdutos.controllers.request.*;
import br.com.bling.ApiProdutos.controllers.response.CategoriaResponse;
import br.com.bling.ApiProdutos.controllers.response.JsonResponse;
import br.com.bling.ApiProdutos.controllers.response.ProdutoResponse;
import br.com.bling.ApiProdutos.controllers.response.RetornoResponse;
import br.com.bling.ApiProdutos.exceptions.*;
import br.com.bling.ApiProdutos.service.ProdutoService;
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

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProdutoControllerTest {
    @Mock
    ProdutoService produtoService;
    @InjectMocks
    ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {

        RetornoResponse.Produtos produto1 = new RetornoResponse.Produtos();
        produto1.produto = new ProdutoResponse();
        produto1.produto.setId("16023906762");
        produto1.produto.setCodigo("147");
        produto1.produto.setDescricao("Cadastro a mão (Simples), (Serviço)");
        produto1.produto.setTipo("S");
        produto1.produto.setSituacao("Ativo");
        produto1.produto.setUnidade("10");
        produto1.produto.setPreco("10.0000000000");
        produto1.produto.setPrecoCusto("1212.0000000000");
        produto1.produto.setDescricaoCurta("Descrição curta");
        produto1.produto.setDescricaoComplementar("Descrição Complementar");
        produto1.produto.setDataInclusao("2023-03-02");
        produto1.produto.setDataAlteracao("2023-03-02");
        produto1.produto.setImageThumbnail("null");
        produto1.produto.setUrlVideo("http://www.video.com");
        produto1.produto.setNomeFornecedor("Conta fornecedor 01");
        produto1.produto.setCodigoFabricante("21313");
        produto1.produto.setMarca("Tesla");
        produto1.produto.setClass_fiscal("1010.10.10");
        produto1.produto.setCest("28.999.00");
        produto1.produto.setOrigem("0");
        produto1.produto.setIdGrupoProduto("365638");
        produto1.produto.setLinkExterno("https://bling.com.br/Api/v2/contato");
        produto1.produto.setObservacoes("Observações");
        produto1.produto.setGrupoProduto("Grupo de produttos teste");
        produto1.produto.setGarantia("3");
        produto1.produto.setDescricaoFornecedor("Vendedor");
        produto1.produto.setIdFabricante("16054055910");
        /*-------------Categoria-------------*/
        produto1.produto.setCategoria(new CategoriaResponse());
        produto1.produto.getCategoria().setId("6801141");
        produto1.produto.getCategoria().setDescricao("Categoria padrão");
        produto1.produto.getCategoria().setIdCategoriaPai("0");
        /*-----------------------------------*/
        produto1.produto.setPesoLiq("150.00000");
        produto1.produto.setPesoBruto("4.00000");
        produto1.produto.setEstoqueMinimo("500.00");
        produto1.produto.setEstoqueMaximo("200.00");
        produto1.produto.setGtin("1");
        produto1.produto.setGtinEmbalagem("1");
        produto1.produto.setLarguraProduto("13");
        produto1.produto.setAlturaProduto("12");
        produto1.produto.setProfundidadeProduto("11");
        produto1.produto.setUnidadeMedida("Metros");
        produto1.produto.setItensPorCaixa("9");
        produto1.produto.setVolumes("10");
        produto1.produto.setLocalizacao("Bahia");
        produto1.produto.setCrossdocking("100");
        produto1.produto.setCondicao("Recondicionado");
        produto1.produto.setFreteGratis("N");
        produto1.produto.setProducao("P");
        produto1.produto.setDataValidade("2025-10-10");
        produto1.produto.setSpedTipoItem("09");


        // DEPOSITO TESTE 02
        RetornoResponse.Produtos produto2 = new RetornoResponse.Produtos();
        produto2.produto = new ProdutoResponse();
        produto2.produto.setId("16023906762");
        produto2.produto.setCodigo("147");
        produto2.produto.setDescricao("Cadastro a mão (Simples), (Serviço)");
        produto2.produto.setTipo("S");
        produto2.produto.setSituacao("Ativo");
        produto2.produto.setUnidade("10");
        produto2.produto.setPreco("10.0000000000");
        produto2.produto.setPrecoCusto("1212.0000000000");
        produto2.produto.setDescricaoCurta("Descrição curta");
        produto2.produto.setDescricaoComplementar("Descrição Complementar");
        produto2.produto.setDataInclusao("2023-03-02");
        produto2.produto.setDataAlteracao("2023-03-02");
        produto2.produto.setImageThumbnail("null");
        produto2.produto.setUrlVideo("http://www.video.com");
        produto2.produto.setNomeFornecedor("Conta fornecedor 01");
        produto2.produto.setCodigoFabricante("21313");
        produto2.produto.setMarca("Tesla");
        produto2.produto.setClass_fiscal("1010.10.10");
        produto2.produto.setCest("28.999.00");
        produto2.produto.setOrigem("0");
        produto2.produto.setIdGrupoProduto("365638");
        produto2.produto.setLinkExterno("https://bling.com.br/Api/v2/contato");
        produto2.produto.setObservacoes("Observações");
        produto2.produto.setGrupoProduto("Grupo de produttos teste");
        produto2.produto.setGarantia("3");
        produto2.produto.setDescricaoFornecedor("Vendedor");
        produto2.produto.setIdFabricante("16054055910");
        /*-------------Categoria-------------*/
        produto2.produto.setCategoria(new CategoriaResponse());
        produto2.produto.getCategoria().setId("6801141");
        produto2.produto.getCategoria().setDescricao("Categoria padrão");
        produto2.produto.getCategoria().setIdCategoriaPai("0");
        /*-----------------------------------*/
        produto2.produto.setPesoLiq("150.00000");
        produto2.produto.setPesoBruto("4.00000");
        produto2.produto.setEstoqueMinimo("500.00");
        produto2.produto.setEstoqueMaximo("200.00");
        produto2.produto.setGtin("1");
        produto2.produto.setGtinEmbalagem("1");
        produto2.produto.setLarguraProduto("13");
        produto2.produto.setAlturaProduto("12");
        produto2.produto.setProfundidadeProduto("11");
        produto2.produto.setUnidadeMedida("Metros");
        produto2.produto.setItensPorCaixa("9");
        produto2.produto.setVolumes("10");
        produto2.produto.setLocalizacao("Bahia");
        produto2.produto.setCrossdocking("100");
        produto2.produto.setCondicao("Recondicionado");
        produto2.produto.setFreteGratis("N");
        produto2.produto.setProducao("P");
        produto2.produto.setDataValidade("2025-10-10");
        produto2.produto.setSpedTipoItem("09");

        RetornoResponse retorno = new RetornoResponse();
        retorno.produtos = new ArrayList<>();
        retorno.produtos.add(produto1);
        retorno.produtos.add(produto2);

        JsonResponse resposta = new JsonResponse();
        resposta.retorno = retorno;

        // Configura o comportamento do serviço simulado
        when(produtoService.getAllProducts()).thenReturn(resposta);

        // Chama o método sendo testado
        JsonResponse result = produtoController.getAllProducts().getBody();

        // Verifica se o serviço simulado foi chamado corretamente e se o resultado foi o esperado
        verify(produtoService).getAllProducts();
        assertEquals(resposta, result);
    }

    @Test
    void testGetAllProductsException() {

        when(produtoService.getAllProducts()).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(ProdutoListaException.class, () -> {
            produtoController.getAllProducts();
        });

        // Verifica se o serviço foi chamado
        verify(produtoService).getAllProducts();
    }

    @Test
    void testGetProductByCode() {
        String codigo = "825";

        JsonResponse resposta = new JsonResponse();
        RetornoResponse retorno = new RetornoResponse();

        ArrayList<RetornoResponse.Produtos> produtos = new ArrayList<>();
        RetornoResponse.Produtos produto = new RetornoResponse.Produtos();

        produto.produto = new ProdutoResponse();
        produto.produto.setId("16023906762");
        produto.produto.setCodigo(codigo);
        produto.produto.setDescricao("adastro a mão (Simples), (Serviço)");
        produto.produto.setTipo("S");
        produto.produto.setSituacao("Ativo");
        produto.produto.setUnidade("10");
        produto.produto.setPreco("10.0000000000");
        produto.produto.setPrecoCusto("1212.0000000000");
        produto.produto.setDescricaoCurta("Descrição curta");
        produto.produto.setDescricaoComplementar("Descrição Complementar");
        produto.produto.setDataInclusao("2023-03-02");
        produto.produto.setDataAlteracao("2023-03-02");
        produto.produto.setImageThumbnail("null");
        produto.produto.setUrlVideo("http://www.video.com");
        produto.produto.setNomeFornecedor("Conta fornecedor 01");
        produto.produto.setCodigoFabricante("21313");
        produto.produto.setMarca("Tesla");
        produto.produto.setClass_fiscal("1010.10.10");
        produto.produto.setCest("28.999.00");
        produto.produto.setOrigem("0");
        produto.produto.setIdGrupoProduto("365638");
        produto.produto.setLinkExterno("https://bling.com.br/Api/v2/contato");
        produto.produto.setObservacoes("Observações");
        produto.produto.setGrupoProduto("Grupo de produttos teste");
        produto.produto.setGarantia("3");
        produto.produto.setDescricaoFornecedor("Vendedor");
        produto.produto.setIdFabricante("16054055910");
        /*-------------Categoria-------------*/
        produto.produto.setCategoria(new CategoriaResponse());
        produto.produto.getCategoria().setId("6801141");
        produto.produto.getCategoria().setDescricao("Categoria padrão");
        produto.produto.getCategoria().setIdCategoriaPai("0");
        /*-----------------------------------*/
        produto.produto.setPesoLiq("150.00000");
        produto.produto.setPesoBruto("4.00000");
        produto.produto.setEstoqueMinimo("500.00");
        produto.produto.setEstoqueMaximo("200.00");
        produto.produto.setGtin("1");
        produto.produto.setGtinEmbalagem("1");
        produto.produto.setLarguraProduto("13");
        produto.produto.setAlturaProduto("12");
        produto.produto.setProfundidadeProduto("11");
        produto.produto.setUnidadeMedida("Metros");
        produto.produto.setItensPorCaixa("9");
        produto.produto.setVolumes("10");
        produto.produto.setLocalizacao("Bahia");
        produto.produto.setCrossdocking("100");
        produto.produto.setCondicao("Recondicionado");
        produto.produto.setFreteGratis("N");
        produto.produto.setProducao("P");
        produto.produto.setDataValidade("2025-10-10");
        produto.produto.setSpedTipoItem("09");


        produtos.add(produto);
        retorno.setProdutos(produtos);
        resposta.setRetorno(retorno);

        Mockito.when(produtoService.getProductByCode(codigo)).thenReturn(resposta);

        JsonResponse result = produtoController.getProductByCode(codigo).getBody();
        Assertions.assertEquals(resposta, result);
    }

    @Test
    void testGetProductByCodeException() {
        String codigo = "123";
        when(produtoService.getProductByCode(codigo)).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(ProdutoCodigoException.class, () -> {
            produtoController.getProductByCode(codigo);
        });

        // Verifica se o serviço foi chamado
        verify(produtoService).getProductByCode(codigo);
    }

    @Test
    void testGetProductByCodeSupplier() {

        String codigo = "825";
        String nomeFornecedor = "Teste fornecedor";

        JsonResponse resposta = new JsonResponse();
        RetornoResponse retorno = new RetornoResponse();

        ArrayList<RetornoResponse.Produtos> produtos = new ArrayList<>();
        RetornoResponse.Produtos produto = new RetornoResponse.Produtos();

        produto.produto = new ProdutoResponse();
        produto.produto.setId("16023906762");
        produto.produto.setCodigo(codigo);
        produto.produto.setDescricao("adastro a mão (Simples), (Serviço)");
        produto.produto.setTipo("S");
        produto.produto.setSituacao("Ativo");
        produto.produto.setUnidade("10");
        produto.produto.setPreco("10.0000000000");
        produto.produto.setPrecoCusto("1212.0000000000");
        produto.produto.setDescricaoCurta("Descrição curta");
        produto.produto.setDescricaoComplementar("Descrição Complementar");
        produto.produto.setDataInclusao("2023-03-02");
        produto.produto.setDataAlteracao("2023-03-02");
        produto.produto.setImageThumbnail("null");
        produto.produto.setUrlVideo("http://www.video.com");
        produto.produto.setNomeFornecedor(nomeFornecedor);
        produto.produto.setCodigoFabricante("21313");
        produto.produto.setMarca("Tesla");
        produto.produto.setClass_fiscal("1010.10.10");
        produto.produto.setCest("28.999.00");
        produto.produto.setOrigem("0");
        produto.produto.setIdGrupoProduto("365638");
        produto.produto.setLinkExterno("https://bling.com.br/Api/v2/contato");
        produto.produto.setObservacoes("Observações");
        produto.produto.setGrupoProduto("Grupo de produttos teste");
        produto.produto.setGarantia("3");
        produto.produto.setDescricaoFornecedor("Vendedor");
        produto.produto.setIdFabricante("16054055910");
        /*-------------Categoria-------------*/
        produto.produto.setCategoria(new CategoriaResponse());
        produto.produto.getCategoria().setId("6801141");
        produto.produto.getCategoria().setDescricao("Categoria padrão");
        produto.produto.getCategoria().setIdCategoriaPai("0");
        /*-----------------------------------*/
        produto.produto.setPesoLiq("150.00000");
        produto.produto.setPesoBruto("4.00000");
        produto.produto.setEstoqueMinimo("500.00");
        produto.produto.setEstoqueMaximo("200.00");
        produto.produto.setGtin("1");
        produto.produto.setGtinEmbalagem("1");
        produto.produto.setLarguraProduto("13");
        produto.produto.setAlturaProduto("12");
        produto.produto.setProfundidadeProduto("11");
        produto.produto.setUnidadeMedida("Metros");
        produto.produto.setItensPorCaixa("9");
        produto.produto.setVolumes("10");
        produto.produto.setLocalizacao("Bahia");
        produto.produto.setCrossdocking("100");
        produto.produto.setCondicao("Recondicionado");
        produto.produto.setFreteGratis("N");
        produto.produto.setProducao("P");
        produto.produto.setDataValidade("2025-10-10");
        produto.produto.setSpedTipoItem("09");

        produtos.add(produto);
        retorno.setProdutos(produtos);
        resposta.setRetorno(retorno);

        Mockito.when(produtoService.getProductByCodeSupplier(codigo,nomeFornecedor)).thenReturn(resposta);

        JsonResponse result = produtoController.getProductByCodeSupplier(codigo, nomeFornecedor).getBody();
        Assertions.assertEquals(resposta, result);
    }

    @Test
    void testGetProductByCodeSupplierException() {
        String codigo = "123";
        String id_fornecedor = "Teste";
        when(produtoService.getProductByCodeSupplier(codigo, id_fornecedor)).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(ProdutoCodigoFornecedorException.class, () -> {
            produtoController.getProductByCodeSupplier(codigo, id_fornecedor);
        });

        // Verifica se o serviço foi chamado
        verify(produtoService).getProductByCodeSupplier(codigo, id_fornecedor);
    }

    @Test
    void testDeleteProductByCode() {
        String codigo = "825";

        // Cria resposta simulada do serviço
        JsonResponse resposta = new JsonResponse();
        RetornoResponse retorno = new RetornoResponse();
        ArrayList<RetornoResponse.Produtos> produtos = new ArrayList<>();
        RetornoResponse.Produtos produto = new RetornoResponse.Produtos();

        produto.produto = new ProdutoResponse();
        produto.produto.setId("16023906762");
        produto.produto.setCodigo(codigo);

        produtos.add(produto);
        retorno.setProdutos(produtos);
        resposta.setRetorno(retorno);

        // Configura comportamento simulado do serviço
        Mockito.when(produtoService.getProductByCode(codigo)).thenReturn(resposta);

        // Chama o método que será testado
        Object result = (produtoController.deleteProductByCode(codigo)).getBody();

        // Verifica o resultado
        String expected = "Produto com o código " + codigo + " foi deletado com sucesso!";
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testDeleteProductByCodeException() throws Exception {
        String codigo = "12345";
        when(produtoService.getProductByCode(codigo)).thenReturn(null);

        // Chama o método sendo testado
        ProdutoExclusaoException thrown = assertThrows(
                ProdutoExclusaoException.class,
                () -> produtoController.deleteProductByCode(codigo)
        );

        // Verifica se a mensagem da exceção é a esperada
        assertEquals("Produto com código " + codigo + " não encontrado para exclusão", thrown.getMessage());

        // Verifica se o serviço foi chamado
        verify(produtoService).getProductByCode(codigo);
    }

    @Test
    void testCreateProduct() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<produto>\n" +
                "  <id>16023906762</id>\n" +
                "  <codigo>10</codigo>\n" +
                "  <codigoItem>Cadastro a mão (Simples), (Serviço)</codigoItem>\n" +
                "  <descricao>S</descricao>\n" +
                "  <tipo>Ativo</tipo>\n" +
                "  <situacao>10</situacao>\n" +
                "  <descricaoCurta>10.0000000000</descricaoCurta>\n" +
                "  <descricaoComplementar>1212.0000000000</descricaoComplementar>\n" +
                "  <vlr_unit>10.00</vlr_unit>\n" +
                "  <preco_custo>Descrição Complementar</preco_custo>\n" +
                "  <peso_bruto>2023-03-02</peso_bruto>\n" +
                "  <peso_liq>2023-03-02</peso_liq>\n" +
                "  <class_fiscal>null</class_fiscal>\n" +
                "  <marca>http://www.video.com</marca>\n" +
                "  <cest>nomeFornecedor</cest>\n" +
                "  <origem>21313</origem>\n" +
                "  <idGrupoProduto>Tesla</idGrupoProduto>\n" +
                "  <condicao>Recondicionado</condicao>\n" +
                "  <freteGratis>28.999.00</freteGratis>\n" +
                "  <linkExterno>0</linkExterno>\n" +
                "  <observacoes>365638</observacoes>\n" +
                "  <producao>https://bling.com.br/Api/v2/contato</producao>\n" +
                "  <unidadeMedida>Observações</unidadeMedida>\n" +
                "  <dataValidade>Grupo de produttos teste</dataValidade>\n" +
                "  <descricaoFornecedor>3</descricaoFornecedor>\n" +
                "  <idFabricante>Vendedor</idFabricante>\n" +
                "  <codigoFabricante>16054055910</codigoFabricante>\n" +
                "  <deposito>\n" +
                "    <id>6801141</id>\n" +
                "    <estoque>Categoria padrão</estoque>\n" +
                "  </deposito>\n" +
                "  <gtin>150.00000</gtin>\n" +
                "  <gtinEmbalagem>4.00000</gtinEmbalagem>\n" +
                "  <largura>500.00</largura>\n" +
                "  <altura>200.00</altura>\n" +
                "  <profundidade>1</profundidade>\n" +
                "  <estoqueMinimo>1</estoqueMinimo>\n" +
                "  <estoqueMaximo>13</estoqueMaximo>\n" +
                "  <itensPorCaixa>12</itensPorCaixa>\n" +
                "  <volumes>11</volumes>\n" +
                "  <urlVideo>Metros</urlVideo>\n" +
                "  <localizacao>9</localizacao>\n" +
                "  <crossdocking>10</crossdocking>\n" +
                "  <garantia>Bahia</garantia>\n" +
                "  <spedTipoItem>100</spedTipoItem>\n" +
                "  <variacoes>\n" +
                "    <variacao>\n" +
                "      <nome>1</nome>\n" +
                "      <codigo>1</codigo>\n" +
                "      <vlr_unit>1</vlr_unit>\n" +
                "      <clonarDadosPai>1</clonarDadosPai>\n" +
                "      <estoque>1</estoque>\n" +
                "      <deposito>\n" +
                "        <id>1</id>\n" +
                "        <estoque>1</estoque>\n" +
                "      </deposito>\n" +
                "    </variacao>\n" +
                "  </variacoes>   \n" +
                "  <imagens>\n" +
                "      <url>6801141</url>\n" +
                "  </imagens>\n" +
                "  <campos_customizados>\n" +
                "      <alias>6801141</alias>\n" +
                "  </campos_customizados>\n" +
                "  <condicao>Recondicionado</condicao>\n" +
                "  <id_categoria>1</id_categoria>\n" +
                "  <estrutura>\n" +
                "      <tipo_estoque>6801141</tipo_estoque>\n" +
                "      <lancar_estoque>6801141</lancar_estoque>\n" +
                "      <componente>\n" +
                "          <nome>1</nome>\n" +
                "          <codigo>Vendedor</codigo>\n" +
                "          <quantidade>1</quantidade>\n" +
                "      </componente>\n" +
                "  </estrutura>\n" +
                "</produto>";

        // Simula a resposta da chamada para o serviço de categoria
        JsonRequest resposta = new JsonRequest();
        RetornoRequest retorno = new RetornoRequest();

        ArrayList<RetornoRequest.Produtos> produtoList = new ArrayList<>();
        RetornoRequest.Produtos produto = new RetornoRequest.Produtos();

        produto.produto = new ProdutoRequest();
        produto.produto.setId("16023906762");
        produto.produto.setCodigo("150");
        produto.produto.setCodigoItem(10);
        produto.produto.setDescricao("S");
        produto.produto.setTipo("Ativo");
        produto.produto.setSituacao("10");
        produto.produto.setDescricaoCurta("10.0000000000");
        produto.produto.setDescricaoComplementar("1212.0000000000");
        produto.produto.setVlr_unit(BigDecimal.valueOf(10.00));
        produto.produto.setPreco_custo(BigDecimal.valueOf(10));
        produto.produto.setPeso_bruto(BigDecimal.valueOf(2023));
        produto.produto.setPeso_liq(BigDecimal.valueOf(2023));
        produto.produto.setClass_fiscal("null");
        produto.produto.setMarca("http://www.video.com");
        produto.produto.setCest("nomeFornecedor");
        produto.produto.setOrigem("21313");
        produto.produto.setIdGrupoProduto(BigDecimal.valueOf(555));
        produto.produto.setCondicao("1010.10.10");
        produto.produto.setFreteGratis("28.999.00");
        produto.produto.setLinkExterno("0");
        produto.produto.setObservacoes("365638");
        produto.produto.setProducao("https://bling.com.br/Api/v2/contato");
        produto.produto.setUnidadeMedida("Observações");
        produto.produto.setDataValidade("10.10.2022");
        produto.produto.setDescricaoFornecedor("3");
        produto.produto.setIdFabricante(BigDecimal.valueOf(258));
        produto.produto.setCodigoFabricante("1025");
        /*-----------------------------------*/
        produto.produto.setDeposito(new DepositoRequest());
        produto.produto.getDeposito().setId(BigDecimal.valueOf(6801141));
        produto.produto.getDeposito().setEstoque(BigDecimal.valueOf(852));
        /*-----------------------------------*/
        produto.produto.setGtin("150");
        produto.produto.setGtinEmbalagem("4");
        produto.produto.setLargura("500");
        produto.produto.setAltura("200");
        produto.produto.setProfundidade("1");
        produto.produto.setEstoqueMinimo(BigDecimal.valueOf(1));
        produto.produto.setEstoqueMaximo(BigDecimal.valueOf(13));
        produto.produto.setItensPorCaixa(BigDecimal.valueOf(12));
        produto.produto.setVolumes(BigDecimal.valueOf(11));
        produto.produto.setUrlVideo("Metros");
        produto.produto.setLocalizacao("9");
        produto.produto.setCrossdocking(BigDecimal.valueOf(10));
        produto.produto.setGarantia(85);
        produto.produto.setSpedTipoItem(159);
        /*-----------------------------------*/
        produto.produto.setVariacoes(new VariacoesRequest());
        produto.produto.getVariacoes().setVariacao(new ArrayList<>());
        VariacaoRequest variacao = new VariacaoRequest();
        variacao.setNome("1");
        variacao.setCodigo("1");
        variacao.setVlr_unit(BigDecimal.valueOf(1));
        variacao.setClonarDadosPai("1");
        variacao.setEstoque(BigDecimal.valueOf(1));
        variacao.setDeposito(new DepositoRequest());
        variacao.getDeposito().setId(BigDecimal.valueOf(1));
        variacao.getDeposito().setEstoque(BigDecimal.valueOf(1));
        produto.produto.getVariacoes().getVariacao().add(variacao);
        /*-----------------------------------*/
        produto.produto.setImagens(new ArrayList<>()); // Inicializa a lista de imagens
        produto.produto.getImagens().add(new ImagemRequest("6801141"));
        /*-----------------------------------*/
        produto.produto.setCamposCustomizados(new CamposCustomizadosRequest());
        produto.produto.getCamposCustomizados().setAlias("6801141");
        /*-----------------------------------*/
        produto.produto.setCondicao("Recondicionado");
        produto.produto.setIdCategoria(BigDecimal.valueOf(1));
        produto.produto.setEstrutura(new EstruturaRequest());
        produto.produto.getEstrutura().setTipoEstoque("6801141");
        produto.produto.getEstrutura().setLancarEstoque("6801141");
        produto.produto.getEstrutura().setComponente(new ArrayList<>());
        ComponenteRequest componentes = new ComponenteRequest();
        componentes.setNome("1");
        componentes.setCodigo("Vendedor");
        componentes.setQuantidade(BigDecimal.valueOf(1));
        produto.produto.getEstrutura().getComponente().add(componentes);

        produtoList.add(produto);
        retorno.setProdutos(produtoList);
        resposta.setRetorno(retorno);

        when(produtoService.createProduct(xml)).thenReturn(resposta);

        JsonRequest result = (JsonRequest) produtoController.createProduct(xml).getBody();
        assertEquals(resposta, result);
    }

    @Test
    void testCreateProductException_1() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<xml>...</xml>";
        when(produtoService.createProduct(xml)).thenReturn(null);

        // Chama o método sendo testado
        ResponseEntity<?> response = produtoController.createProduct(xml);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Verifica se o serviço foi chamado
        verify(produtoService).createProduct(xml);
    }

    @Test
    void testCreateCategoryException_2() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<xml>...</xml>";

        // Cria um mock do serviço que lança uma HttpStatusCodeException
        when(produtoService.createProduct(xml)).thenThrow(new HttpStatusCodeException(HttpStatus.NOT_FOUND) {});

        // Chama o método sendo testado e verifica se a resposta é a esperada
        ResponseEntity<?> response = produtoController.createProduct(xml);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(new JsonRequest(), response.getBody());

        // Verifica se o serviço foi chamado
        verify(produtoService).createProduct(xml);
    }

    @Test
    void testCreateCategoryException_3() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<xml>...</xml>";

        // Cria um mock do serviço que lança uma exceção
        when(produtoService.createProduct(xml)).thenThrow(new RuntimeException());

        // Chama o método sendo testado e espera a exceção correta
        ResponseEntity<?> response = produtoController.createProduct(xml);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verifica se o serviço foi chamado
        verify(produtoService).createProduct(xml);
    }

    @Test
    void testUpdateProduct() {
        String codigo = "10";
        String xml = "<produto>\n" +
                "  <id>16023906762</id>\n" +
                "  <codigo>10</codigo>\n" +
                "  <codigoItem>Cadastro a mão (Simples), (Serviço)</codigoItem>\n" +
                "  <descricao>S</descricao>\n" +
                "  <tipo>Ativo</tipo>\n" +
                "  <situacao>10</situacao>\n" +
                "  <descricaoCurta>10.0000000000</descricaoCurta>\n" +
                "  <descricaoComplementar>1212.0000000000</descricaoComplementar>\n" +
                "  <vlr_unit>10.00</vlr_unit>\n" +
                "  <preco_custo>Descrição Complementar</preco_custo>\n" +
                "  <peso_bruto>2023-03-02</peso_bruto>\n" +
                "  <peso_liq>2023-03-02</peso_liq>\n" +
                "  <class_fiscal>null</class_fiscal>\n" +
                "  <marca>http://www.video.com</marca>\n" +
                "  <cest>nomeFornecedor</cest>\n" +
                "  <origem>21313</origem>\n" +
                "  <idGrupoProduto>Tesla</idGrupoProduto>\n" +
                "  <condicao>Recondicionado</condicao>\n" +
                "  <freteGratis>28.999.00</freteGratis>\n" +
                "  <linkExterno>0</linkExterno>\n" +
                "  <observacoes>365638</observacoes>\n" +
                "  <producao>https://bling.com.br/Api/v2/contato</producao>\n" +
                "  <unidadeMedida>Observações</unidadeMedida>\n" +
                "  <dataValidade>Grupo de produttos teste</dataValidade>\n" +
                "  <descricaoFornecedor>3</descricaoFornecedor>\n" +
                "  <idFabricante>Vendedor</idFabricante>\n" +
                "  <codigoFabricante>16054055910</codigoFabricante>\n" +
                "  <deposito>\n" +
                "    <id>6801141</id>\n" +
                "    <estoque>Categoria padrão</estoque>\n" +
                "  </deposito>\n" +
                "  <gtin>150.00000</gtin>\n" +
                "  <gtinEmbalagem>4.00000</gtinEmbalagem>\n" +
                "  <largura>500.00</largura>\n" +
                "  <altura>200.00</altura>\n" +
                "  <profundidade>1</profundidade>\n" +
                "  <estoqueMinimo>1</estoqueMinimo>\n" +
                "  <estoqueMaximo>13</estoqueMaximo>\n" +
                "  <itensPorCaixa>12</itensPorCaixa>\n" +
                "  <volumes>11</volumes>\n" +
                "  <urlVideo>Metros</urlVideo>\n" +
                "  <localizacao>9</localizacao>\n" +
                "  <crossdocking>10</crossdocking>\n" +
                "  <garantia>Bahia</garantia>\n" +
                "  <spedTipoItem>100</spedTipoItem>\n" +
                "  <variacoes>\n" +
                "    <variacao>\n" +
                "      <nome>1</nome>\n" +
                "      <codigo>1</codigo>\n" +
                "      <vlr_unit>1</vlr_unit>\n" +
                "      <clonarDadosPai>1</clonarDadosPai>\n" +
                "      <estoque>1</estoque>\n" +
                "      <deposito>\n" +
                "        <id>1</id>\n" +
                "        <estoque>1</estoque>\n" +
                "      </deposito>\n" +
                "    </variacao>\n" +
                "  </variacoes>   \n" +
                "  <imagens>\n" +
                "      <url>6801141</url>\n" +
                "  </imagens>\n" +
                "  <campos_customizados>\n" +
                "      <alias>6801141</alias>\n" +
                "  </campos_customizados>\n" +
                "  <condicao>Recondicionado</condicao>\n" +
                "  <id_categoria>1</id_categoria>\n" +
                "  <estrutura>\n" +
                "      <tipo_estoque>6801141</tipo_estoque>\n" +
                "      <lancar_estoque>6801141</lancar_estoque>\n" +
                "      <componente>\n" +
                "          <nome>1</nome>\n" +
                "          <codigo>Vendedor</codigo>\n" +
                "          <quantidade>1</quantidade>\n" +
                "      </componente>\n" +
                "  </estrutura>\n" +
                "</produto>";

        // Simula a resposta da chamada para o serviço de categoria
        JsonRequest resposta = new JsonRequest();
        RetornoRequest retorno = new RetornoRequest();

        ArrayList<RetornoRequest.Produtos> produtoList = new ArrayList<>();
        RetornoRequest.Produtos produto = new RetornoRequest.Produtos();

        produto.produto = new ProdutoRequest();
        produto.produto.setId("16023906762");
        produto.produto.setCodigo("150");
        produto.produto.setCodigoItem(10);
        produto.produto.setDescricao("S");
        produto.produto.setTipo("Ativo");
        produto.produto.setSituacao("10");
        produto.produto.setDescricaoCurta("10.0000000000");
        produto.produto.setDescricaoComplementar("1212.0000000000");
        produto.produto.setVlr_unit(BigDecimal.valueOf(10.00));
        produto.produto.setPreco_custo(BigDecimal.valueOf(10));
        produto.produto.setPeso_bruto(BigDecimal.valueOf(2023));
        produto.produto.setPeso_liq(BigDecimal.valueOf(2023));
        produto.produto.setClass_fiscal("null");
        produto.produto.setMarca("http://www.video.com");
        produto.produto.setCest("nomeFornecedor");
        produto.produto.setOrigem("21313");
        produto.produto.setIdGrupoProduto(BigDecimal.valueOf(555));
        produto.produto.setCondicao("1010.10.10");
        produto.produto.setFreteGratis("28.999.00");
        produto.produto.setLinkExterno("0");
        produto.produto.setObservacoes("365638");
        produto.produto.setProducao("https://bling.com.br/Api/v2/contato");
        produto.produto.setUnidadeMedida("Observações");
        produto.produto.setDataValidade("10.10.2022");
        produto.produto.setDescricaoFornecedor("3");
        produto.produto.setIdFabricante(BigDecimal.valueOf(258));
        produto.produto.setCodigoFabricante("1025");
        /*-----------------------------------*/
        produto.produto.setDeposito(new DepositoRequest());
        produto.produto.getDeposito().setId(BigDecimal.valueOf(6801141));
        produto.produto.getDeposito().setEstoque(BigDecimal.valueOf(852));
        /*-----------------------------------*/
        produto.produto.setGtin("150");
        produto.produto.setGtinEmbalagem("4");
        produto.produto.setLargura("500");
        produto.produto.setAltura("200");
        produto.produto.setProfundidade("1");
        produto.produto.setEstoqueMinimo(BigDecimal.valueOf(1));
        produto.produto.setEstoqueMaximo(BigDecimal.valueOf(13));
        produto.produto.setItensPorCaixa(BigDecimal.valueOf(12));
        produto.produto.setVolumes(BigDecimal.valueOf(11));
        produto.produto.setUrlVideo("Metros");
        produto.produto.setLocalizacao("9");
        produto.produto.setCrossdocking(BigDecimal.valueOf(10));
        produto.produto.setGarantia(85);
        produto.produto.setSpedTipoItem(159);
        /*-----------------------------------*/
        produto.produto.setVariacoes(new VariacoesRequest());
        produto.produto.getVariacoes().setVariacao(new ArrayList<>());
        VariacaoRequest variacao = new VariacaoRequest();
        variacao.setNome("1");
        variacao.setCodigo("1");
        variacao.setVlr_unit(BigDecimal.valueOf(1));
        variacao.setClonarDadosPai("1");
        variacao.setEstoque(BigDecimal.valueOf(1));
        variacao.setDeposito(new DepositoRequest());
        variacao.getDeposito().setId(BigDecimal.valueOf(1));
        variacao.getDeposito().setEstoque(BigDecimal.valueOf(1));
        produto.produto.getVariacoes().getVariacao().add(variacao);
        /*-----------------------------------*/
        produto.produto.setImagens(new ArrayList<>()); // Inicializa a lista de imagens
        produto.produto.getImagens().add(new ImagemRequest("6801141"));
        /*-----------------------------------*/
        produto.produto.setCamposCustomizados(new CamposCustomizadosRequest());
        produto.produto.getCamposCustomizados().setAlias("6801141");
        /*-----------------------------------*/
        produto.produto.setCondicao("Recondicionado");
        produto.produto.setIdCategoria(BigDecimal.valueOf(1));
        produto.produto.setEstrutura(new EstruturaRequest());
        produto.produto.getEstrutura().setTipoEstoque("6801141");
        produto.produto.getEstrutura().setLancarEstoque("6801141");
        produto.produto.getEstrutura().setComponente(new ArrayList<>());
        ComponenteRequest componentes = new ComponenteRequest();
        componentes.setNome("1");
        componentes.setCodigo("Vendedor");
        componentes.setQuantidade(BigDecimal.valueOf(1));
        produto.produto.getEstrutura().getComponente().add(componentes);

        produtoList.add(produto);
        retorno.setProdutos(produtoList);
        resposta.setRetorno(retorno);

        produtoList.add(produto);
        retorno.setProdutos(produtoList);
        resposta.setRetorno(retorno);

        when(produtoService.updateProduct(xml, codigo)).thenReturn(resposta);

        JsonRequest result = produtoController.updateProduct(xml,codigo).getBody();
        assertEquals(resposta, result);
    }

    @Test
    void testUpdateProductException() {
        String xml = "<xml>...</xml>";
        String codigo = "1257";
        when(produtoService.updateProduct(xml, codigo)).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(ProdutoAtualizarException.class, () -> {
            produtoController.updateProduct(xml, codigo);
        });

        // Verifica se o serviço foi chamado
        verify(produtoService).updateProduct(xml, codigo);
    }
}