package br.com.bling.ApiProdutos.service;

import br.com.bling.ApiProdutos.controllers.request.JsonRequest;
import br.com.bling.ApiProdutos.controllers.response.JsonResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class ProdutoServiceImplTest {
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    ProdutoServiceImpl produtoServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtos\":[{\"produto\":{\"id\":\"01\",\"codigo\":\"CAD00011\",\"descricao\":\"CADEIRA XYZ F5\",\"tipo\":\"P\",\"situacao\":\"A\",\"unidade\":\"01\",\"preco\":\"100.0000000000\",\"precoCusto\":\"85.0000000000\",\"descricaoCurta\":\"CADEIRA XYZ\",\"descricaoComplementar\":\"CADEIRA em detalhes\",\"dataInclusao\":\"2016-08-17\",\"dataAlteracao\":\"2016-08-17\",\"imageThumbnail\":\"01\",\"urlVideo\":\"https://www.youtube.com/watch?v=zKKL-SgC5lY\",\"nomeFornecedor\":\"Bling\",\"codigoFabricante\":\"01\",\"marca\":\"Marca de teste\",\"class_fiscal\":\"1234.56.78\",\"cest\":\"28.040.00\",\"origem\":\"1\",\"idGrupoProduto\":\"12345\",\"linkExterno\":\"https://minhaloja.com.br/cadeira-xyz-f5\",\"observacoes\":\"Observações da CADEIRA XYZ F5\",\"grupoProduto\":\"Móveis\",\"garantia\":\"4\",\"descricaoFornecedor\":\"Descrição do fornecedor\",\"idFabricante\":\"01\",\"categoria\":{\"id\":\"51212592\",\"descricao\":\"Geral\"},\"pesoLiq\":\"85.0000000000\",\"pesoBruto\":\"1.000\",\"estoqueMinimo\":\"1.000\",\"estoqueMaximo\":\"1.000\",\"gtin\":\"01\",\"gtinEmbalagem\":\"01\",\"larguraProduto\":\"10\",\"alturaProduto\":\"89\",\"profundidadeProduto\":\"67\",\"unidadeMedida\":\"Centímetros\",\"itensPorCaixa\":\"2\",\"volumes\":\"2\",\"localizacao\":\"Prateleira B\",\"crossdocking\":\"2\",\"condicao\":\"Novo\",\"freteGratis\":\"S\",\"producao\":\"P\",\"dataValidade\":\"2019-11-20\",\"spedTipoItem\":\"01\"}},{\"produto\":{\"id\":\"01\",\"codigo\":\"CAD00011\",\"descricao\":\"CADEIRA XYZ F5\",\"tipo\":\"P\",\"situacao\":\"A\",\"unidade\":\"01\",\"preco\":\"100.0000000000\",\"precoCusto\":\"85.0000000000\",\"descricaoCurta\":\"CADEIRA XYZ\",\"descricaoComplementar\":\"CADEIRA em detalhes\",\"dataInclusao\":\"2016-08-17\",\"dataAlteracao\":\"2016-08-17\",\"imageThumbnail\":\"01\",\"urlVideo\":\"https://www.youtube.com/watch?v=zKKL-SgC5lY\",\"nomeFornecedor\":\"Bling\",\"codigoFabricante\":\"01\",\"marca\":\"Marca de teste\",\"class_fiscal\":\"1234.56.78\",\"cest\":\"28.040.00\",\"origem\":\"1\",\"idGrupoProduto\":\"12345\",\"linkExterno\":\"https://minhaloja.com.br/cadeira-xyz-f5\",\"observacoes\":\"Observações da CADEIRA XYZ F5\",\"grupoProduto\":\"Móveis\",\"garantia\":\"4\",\"descricaoFornecedor\":\"Descrição do fornecedor\",\"idFabricante\":\"01\",\"categoria\":{\"id\":\"51212592\",\"descricao\":\"Geral\"},\"pesoLiq\":\"85.0000000000\",\"pesoBruto\":\"1.000\",\"estoqueMinimo\":\"1.000\",\"estoqueMaximo\":\"1.000\",\"gtin\":\"01\",\"gtinEmbalagem\":\"01\",\"larguraProduto\":\"10\",\"alturaProduto\":\"89\",\"profundidadeProduto\":\"67\",\"unidadeMedida\":\"Centímetros\",\"itensPorCaixa\":\"2\",\"volumes\":\"2\",\"localizacao\":\"Prateleira B\",\"crossdocking\":\"2\",\"condicao\":\"Novo\",\"freteGratis\":\"S\",\"producao\":\"P\",\"dataValidade\":\"2019-11-20\",\"spedTipoItem\":\"01\"}}]}}";
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);

        JsonResponse result = produtoServiceImpl.getAllProducts();

        // Verifica se a lista de categorias foi corretamente convertida a partir da resposta da API
        Assertions.assertEquals(2, result.getRetorno().getProdutos().size());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getId());
        Assertions.assertEquals("CAD00011", result.getRetorno().getProdutos().get(0).getProduto().getCodigo());
        Assertions.assertEquals("CADEIRA XYZ F5", result.getRetorno().getProdutos().get(0).getProduto().getDescricao());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getTipo());
        Assertions.assertEquals("A", result.getRetorno().getProdutos().get(0).getProduto().getSituacao());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getUnidade());
        Assertions.assertEquals("100.0000000000", result.getRetorno().getProdutos().get(0).getProduto().getPreco());
        Assertions.assertEquals("85.0000000000", result.getRetorno().getProdutos().get(0).getProduto().getPrecoCusto());
        Assertions.assertEquals("CADEIRA XYZ", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoCurta());
        Assertions.assertEquals("CADEIRA em detalhes", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoComplementar());
        Assertions.assertEquals("2016-08-17", result.getRetorno().getProdutos().get(0).getProduto().getDataInclusao());
        Assertions.assertEquals("2016-08-17", result.getRetorno().getProdutos().get(0).getProduto().getDataAlteracao());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getImageThumbnail());
        Assertions.assertEquals("https://www.youtube.com/watch?v=zKKL-SgC5lY", result.getRetorno().getProdutos().get(0).getProduto().getUrlVideo());
        Assertions.assertEquals("Bling", result.getRetorno().getProdutos().get(0).getProduto().getNomeFornecedor());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getCodigoFabricante());
        Assertions.assertEquals("Marca de teste", result.getRetorno().getProdutos().get(0).getProduto().getMarca());
        Assertions.assertEquals("1234.56.78", result.getRetorno().getProdutos().get(0).getProduto().getClass_fiscal());
        Assertions.assertEquals("28.040.00", result.getRetorno().getProdutos().get(0).getProduto().getCest());
        Assertions.assertEquals("1", result.getRetorno().getProdutos().get(0).getProduto().getOrigem());
        Assertions.assertEquals("12345", result.getRetorno().getProdutos().get(0).getProduto().getIdGrupoProduto());
        Assertions.assertEquals("https://minhaloja.com.br/cadeira-xyz-f5", result.getRetorno().getProdutos().get(0).getProduto().getLinkExterno());
        Assertions.assertEquals("Observações da CADEIRA XYZ F5", result.getRetorno().getProdutos().get(0).getProduto().getObservacoes());
        Assertions.assertEquals("Móveis", result.getRetorno().getProdutos().get(0).getProduto().getGrupoProduto());
        Assertions.assertEquals("4", result.getRetorno().getProdutos().get(0).getProduto().getGarantia());
        Assertions.assertEquals("Descrição do fornecedor", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoFornecedor());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getIdFabricante());
        /* CategoriaRequest---------------------------------------------------------------------------------------------------------------------------------*/
        Assertions.assertEquals("51212592", result.getRetorno().getProdutos().get(0).getProduto().getCategoria().getId());
        Assertions.assertEquals("Geral", result.getRetorno().getProdutos().get(0).getProduto().getCategoria().getDescricao());
        /* -------------------------------------------------------------------------------------------------------------------------------------------------*/
        Assertions.assertEquals("85.0000000000", result.getRetorno().getProdutos().get(0).getProduto().getPesoLiq());
        Assertions.assertEquals("1.000", result.getRetorno().getProdutos().get(0).getProduto().getPesoBruto());
        Assertions.assertEquals("1.000", result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMinimo());
        Assertions.assertEquals("1.000", result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMaximo());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getGtin());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getGtinEmbalagem());
        Assertions.assertEquals("10", result.getRetorno().getProdutos().get(0).getProduto().getLarguraProduto());
        Assertions.assertEquals("89", result.getRetorno().getProdutos().get(0).getProduto().getAlturaProduto());
        Assertions.assertEquals("67", result.getRetorno().getProdutos().get(0).getProduto().getProfundidadeProduto());
        Assertions.assertEquals("Centímetros", result.getRetorno().getProdutos().get(0).getProduto().getUnidadeMedida());
        Assertions.assertEquals("2", result.getRetorno().getProdutos().get(0).getProduto().getItensPorCaixa());
        Assertions.assertEquals("2", result.getRetorno().getProdutos().get(0).getProduto().getVolumes());
        Assertions.assertEquals("Prateleira B", result.getRetorno().getProdutos().get(0).getProduto().getLocalizacao());
        Assertions.assertEquals("2", result.getRetorno().getProdutos().get(0).getProduto().getCrossdocking());
        Assertions.assertEquals("Novo", result.getRetorno().getProdutos().get(0).getProduto().getCondicao());
        Assertions.assertEquals("S", result.getRetorno().getProdutos().get(0).getProduto().getFreteGratis());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getProducao());
        Assertions.assertEquals("2019-11-20", result.getRetorno().getProdutos().get(0).getProduto().getDataValidade());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getSpedTipoItem());


        // LISTA DE CONTATO 2
        Assertions.assertEquals(2, result.getRetorno().getProdutos().size());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getId());
        Assertions.assertEquals("CAD00011", result.getRetorno().getProdutos().get(0).getProduto().getCodigo());
        Assertions.assertEquals("CADEIRA XYZ F5", result.getRetorno().getProdutos().get(0).getProduto().getDescricao());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getTipo());
        Assertions.assertEquals("A", result.getRetorno().getProdutos().get(0).getProduto().getSituacao());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getUnidade());
        Assertions.assertEquals("100.0000000000", result.getRetorno().getProdutos().get(0).getProduto().getPreco());
        Assertions.assertEquals("85.0000000000", result.getRetorno().getProdutos().get(0).getProduto().getPrecoCusto());
        Assertions.assertEquals("CADEIRA XYZ", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoCurta());
        Assertions.assertEquals("CADEIRA em detalhes", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoComplementar());
        Assertions.assertEquals("2016-08-17", result.getRetorno().getProdutos().get(0).getProduto().getDataInclusao());
        Assertions.assertEquals("2016-08-17", result.getRetorno().getProdutos().get(0).getProduto().getDataAlteracao());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getImageThumbnail());
        Assertions.assertEquals("https://www.youtube.com/watch?v=zKKL-SgC5lY", result.getRetorno().getProdutos().get(0).getProduto().getUrlVideo());
        Assertions.assertEquals("Bling", result.getRetorno().getProdutos().get(0).getProduto().getNomeFornecedor());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getCodigoFabricante());
        Assertions.assertEquals("Marca de teste", result.getRetorno().getProdutos().get(0).getProduto().getMarca());
        Assertions.assertEquals("1234.56.78", result.getRetorno().getProdutos().get(0).getProduto().getClass_fiscal());
        Assertions.assertEquals("28.040.00", result.getRetorno().getProdutos().get(0).getProduto().getCest());
        Assertions.assertEquals("1", result.getRetorno().getProdutos().get(0).getProduto().getOrigem());
        Assertions.assertEquals("12345", result.getRetorno().getProdutos().get(0).getProduto().getIdGrupoProduto());
        Assertions.assertEquals("https://minhaloja.com.br/cadeira-xyz-f5", result.getRetorno().getProdutos().get(0).getProduto().getLinkExterno());
        Assertions.assertEquals("Observações da CADEIRA XYZ F5", result.getRetorno().getProdutos().get(0).getProduto().getObservacoes());
        Assertions.assertEquals("Móveis", result.getRetorno().getProdutos().get(0).getProduto().getGrupoProduto());
        Assertions.assertEquals("4", result.getRetorno().getProdutos().get(0).getProduto().getGarantia());
        Assertions.assertEquals("Descrição do fornecedor", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoFornecedor());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getIdFabricante());
        /* CategoriaRequest---------------------------------------------------------------------------------------------------------------------------------*/
        Assertions.assertEquals("51212592", result.getRetorno().getProdutos().get(0).getProduto().getCategoria().getId());
        Assertions.assertEquals("Geral", result.getRetorno().getProdutos().get(0).getProduto().getCategoria().getDescricao());
        /* -------------------------------------------------------------------------------------------------------------------------------------------------*/
        Assertions.assertEquals("85.0000000000", result.getRetorno().getProdutos().get(0).getProduto().getPesoLiq());
        Assertions.assertEquals("1.000", result.getRetorno().getProdutos().get(0).getProduto().getPesoBruto());
        Assertions.assertEquals("1.000", result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMinimo());
        Assertions.assertEquals("1.000", result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMaximo());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getGtin());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getGtinEmbalagem());
        Assertions.assertEquals("10", result.getRetorno().getProdutos().get(0).getProduto().getLarguraProduto());
        Assertions.assertEquals("89", result.getRetorno().getProdutos().get(0).getProduto().getAlturaProduto());
        Assertions.assertEquals("67", result.getRetorno().getProdutos().get(0).getProduto().getProfundidadeProduto());
        Assertions.assertEquals("Centímetros", result.getRetorno().getProdutos().get(0).getProduto().getUnidadeMedida());
        Assertions.assertEquals("2", result.getRetorno().getProdutos().get(0).getProduto().getItensPorCaixa());
        Assertions.assertEquals("2", result.getRetorno().getProdutos().get(0).getProduto().getVolumes());
        Assertions.assertEquals("Prateleira B", result.getRetorno().getProdutos().get(0).getProduto().getLocalizacao());
        Assertions.assertEquals("2", result.getRetorno().getProdutos().get(0).getProduto().getCrossdocking());
        Assertions.assertEquals("Novo", result.getRetorno().getProdutos().get(0).getProduto().getCondicao());
        Assertions.assertEquals("S", result.getRetorno().getProdutos().get(0).getProduto().getFreteGratis());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getProducao());
        Assertions.assertEquals("2019-11-20", result.getRetorno().getProdutos().get(0).getProduto().getDataValidade());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getSpedTipoItem());

        System.out.println("GET LIST: " + result);
    }

    @Test
    void testGetProductByCode() {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtos\":[{\"produto\":{\"id\":\"01\",\"codigo\":\"CAD00011\",\"descricao\":\"CADEIRA XYZ F5\",\"tipo\":\"P\",\"situacao\":\"A\",\"unidade\":\"01\",\"preco\":\"100.0000000000\",\"precoCusto\":\"85.0000000000\",\"descricaoCurta\":\"CADEIRA XYZ\",\"descricaoComplementar\":\"CADEIRA em detalhes\",\"dataInclusao\":\"2016-08-17\",\"dataAlteracao\":\"2016-08-17\",\"imageThumbnail\":\"01\",\"urlVideo\":\"https://www.youtube.com/watch?v=zKKL-SgC5lY\",\"nomeFornecedor\":\"Bling\",\"codigoFabricante\":\"01\",\"marca\":\"Marca de teste\",\"class_fiscal\":\"1234.56.78\",\"cest\":\"28.040.00\",\"origem\":\"1\",\"idGrupoProduto\":\"12345\",\"linkExterno\":\"https://minhaloja.com.br/cadeira-xyz-f5\",\"observacoes\":\"Observações da CADEIRA XYZ F5\",\"grupoProduto\":\"Móveis\",\"garantia\":\"4\",\"descricaoFornecedor\":\"Descrição do fornecedor\",\"idFabricante\":\"01\",\"categoria\":{\"id\":\"51212592\",\"descricao\":\"Geral\"},\"pesoLiq\":\"85.0000000000\",\"pesoBruto\":\"1.000\",\"estoqueMinimo\":\"1.000\",\"estoqueMaximo\":\"1.000\",\"gtin\":\"01\",\"gtinEmbalagem\":\"01\",\"larguraProduto\":\"10\",\"alturaProduto\":\"89\",\"profundidadeProduto\":\"67\",\"unidadeMedida\":\"Centímetros\",\"itensPorCaixa\":\"2\",\"volumes\":\"2\",\"localizacao\":\"Prateleira B\",\"crossdocking\":\"2\",\"condicao\":\"Novo\",\"freteGratis\":\"S\",\"producao\":\"P\",\"dataValidade\":\"2019-11-20\",\"spedTipoItem\":\"01\"}}]}}";
        String codigo = "150";
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);

        JsonResponse result = produtoServiceImpl.getProductByCode(codigo);

        // Verifica se a lista de categorias foi corretamente convertida a partir da resposta da API
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getId());
        Assertions.assertEquals("CAD00011", result.getRetorno().getProdutos().get(0).getProduto().getCodigo());
        Assertions.assertEquals("CADEIRA XYZ F5", result.getRetorno().getProdutos().get(0).getProduto().getDescricao());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getTipo());
        Assertions.assertEquals("A", result.getRetorno().getProdutos().get(0).getProduto().getSituacao());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getUnidade());
        Assertions.assertEquals("100.0000000000", result.getRetorno().getProdutos().get(0).getProduto().getPreco());
        Assertions.assertEquals("85.0000000000", result.getRetorno().getProdutos().get(0).getProduto().getPrecoCusto());
        Assertions.assertEquals("CADEIRA XYZ", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoCurta());
        Assertions.assertEquals("CADEIRA em detalhes", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoComplementar());
        Assertions.assertEquals("2016-08-17", result.getRetorno().getProdutos().get(0).getProduto().getDataInclusao());
        Assertions.assertEquals("2016-08-17", result.getRetorno().getProdutos().get(0).getProduto().getDataAlteracao());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getImageThumbnail());
        Assertions.assertEquals("https://www.youtube.com/watch?v=zKKL-SgC5lY", result.getRetorno().getProdutos().get(0).getProduto().getUrlVideo());
        Assertions.assertEquals("Bling", result.getRetorno().getProdutos().get(0).getProduto().getNomeFornecedor());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getCodigoFabricante());
        Assertions.assertEquals("Marca de teste", result.getRetorno().getProdutos().get(0).getProduto().getMarca());
        Assertions.assertEquals("1234.56.78", result.getRetorno().getProdutos().get(0).getProduto().getClass_fiscal());
        Assertions.assertEquals("28.040.00", result.getRetorno().getProdutos().get(0).getProduto().getCest());
        Assertions.assertEquals("1", result.getRetorno().getProdutos().get(0).getProduto().getOrigem());
        Assertions.assertEquals("12345", result.getRetorno().getProdutos().get(0).getProduto().getIdGrupoProduto());
        Assertions.assertEquals("https://minhaloja.com.br/cadeira-xyz-f5", result.getRetorno().getProdutos().get(0).getProduto().getLinkExterno());
        Assertions.assertEquals("Observações da CADEIRA XYZ F5", result.getRetorno().getProdutos().get(0).getProduto().getObservacoes());
        Assertions.assertEquals("Móveis", result.getRetorno().getProdutos().get(0).getProduto().getGrupoProduto());
        Assertions.assertEquals("4", result.getRetorno().getProdutos().get(0).getProduto().getGarantia());
        Assertions.assertEquals("Descrição do fornecedor", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoFornecedor());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getIdFabricante());
        /* CategoriaRequest---------------------------------------------------------------------------------------------------------------------------------*/
        Assertions.assertEquals("51212592", result.getRetorno().getProdutos().get(0).getProduto().getCategoria().getId());
        Assertions.assertEquals("Geral", result.getRetorno().getProdutos().get(0).getProduto().getCategoria().getDescricao());
        /* -------------------------------------------------------------------------------------------------------------------------------------------------*/
        Assertions.assertEquals("85.0000000000", result.getRetorno().getProdutos().get(0).getProduto().getPesoLiq());
        Assertions.assertEquals("1.000", result.getRetorno().getProdutos().get(0).getProduto().getPesoBruto());
        Assertions.assertEquals("1.000", result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMinimo());
        Assertions.assertEquals("1.000", result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMaximo());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getGtin());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getGtinEmbalagem());
        Assertions.assertEquals("10", result.getRetorno().getProdutos().get(0).getProduto().getLarguraProduto());
        Assertions.assertEquals("89", result.getRetorno().getProdutos().get(0).getProduto().getAlturaProduto());
        Assertions.assertEquals("67", result.getRetorno().getProdutos().get(0).getProduto().getProfundidadeProduto());
        Assertions.assertEquals("Centímetros", result.getRetorno().getProdutos().get(0).getProduto().getUnidadeMedida());
        Assertions.assertEquals("2", result.getRetorno().getProdutos().get(0).getProduto().getItensPorCaixa());
        Assertions.assertEquals("2", result.getRetorno().getProdutos().get(0).getProduto().getVolumes());
        Assertions.assertEquals("Prateleira B", result.getRetorno().getProdutos().get(0).getProduto().getLocalizacao());
        Assertions.assertEquals("2", result.getRetorno().getProdutos().get(0).getProduto().getCrossdocking());
        Assertions.assertEquals("Novo", result.getRetorno().getProdutos().get(0).getProduto().getCondicao());
        Assertions.assertEquals("S", result.getRetorno().getProdutos().get(0).getProduto().getFreteGratis());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getProducao());
        Assertions.assertEquals("2019-11-20", result.getRetorno().getProdutos().get(0).getProduto().getDataValidade());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getSpedTipoItem());

        System.out.println("TEST GET ID: " + result);

    }

    @Test
    void testGetProductByCodeSupplier() {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtos\":[{\"produto\":{\"id\":\"01\",\"codigo\":\"CAD00011\",\"descricao\":\"CADEIRA XYZ F5\",\"tipo\":\"P\",\"situacao\":\"A\",\"unidade\":\"01\",\"preco\":\"100.0000000000\",\"precoCusto\":\"85.0000000000\",\"descricaoCurta\":\"CADEIRA XYZ\",\"descricaoComplementar\":\"CADEIRA em detalhes\",\"dataInclusao\":\"2016-08-17\",\"dataAlteracao\":\"2016-08-17\",\"imageThumbnail\":\"01\",\"urlVideo\":\"https://www.youtube.com/watch?v=zKKL-SgC5lY\",\"nomeFornecedor\":\"Bling\",\"codigoFabricante\":\"01\",\"marca\":\"Marca de teste\",\"class_fiscal\":\"1234.56.78\",\"cest\":\"28.040.00\",\"origem\":\"1\",\"idGrupoProduto\":\"12345\",\"linkExterno\":\"https://minhaloja.com.br/cadeira-xyz-f5\",\"observacoes\":\"Observações da CADEIRA XYZ F5\",\"grupoProduto\":\"Móveis\",\"garantia\":\"4\",\"descricaoFornecedor\":\"Descrição do fornecedor\",\"idFabricante\":\"01\",\"categoria\":{\"id\":\"51212592\",\"descricao\":\"Geral\"},\"pesoLiq\":\"85.0000000000\",\"pesoBruto\":\"1.000\",\"estoqueMinimo\":\"1.000\",\"estoqueMaximo\":\"1.000\",\"gtin\":\"01\",\"gtinEmbalagem\":\"01\",\"larguraProduto\":\"10\",\"alturaProduto\":\"89\",\"profundidadeProduto\":\"67\",\"unidadeMedida\":\"Centímetros\",\"itensPorCaixa\":\"2\",\"volumes\":\"2\",\"localizacao\":\"Prateleira B\",\"crossdocking\":\"2\",\"condicao\":\"Novo\",\"freteGratis\":\"S\",\"producao\":\"P\",\"dataValidade\":\"2019-11-20\",\"spedTipoItem\":\"01\"}}]}}";
        String codigo = "150";
        String idFornecedor = "159";
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);

        JsonResponse result = produtoServiceImpl.getProductByCodeSupplier(codigo, idFornecedor);

        // Verifica se a lista de categorias foi corretamente convertida a partir da resposta da API
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getId());
        Assertions.assertEquals("CAD00011", result.getRetorno().getProdutos().get(0).getProduto().getCodigo());
        Assertions.assertEquals("CADEIRA XYZ F5", result.getRetorno().getProdutos().get(0).getProduto().getDescricao());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getTipo());
        Assertions.assertEquals("A", result.getRetorno().getProdutos().get(0).getProduto().getSituacao());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getUnidade());
        Assertions.assertEquals("100.0000000000", result.getRetorno().getProdutos().get(0).getProduto().getPreco());
        Assertions.assertEquals("85.0000000000", result.getRetorno().getProdutos().get(0).getProduto().getPrecoCusto());
        Assertions.assertEquals("CADEIRA XYZ", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoCurta());
        Assertions.assertEquals("CADEIRA em detalhes", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoComplementar());
        Assertions.assertEquals("2016-08-17", result.getRetorno().getProdutos().get(0).getProduto().getDataInclusao());
        Assertions.assertEquals("2016-08-17", result.getRetorno().getProdutos().get(0).getProduto().getDataAlteracao());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getImageThumbnail());
        Assertions.assertEquals("https://www.youtube.com/watch?v=zKKL-SgC5lY", result.getRetorno().getProdutos().get(0).getProduto().getUrlVideo());
        Assertions.assertEquals("Bling", result.getRetorno().getProdutos().get(0).getProduto().getNomeFornecedor());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getCodigoFabricante());
        Assertions.assertEquals("Marca de teste", result.getRetorno().getProdutos().get(0).getProduto().getMarca());
        Assertions.assertEquals("1234.56.78", result.getRetorno().getProdutos().get(0).getProduto().getClass_fiscal());
        Assertions.assertEquals("28.040.00", result.getRetorno().getProdutos().get(0).getProduto().getCest());
        Assertions.assertEquals("1", result.getRetorno().getProdutos().get(0).getProduto().getOrigem());
        Assertions.assertEquals("12345", result.getRetorno().getProdutos().get(0).getProduto().getIdGrupoProduto());
        Assertions.assertEquals("https://minhaloja.com.br/cadeira-xyz-f5", result.getRetorno().getProdutos().get(0).getProduto().getLinkExterno());
        Assertions.assertEquals("Observações da CADEIRA XYZ F5", result.getRetorno().getProdutos().get(0).getProduto().getObservacoes());
        Assertions.assertEquals("Móveis", result.getRetorno().getProdutos().get(0).getProduto().getGrupoProduto());
        Assertions.assertEquals("4", result.getRetorno().getProdutos().get(0).getProduto().getGarantia());
        Assertions.assertEquals("Descrição do fornecedor", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoFornecedor());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getIdFabricante());
        /* CategoriaRequest---------------------------------------------------------------------------------------------------------------------------------*/
        Assertions.assertEquals("51212592", result.getRetorno().getProdutos().get(0).getProduto().getCategoria().getId());
        Assertions.assertEquals("Geral", result.getRetorno().getProdutos().get(0).getProduto().getCategoria().getDescricao());
        /* -------------------------------------------------------------------------------------------------------------------------------------------------*/
        Assertions.assertEquals("85.0000000000", result.getRetorno().getProdutos().get(0).getProduto().getPesoLiq());
        Assertions.assertEquals("1.000", result.getRetorno().getProdutos().get(0).getProduto().getPesoBruto());
        Assertions.assertEquals("1.000", result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMinimo());
        Assertions.assertEquals("1.000", result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMaximo());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getGtin());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getGtinEmbalagem());
        Assertions.assertEquals("10", result.getRetorno().getProdutos().get(0).getProduto().getLarguraProduto());
        Assertions.assertEquals("89", result.getRetorno().getProdutos().get(0).getProduto().getAlturaProduto());
        Assertions.assertEquals("67", result.getRetorno().getProdutos().get(0).getProduto().getProfundidadeProduto());
        Assertions.assertEquals("Centímetros", result.getRetorno().getProdutos().get(0).getProduto().getUnidadeMedida());
        Assertions.assertEquals("2", result.getRetorno().getProdutos().get(0).getProduto().getItensPorCaixa());
        Assertions.assertEquals("2", result.getRetorno().getProdutos().get(0).getProduto().getVolumes());
        Assertions.assertEquals("Prateleira B", result.getRetorno().getProdutos().get(0).getProduto().getLocalizacao());
        Assertions.assertEquals("2", result.getRetorno().getProdutos().get(0).getProduto().getCrossdocking());
        Assertions.assertEquals("Novo", result.getRetorno().getProdutos().get(0).getProduto().getCondicao());
        Assertions.assertEquals("S", result.getRetorno().getProdutos().get(0).getProduto().getFreteGratis());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getProducao());
        Assertions.assertEquals("2019-11-20", result.getRetorno().getProdutos().get(0).getProduto().getDataValidade());
        Assertions.assertEquals("01", result.getRetorno().getProdutos().get(0).getProduto().getSpedTipoItem());

        System.out.println("TEST GET ID e IDFORNECEDOR: " + result);

    }

    @Test
    void testDeleteProductByCode() {
        String codigo = "333";

        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtos\":[{\"produto\":{\"id\":\"01\",\"codigo\":\"" + codigo + "\",\"codigoItem\":\"01\",\"descricao\":\"01\",\"tipo\":\"01\",\"situacao\":\"01\",\"descricaoCurta\":\"01\",\"descricaoComplementar\":\"01\",\"un\":\"01\",\"vlr_unit\":\"01\",\"preco_custo\":\"01\",\"peso_bruto\":\"01\",\"peso_liq\":\"01\",\"class_fiscal\":\"01\",\"marca\":\"01\",\"cest\":\"01\",\"origem\":\"01\",\"idGrupoProduto\":\"01\",\"condicao\":\"01\",\"freteGratis\":\"01\",\"linkExterno\":\"01\",\"observacoes\":\"01\",\"producao\":\"01\",\"unidadeMedida\":\"01\",\"dataValidade\":\"01\",\"descricaoFornecedor\":\"01\",\"idFabricante\":\"01\",\"codigoFabricante\":\"01\",\"deposito\":{\"id\":\"01\",\"estoque\":\"01\"},\"gtin\":\"01\",\"gtinEmbalagem\":\"01\",\"largura\":\"01\",\"altura\":\"01\",\"profundidade\":\"01\",\"estoqueMinimo\":\"01\",\"estoqueMaximo\":\"01\",\"itensPorCaixa\":\"01\",\"volumes\":\"01\",\"urlVideo\":\"01\",\"localizacao\":\"01\",\"crossdocking\":\"01\",\"garantia\":\"01\",\"spedTipoItem\":\"01\",\"idCategoria\":\"01\"}}]}}";
        when(restTemplate.exchange(anyString(), eq(HttpMethod.DELETE), any(), eq(String.class))).thenReturn(new ResponseEntity<>(jsonResponse, HttpStatus.OK));

        // Chama o método que deleta o produto pelo código
        assertDoesNotThrow(() -> produtoServiceImpl.deleteProductByCode(codigo));

        System.out.println("TEST DELETE: " + "Código deletado no teste: " + codigo);
    }

    @Test
    void testCreateProductSimple() {     //TESTE PRODUTO SIMPLES
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtos\":[{\"produto\":{\"codigo\":223435780,\"descricao\":\"Caneta 001\",\"situacao\":\"Ativo\",\"descricaoCurta\":\"Descrição curta da caneta\",\"descricaoComplementar\":\"Descrição complementar da caneta\",\"un\":\"Pc\",\"vlr_unit\":1.68,\"preco_custo\":1.23,\"peso_bruto\":0.2,\"peso_liq\":0.18,\"class_fiscal\":\"1000.01.01\",\"marca\":\"Marca da Caneta\",\"origem\":0,\"estoque\":10,\"deposito\":{\"id\":123456,\"estoque\":200},\"gtin\":223435780,\"gtinEmbalagem\":54546,\"largura\":11,\"altura\":21,\"profundidade\":31,\"estoqueMinimo\":1,\"estoqueMaximo\":100,\"cest\":\"28.040.00\",\"idGrupoProduto\":12345,\"condicao\":\"Novo\",\"freteGratis\":\"N\",\"linkExterno\":\"https://minhaloja.com.br/meu-produto\",\"observacoes\":\"Observações do meu produtos\",\"producao\":\"P\",\"dataValidade\":\"20/11/2019\",\"descricaoFornecedor\":\"Descrição do fornecedor\",\"idFabricante\":0,\"codigoFabricante\":123,\"unidadeMedida\":\"Centímetros\",\"garantia\":4,\"itensPorCaixa\":2,\"volumes\":2,\"urlVideo\":\"https://www.youtube.com/watch?v=zKKL-SgC5lY\",\"imagens\":[{\"url\":\"https://bling.com.br/bling.jpg\"}],\"camposCustomizados\":{\"alias\":16},\"idCategoria\":1234}}]}}";
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        JsonRequest result = produtoServiceImpl.createProduct("xml");

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        Assertions.assertEquals("223435780", result.getRetorno().getProdutos().get(0).getProduto().getCodigo());
        Assertions.assertEquals("Caneta 001", result.getRetorno().getProdutos().get(0).getProduto().getDescricao());
        Assertions.assertEquals("Ativo", result.getRetorno().getProdutos().get(0).getProduto().getSituacao());
        Assertions.assertEquals("Descrição curta da caneta", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoCurta());
        Assertions.assertEquals("Descrição complementar da caneta", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoComplementar());
        Assertions.assertEquals("Pc", result.getRetorno().getProdutos().get(0).getProduto().getUn());
        Assertions.assertEquals(BigDecimal.valueOf(1.68), result.getRetorno().getProdutos().get(0).getProduto().getVlr_unit());
        Assertions.assertEquals(BigDecimal.valueOf(1.23), result.getRetorno().getProdutos().get(0).getProduto().getPreco_custo());
        Assertions.assertEquals(BigDecimal.valueOf(0.2), result.getRetorno().getProdutos().get(0).getProduto().getPeso_bruto());
        Assertions.assertEquals(BigDecimal.valueOf(0.18), result.getRetorno().getProdutos().get(0).getProduto().getPeso_liq());
        Assertions.assertEquals("1000.01.01", result.getRetorno().getProdutos().get(0).getProduto().getClass_fiscal());
        Assertions.assertEquals("Marca da Caneta", result.getRetorno().getProdutos().get(0).getProduto().getMarca());
        Assertions.assertEquals("0", result.getRetorno().getProdutos().get(0).getProduto().getOrigem());
        Assertions.assertEquals(10, result.getRetorno().getProdutos().get(0).getProduto().getEstoque());
        //DepositoRequest
        Assertions.assertEquals(BigDecimal.valueOf(123456), result.getRetorno().getProdutos().get(0).getProduto().getDeposito().getId());
        Assertions.assertEquals(BigDecimal.valueOf(200), result.getRetorno().getProdutos().get(0).getProduto().getDeposito().getEstoque());
        /*---------------------------------------------------------------------------------------------------------------------*/
        Assertions.assertEquals("223435780", result.getRetorno().getProdutos().get(0).getProduto().getGtin());
        Assertions.assertEquals("54546", result.getRetorno().getProdutos().get(0).getProduto().getGtinEmbalagem());
        Assertions.assertEquals("11", result.getRetorno().getProdutos().get(0).getProduto().getLargura());
        Assertions.assertEquals("21", result.getRetorno().getProdutos().get(0).getProduto().getAltura());
        Assertions.assertEquals("31", result.getRetorno().getProdutos().get(0).getProduto().getProfundidade());
        Assertions.assertEquals(BigDecimal.valueOf(1), result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMinimo());
        Assertions.assertEquals(BigDecimal.valueOf(100), result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMaximo());
        Assertions.assertEquals("28.040.00", result.getRetorno().getProdutos().get(0).getProduto().getCest());
        Assertions.assertEquals(BigDecimal.valueOf(12345), result.getRetorno().getProdutos().get(0).getProduto().getIdGrupoProduto());
        Assertions.assertEquals("Novo", result.getRetorno().getProdutos().get(0).getProduto().getCondicao());
        Assertions.assertEquals("N", result.getRetorno().getProdutos().get(0).getProduto().getFreteGratis());
        Assertions.assertEquals("https://minhaloja.com.br/meu-produto", result.getRetorno().getProdutos().get(0).getProduto().getLinkExterno());
        Assertions.assertEquals("Observações do meu produtos", result.getRetorno().getProdutos().get(0).getProduto().getObservacoes());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getProducao());
        Assertions.assertEquals("20/11/2019", result.getRetorno().getProdutos().get(0).getProduto().getDataValidade());
        Assertions.assertEquals("Descrição do fornecedor", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoFornecedor());
        Assertions.assertEquals(BigDecimal.valueOf(0), result.getRetorno().getProdutos().get(0).getProduto().getIdFabricante());
        Assertions.assertEquals("123", result.getRetorno().getProdutos().get(0).getProduto().getCodigoFabricante());
        Assertions.assertEquals("Centímetros", result.getRetorno().getProdutos().get(0).getProduto().getUnidadeMedida());
        Assertions.assertEquals(4, result.getRetorno().getProdutos().get(0).getProduto().getGarantia());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getItensPorCaixa());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getVolumes());
        Assertions.assertEquals("https://www.youtube.com/watch?v=zKKL-SgC5lY", result.getRetorno().getProdutos().get(0).getProduto().getUrlVideo());
        //ImagemRequest
        Assertions.assertEquals("https://bling.com.br/bling.jpg", result.getRetorno().getProdutos().get(0).getProduto().getImagens().get(0).getUrl());
        //CamposCustomizadosReques
        Assertions.assertEquals("16", result.getRetorno().getProdutos().get(0).getProduto().getCamposCustomizados().getAlias());
        /*---------------------------------------------------------------------------------------------------------------------*/
        Assertions.assertEquals(BigDecimal.valueOf(1234), result.getRetorno().getProdutos().get(0).getProduto().getIdCategoria());

        System.out.println("TEST POST CREATE PRODUCT SIMPLE: " + result);
    }

    @Test
    void testCreateProductVariation() {     //TESTE PRODUTO COM VARIAÇÃO.
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtos\":[{\"produto\":{\"codigo\":223435780,\"descricao\":\"Caneta 001\",\"descricaoCurta\":\"Descrição curta da caneta\",\"descricaoComplementar\":\"Descrição complementar da caneta\",\"un\":\"Pc\",\"vlr_unit\":1.68,\"preco_custo\":1.23,\"peso_bruto\":0.2,\"peso_liq\":0.18,\"class_fiscal\":\"1000.01.01\",\"marca\":\"Marca da Caneta\",\"origem\":0,\"gtin\":223435780,\"gtinEmbalagem\":54546,\"largura\":11,\"altura\":21,\"profundidade\":31,\"estoqueMinimo\":1,\"estoqueMaximo\":100,\"cest\":\"28.040.00\",\"idGrupoProduto\":12345,\"condicao\":\"Usado\",\"freteGratis\":\"S\",\"linkExterno\":\"https://minhaloja.com.br/meu-produto\",\"observacoes\":\"Observações do meu produto\",\"producao\":\"P\",\"dataValidade\":\"20/11/2019\",\"descricaoFornecedor\":\"Descrição do fornecedor\",\"idFabricante\":0,\"codigoFabricante\":123,\"unidadeMedida\":\"Centímetros\",\"crossdocking\":2,\"garantia\":4,\"itensPorCaixa\":2,\"volumes\":2,\"urlVideo\":\"https://www.youtube.com/watch?v=zKKL-SgC5lY\",\"idCategoria\":1234,\"variacoes\":{\"variacao\":[{\"nome\":\"Cor:Preto\",\"codigo\":\"223435780\",\"clonarDadosPai\":\"S\",\"vlr_unit\":150,\"estoque\":120,\"un\":\"1\",\"deposito\":{\"id\":123456,\"estoque\":200}}]},\"imagens\":[{\"url\":\"https://www.bling.com.br/bling.jpg\"}]}}]}}";
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        JsonRequest result = produtoServiceImpl.createProduct("xml");

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        Assertions.assertEquals("223435780", result.getRetorno().getProdutos().get(0).getProduto().getCodigo());
        Assertions.assertEquals("Caneta 001", result.getRetorno().getProdutos().get(0).getProduto().getDescricao());
        Assertions.assertEquals("Descrição curta da caneta", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoCurta());
        Assertions.assertEquals("Descrição complementar da caneta", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoComplementar());
        Assertions.assertEquals("Pc", result.getRetorno().getProdutos().get(0).getProduto().getUn());
        Assertions.assertEquals(BigDecimal.valueOf(1.68), result.getRetorno().getProdutos().get(0).getProduto().getVlr_unit());
        Assertions.assertEquals(BigDecimal.valueOf(1.23), result.getRetorno().getProdutos().get(0).getProduto().getPreco_custo());
        Assertions.assertEquals(BigDecimal.valueOf(0.2), result.getRetorno().getProdutos().get(0).getProduto().getPeso_bruto());
        Assertions.assertEquals(BigDecimal.valueOf(0.18), result.getRetorno().getProdutos().get(0).getProduto().getPeso_liq());
        Assertions.assertEquals("1000.01.01", result.getRetorno().getProdutos().get(0).getProduto().getClass_fiscal());
        Assertions.assertEquals("Marca da Caneta", result.getRetorno().getProdutos().get(0).getProduto().getMarca());
        Assertions.assertEquals("0", result.getRetorno().getProdutos().get(0).getProduto().getOrigem());
        Assertions.assertEquals("223435780", result.getRetorno().getProdutos().get(0).getProduto().getGtin());
        Assertions.assertEquals("54546", result.getRetorno().getProdutos().get(0).getProduto().getGtinEmbalagem());
        Assertions.assertEquals("11", result.getRetorno().getProdutos().get(0).getProduto().getLargura());
        Assertions.assertEquals("21", result.getRetorno().getProdutos().get(0).getProduto().getAltura());
        Assertions.assertEquals("31", result.getRetorno().getProdutos().get(0).getProduto().getProfundidade());
        Assertions.assertEquals(BigDecimal.valueOf(1), result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMinimo());
        Assertions.assertEquals(BigDecimal.valueOf(100), result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMaximo());
        Assertions.assertEquals("1000.01.01", result.getRetorno().getProdutos().get(0).getProduto().getClass_fiscal());
        Assertions.assertEquals("28.040.00", result.getRetorno().getProdutos().get(0).getProduto().getCest());
        Assertions.assertEquals(BigDecimal.valueOf(12345), result.getRetorno().getProdutos().get(0).getProduto().getIdGrupoProduto());
        Assertions.assertEquals("Usado", result.getRetorno().getProdutos().get(0).getProduto().getCondicao());
        Assertions.assertEquals("S", result.getRetorno().getProdutos().get(0).getProduto().getFreteGratis());
        Assertions.assertEquals("https://minhaloja.com.br/meu-produto", result.getRetorno().getProdutos().get(0).getProduto().getLinkExterno());
        Assertions.assertEquals("Observações do meu produto", result.getRetorno().getProdutos().get(0).getProduto().getObservacoes());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getProducao());
        Assertions.assertEquals("20/11/2019", result.getRetorno().getProdutos().get(0).getProduto().getDataValidade());
        Assertions.assertEquals("Descrição do fornecedor", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoFornecedor());
        Assertions.assertEquals(BigDecimal.valueOf(0), result.getRetorno().getProdutos().get(0).getProduto().getIdFabricante());
        Assertions.assertEquals("123", result.getRetorno().getProdutos().get(0).getProduto().getCodigoFabricante());
        Assertions.assertEquals("Centímetros", result.getRetorno().getProdutos().get(0).getProduto().getUnidadeMedida());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getCrossdocking());
        Assertions.assertEquals(4, result.getRetorno().getProdutos().get(0).getProduto().getGarantia());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getItensPorCaixa());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getVolumes());
        Assertions.assertEquals("https://www.youtube.com/watch?v=zKKL-SgC5lY", result.getRetorno().getProdutos().get(0).getProduto().getUrlVideo());
        Assertions.assertEquals(BigDecimal.valueOf(1234), result.getRetorno().getProdutos().get(0).getProduto().getIdCategoria());
        //VariacaoRequest
        Assertions.assertEquals("Cor:Preto", result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getNome());
        Assertions.assertEquals("223435780", result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getCodigo());
        Assertions.assertEquals("S", result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getClonarDadosPai());
        Assertions.assertEquals(BigDecimal.valueOf(120), result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getEstoque());
        Assertions.assertEquals("1", result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getUn());
        Assertions.assertEquals(BigDecimal.valueOf(150), result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getVlr_unit());
        //DepositoRequest
        Assertions.assertEquals(BigDecimal.valueOf(123456), result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getDeposito().getId());
        Assertions.assertEquals(BigDecimal.valueOf(200), result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getDeposito().getEstoque());
        //ImagemRequest
        Assertions.assertEquals("https://www.bling.com.br/bling.jpg", result.getRetorno().getProdutos().get(0).getProduto().getImagens().get(0).getUrl());

        System.out.println("TEST POST CREATE PRODUCT WITH VARIATION: " + result);

    }

    @Test
    void testCreateProductComposition() {     //TESTE PRODUTO COM COMPOSIÇÃO.
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtos\":[{\"produto\":{\"codigo\":456489798,\"descricao\":\"Kit 001\",\"descricaoCurta\":\"Kit caneca e copo\",\"descricaoComplementar\":\"Descrição complementar\",\"un\":\"Pc\",\"vlr_unit\":1.68,\"preco_custo\":1.23,\"peso_bruto\":0.2,\"peso_liq\":0.18,\"class_fiscal\":\"1000.01.01\",\"marca\":\"Marca da Caneta\",\"origem\":0,\"gtin\":223435780,\"gtinEmbalagem\":54546,\"largura\":11,\"altura\":21,\"profundidade\":31,\"estoqueMinimo\":1,\"estoqueMaximo\":100,\"cest\":\"28.040.00\",\"idGrupoProduto\":12345,\"condicao\":\"Usado\",\"freteGratis\":\"S\",\"linkExterno\":\"https://minhaloja.com.br/meu-produto\",\"observacoes\":\"Observações do meu produto\",\"producao\":\"P\",\"dataValidade\":\"20/11/2019\",\"descricaoFornecedor\":\"Descrição do fornecedor\",\"idFabricante\":0,\"codigoFabricante\":123,\"unidadeMedida\":\"Centímetros\",\"crossdocking\":2,\"garantia\":4,\"itensPorCaixa\":2,\"volumes\":2,\"urlVideo\":\"https://www.youtube.com/watch?v=zKKL-SgC5lY\",\"idCategoria\":1234,\"imagens\":[{\"url\":\"https://www.bling.com.br/bling.jpg\"}],\"estrutura\":{\"tipoEstoque\":\"F\",\"lancarEstoque\":\"P\",\"componente\":[{\"nome\":\"Caneca Vermelha\",\"codigo\":\"caneca123\",\"quantidade\":2},{\"nome\":\"Copo 300 ml\",\"codigo\":\"copo123\",\"quantidade\":2}]}}}]}}";
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        JsonRequest result = produtoServiceImpl.createProduct("xml");

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        Assertions.assertEquals("456489798", result.getRetorno().getProdutos().get(0).getProduto().getCodigo());
        Assertions.assertEquals("Kit 001", result.getRetorno().getProdutos().get(0).getProduto().getDescricao());
        Assertions.assertEquals("Kit caneca e copo", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoCurta());
        Assertions.assertEquals("Descrição complementar", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoComplementar());
        Assertions.assertEquals("Pc", result.getRetorno().getProdutos().get(0).getProduto().getUn());
        Assertions.assertEquals(BigDecimal.valueOf(1.68), result.getRetorno().getProdutos().get(0).getProduto().getVlr_unit());
        Assertions.assertEquals(BigDecimal.valueOf(1.23), result.getRetorno().getProdutos().get(0).getProduto().getPreco_custo());
        Assertions.assertEquals(BigDecimal.valueOf(0.2), result.getRetorno().getProdutos().get(0).getProduto().getPeso_bruto());
        Assertions.assertEquals(BigDecimal.valueOf(0.18), result.getRetorno().getProdutos().get(0).getProduto().getPeso_liq());
        Assertions.assertEquals("1000.01.01", result.getRetorno().getProdutos().get(0).getProduto().getClass_fiscal());
        Assertions.assertEquals("Marca da Caneta", result.getRetorno().getProdutos().get(0).getProduto().getMarca());
        Assertions.assertEquals("0", result.getRetorno().getProdutos().get(0).getProduto().getOrigem());
        Assertions.assertEquals("223435780", result.getRetorno().getProdutos().get(0).getProduto().getGtin());
        Assertions.assertEquals("54546", result.getRetorno().getProdutos().get(0).getProduto().getGtinEmbalagem());
        Assertions.assertEquals("11", result.getRetorno().getProdutos().get(0).getProduto().getLargura());
        Assertions.assertEquals("21", result.getRetorno().getProdutos().get(0).getProduto().getAltura());
        Assertions.assertEquals("31", result.getRetorno().getProdutos().get(0).getProduto().getProfundidade());
        Assertions.assertEquals(BigDecimal.valueOf(1), result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMinimo());
        Assertions.assertEquals(BigDecimal.valueOf(100), result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMaximo());
        Assertions.assertEquals("28.040.00", result.getRetorno().getProdutos().get(0).getProduto().getCest());
        Assertions.assertEquals(BigDecimal.valueOf(12345), result.getRetorno().getProdutos().get(0).getProduto().getIdGrupoProduto());
        Assertions.assertEquals("Usado", result.getRetorno().getProdutos().get(0).getProduto().getCondicao());
        Assertions.assertEquals("S", result.getRetorno().getProdutos().get(0).getProduto().getFreteGratis());
        Assertions.assertEquals("https://minhaloja.com.br/meu-produto", result.getRetorno().getProdutos().get(0).getProduto().getLinkExterno());
        Assertions.assertEquals("Observações do meu produto", result.getRetorno().getProdutos().get(0).getProduto().getObservacoes());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getProducao());
        Assertions.assertEquals("20/11/2019", result.getRetorno().getProdutos().get(0).getProduto().getDataValidade());
        Assertions.assertEquals("Descrição do fornecedor", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoFornecedor());
        Assertions.assertEquals(BigDecimal.valueOf(0), result.getRetorno().getProdutos().get(0).getProduto().getIdFabricante());
        Assertions.assertEquals("123", result.getRetorno().getProdutos().get(0).getProduto().getCodigoFabricante());
        Assertions.assertEquals("Centímetros", result.getRetorno().getProdutos().get(0).getProduto().getUnidadeMedida());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getCrossdocking());
        Assertions.assertEquals(4, result.getRetorno().getProdutos().get(0).getProduto().getGarantia());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getItensPorCaixa());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getVolumes());
        Assertions.assertEquals("https://www.youtube.com/watch?v=zKKL-SgC5lY", result.getRetorno().getProdutos().get(0).getProduto().getUrlVideo());
        Assertions.assertEquals(BigDecimal.valueOf(1234), result.getRetorno().getProdutos().get(0).getProduto().getIdCategoria());
        //ImagemRequest
        Assertions.assertEquals("https://www.bling.com.br/bling.jpg", result.getRetorno().getProdutos().get(0).getProduto().getImagens().get(0).getUrl());
        //ComponenteRequest
        Assertions.assertEquals("F", result.getRetorno().getProdutos().get(0).getProduto().getEstrutura().getTipoEstoque());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getEstrutura().getLancarEstoque());
        Assertions.assertEquals("Caneca Vermelha", result.getRetorno().getProdutos().get(0).getProduto().getEstrutura().getComponente().get(0).getNome());
        Assertions.assertEquals("caneca123", result.getRetorno().getProdutos().get(0).getProduto().getEstrutura().getComponente().get(0).getCodigo());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getEstrutura().getComponente().get(0).getQuantidade());


        System.out.println("TEST POST CREATE PRODUCT WITH COMPOSITION: " + result);

    }

    @Test
    void testCreateProductService() {     //TESTE PRODUTO SERVIÇO.
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtos\":[{\"produto\":{\"codigo\":123456,\"tipo\":\"S\",\"situacao\":\"Ativo\",\"descricao\":\"Formatação PC\",\"descricaoCurta\":\"Descrição curta do serviço\",\"descricaoComplementar\":\"Descrição complementar do serviço\",\"un\":\"UN\",\"idGrupoProduto\":1675,\"linkExterno\":\"https://site.com.br/servico\",\"observacoes\":\"Observações do serviço\",\"vlr_unit\":85,\"preco_custo\":50,\"urlVideo\":\"https://www.youtube.com/watch?v=zKKL-SgC5lY\"}}]}}";
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        JsonRequest result = produtoServiceImpl.createProduct("xml");

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        Assertions.assertEquals("123456", result.getRetorno().getProdutos().get(0).getProduto().getCodigo());
        Assertions.assertEquals("S", result.getRetorno().getProdutos().get(0).getProduto().getTipo());
        Assertions.assertEquals("Ativo", result.getRetorno().getProdutos().get(0).getProduto().getSituacao());

        Assertions.assertEquals("Formatação PC", result.getRetorno().getProdutos().get(0).getProduto().getDescricao());
        Assertions.assertEquals("Descrição curta do serviço", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoCurta());
        Assertions.assertEquals("Descrição complementar do serviço", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoComplementar());
        Assertions.assertEquals("UN", result.getRetorno().getProdutos().get(0).getProduto().getUn());

        Assertions.assertEquals(BigDecimal.valueOf(1675), result.getRetorno().getProdutos().get(0).getProduto().getIdGrupoProduto());
        Assertions.assertEquals("https://site.com.br/servico", result.getRetorno().getProdutos().get(0).getProduto().getLinkExterno());
        Assertions.assertEquals("Observações do serviço", result.getRetorno().getProdutos().get(0).getProduto().getObservacoes());
        Assertions.assertEquals(BigDecimal.valueOf(85), result.getRetorno().getProdutos().get(0).getProduto().getVlr_unit());
        Assertions.assertEquals(BigDecimal.valueOf(50), result.getRetorno().getProdutos().get(0).getProduto().getPreco_custo());
        Assertions.assertEquals("https://www.youtube.com/watch?v=zKKL-SgC5lY", result.getRetorno().getProdutos().get(0).getProduto().getUrlVideo());

        System.out.println("TEST POST CREATE PRODUCT SERVICE: " + result);

    }

    @Test
    void testUpdateProductSimple() {

        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtos\":[{\"produto\":{\"codigo\":223435780,\"descricao\":\"Caneta 001\",\"situacao\":\"Ativo\",\"descricaoCurta\":\"Descrição curta da caneta\",\"descricaoComplementar\":\"Descrição complementar da caneta\",\"un\":\"Pc\",\"vlr_unit\":1.68,\"preco_custo\":1.23,\"peso_bruto\":0.2,\"peso_liq\":0.18,\"class_fiscal\":\"1000.01.01\",\"marca\":\"Marca da Caneta\",\"origem\":0,\"estoque\":10,\"deposito\":{\"id\":123456,\"estoque\":200},\"gtin\":223435780,\"gtinEmbalagem\":54546,\"largura\":11,\"altura\":21,\"profundidade\":31,\"estoqueMinimo\":1,\"estoqueMaximo\":100,\"cest\":\"28.040.00\",\"idGrupoProduto\":12345,\"condicao\":\"Novo\",\"freteGratis\":\"N\",\"linkExterno\":\"https://minhaloja.com.br/meu-produto\",\"observacoes\":\"Observações do meu produtos\",\"producao\":\"P\",\"dataValidade\":\"20/11/2019\",\"descricaoFornecedor\":\"Descrição do fornecedor\",\"idFabricante\":0,\"codigoFabricante\":123,\"unidadeMedida\":\"Centímetros\",\"garantia\":4,\"itensPorCaixa\":2,\"volumes\":2,\"urlVideo\":\"https://www.youtube.com/watch?v=zKKL-SgC5lY\",\"imagens\":[{\"url\":\"https://bling.com.br/bling.jpg\"}],\"camposCustomizados\":{\"alias\":16},\"idCategoria\":1234}}]}}";
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        JsonRequest result = produtoServiceImpl.updateProduct("xml", "codigo");

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        Assertions.assertEquals("223435780", result.getRetorno().getProdutos().get(0).getProduto().getCodigo());
        Assertions.assertEquals("Caneta 001", result.getRetorno().getProdutos().get(0).getProduto().getDescricao());
        Assertions.assertEquals("Ativo", result.getRetorno().getProdutos().get(0).getProduto().getSituacao());
        Assertions.assertEquals("Descrição curta da caneta", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoCurta());
        Assertions.assertEquals("Descrição complementar da caneta", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoComplementar());
        Assertions.assertEquals("Pc", result.getRetorno().getProdutos().get(0).getProduto().getUn());
        Assertions.assertEquals(BigDecimal.valueOf(1.68), result.getRetorno().getProdutos().get(0).getProduto().getVlr_unit());
        Assertions.assertEquals(BigDecimal.valueOf(1.23), result.getRetorno().getProdutos().get(0).getProduto().getPreco_custo());
        Assertions.assertEquals(BigDecimal.valueOf(0.2), result.getRetorno().getProdutos().get(0).getProduto().getPeso_bruto());
        Assertions.assertEquals(BigDecimal.valueOf(0.18), result.getRetorno().getProdutos().get(0).getProduto().getPeso_liq());
        Assertions.assertEquals("1000.01.01", result.getRetorno().getProdutos().get(0).getProduto().getClass_fiscal());
        Assertions.assertEquals("Marca da Caneta", result.getRetorno().getProdutos().get(0).getProduto().getMarca());
        Assertions.assertEquals("0", result.getRetorno().getProdutos().get(0).getProduto().getOrigem());
        Assertions.assertEquals(10, result.getRetorno().getProdutos().get(0).getProduto().getEstoque());
        //DepositoRequest
        Assertions.assertEquals(BigDecimal.valueOf(123456), result.getRetorno().getProdutos().get(0).getProduto().getDeposito().getId());
        Assertions.assertEquals(BigDecimal.valueOf(200), result.getRetorno().getProdutos().get(0).getProduto().getDeposito().getEstoque());
        /*---------------------------------------------------------------------------------------------------------------------*/
        Assertions.assertEquals("223435780", result.getRetorno().getProdutos().get(0).getProduto().getGtin());
        Assertions.assertEquals("54546", result.getRetorno().getProdutos().get(0).getProduto().getGtinEmbalagem());
        Assertions.assertEquals("11", result.getRetorno().getProdutos().get(0).getProduto().getLargura());
        Assertions.assertEquals("21", result.getRetorno().getProdutos().get(0).getProduto().getAltura());
        Assertions.assertEquals("31", result.getRetorno().getProdutos().get(0).getProduto().getProfundidade());
        Assertions.assertEquals(BigDecimal.valueOf(1), result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMinimo());
        Assertions.assertEquals(BigDecimal.valueOf(100), result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMaximo());
        Assertions.assertEquals("28.040.00", result.getRetorno().getProdutos().get(0).getProduto().getCest());
        Assertions.assertEquals(BigDecimal.valueOf(12345), result.getRetorno().getProdutos().get(0).getProduto().getIdGrupoProduto());
        Assertions.assertEquals("Novo", result.getRetorno().getProdutos().get(0).getProduto().getCondicao());
        Assertions.assertEquals("N", result.getRetorno().getProdutos().get(0).getProduto().getFreteGratis());
        Assertions.assertEquals("https://minhaloja.com.br/meu-produto", result.getRetorno().getProdutos().get(0).getProduto().getLinkExterno());
        Assertions.assertEquals("Observações do meu produtos", result.getRetorno().getProdutos().get(0).getProduto().getObservacoes());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getProducao());
        Assertions.assertEquals("20/11/2019", result.getRetorno().getProdutos().get(0).getProduto().getDataValidade());
        Assertions.assertEquals("Descrição do fornecedor", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoFornecedor());
        Assertions.assertEquals(BigDecimal.valueOf(0), result.getRetorno().getProdutos().get(0).getProduto().getIdFabricante());
        Assertions.assertEquals("123", result.getRetorno().getProdutos().get(0).getProduto().getCodigoFabricante());
        Assertions.assertEquals("Centímetros", result.getRetorno().getProdutos().get(0).getProduto().getUnidadeMedida());
        Assertions.assertEquals(4, result.getRetorno().getProdutos().get(0).getProduto().getGarantia());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getItensPorCaixa());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getVolumes());
        Assertions.assertEquals("https://www.youtube.com/watch?v=zKKL-SgC5lY", result.getRetorno().getProdutos().get(0).getProduto().getUrlVideo());
        //ImagemRequest
        Assertions.assertEquals("https://bling.com.br/bling.jpg", result.getRetorno().getProdutos().get(0).getProduto().getImagens().get(0).getUrl());
        //CamposCustomizadosReques
        Assertions.assertEquals("16", result.getRetorno().getProdutos().get(0).getProduto().getCamposCustomizados().getAlias());
        /*---------------------------------------------------------------------------------------------------------------------*/
        Assertions.assertEquals(BigDecimal.valueOf(1234), result.getRetorno().getProdutos().get(0).getProduto().getIdCategoria());

        System.out.println("TEST POST UPDATE PRODUCT SIMPLE: " + result);
    }

    @Test
    void testUpdateProductVariation() {     //TESTE PRODUTO COM VARIAÇÃO.
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtos\":[{\"produto\":{\"codigo\":223435780,\"descricao\":\"Caneta 001\",\"descricaoCurta\":\"Descrição curta da caneta\",\"descricaoComplementar\":\"Descrição complementar da caneta\",\"un\":\"Pc\",\"vlr_unit\":1.68,\"preco_custo\":1.23,\"peso_bruto\":0.2,\"peso_liq\":0.18,\"class_fiscal\":\"1000.01.01\",\"marca\":\"Marca da Caneta\",\"origem\":0,\"gtin\":223435780,\"gtinEmbalagem\":54546,\"largura\":11,\"altura\":21,\"profundidade\":31,\"estoqueMinimo\":1,\"estoqueMaximo\":100,\"cest\":\"28.040.00\",\"idGrupoProduto\":12345,\"condicao\":\"Usado\",\"freteGratis\":\"S\",\"linkExterno\":\"https://minhaloja.com.br/meu-produto\",\"observacoes\":\"Observações do meu produto\",\"producao\":\"P\",\"dataValidade\":\"20/11/2019\",\"descricaoFornecedor\":\"Descrição do fornecedor\",\"idFabricante\":0,\"codigoFabricante\":123,\"unidadeMedida\":\"Centímetros\",\"crossdocking\":2,\"garantia\":4,\"itensPorCaixa\":2,\"volumes\":2,\"urlVideo\":\"https://www.youtube.com/watch?v=zKKL-SgC5lY\",\"idCategoria\":1234,\"variacoes\":{\"variacao\":[{\"nome\":\"Cor:Preto\",\"codigo\":\"223435780\",\"clonarDadosPai\":\"S\",\"vlr_unit\":150,\"estoque\":120,\"un\":\"1\",\"deposito\":{\"id\":123456,\"estoque\":200}}]},\"imagens\":[{\"url\":\"https://www.bling.com.br/bling.jpg\"}]}}]}}";
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        JsonRequest result = produtoServiceImpl.updateProduct("xml", "codigo");

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        Assertions.assertEquals("223435780", result.getRetorno().getProdutos().get(0).getProduto().getCodigo());
        Assertions.assertEquals("Caneta 001", result.getRetorno().getProdutos().get(0).getProduto().getDescricao());
        Assertions.assertEquals("Descrição curta da caneta", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoCurta());
        Assertions.assertEquals("Descrição complementar da caneta", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoComplementar());
        Assertions.assertEquals("Pc", result.getRetorno().getProdutos().get(0).getProduto().getUn());
        Assertions.assertEquals(BigDecimal.valueOf(1.68), result.getRetorno().getProdutos().get(0).getProduto().getVlr_unit());
        Assertions.assertEquals(BigDecimal.valueOf(1.23), result.getRetorno().getProdutos().get(0).getProduto().getPreco_custo());
        Assertions.assertEquals(BigDecimal.valueOf(0.2), result.getRetorno().getProdutos().get(0).getProduto().getPeso_bruto());
        Assertions.assertEquals(BigDecimal.valueOf(0.18), result.getRetorno().getProdutos().get(0).getProduto().getPeso_liq());
        Assertions.assertEquals("1000.01.01", result.getRetorno().getProdutos().get(0).getProduto().getClass_fiscal());
        Assertions.assertEquals("Marca da Caneta", result.getRetorno().getProdutos().get(0).getProduto().getMarca());
        Assertions.assertEquals("0", result.getRetorno().getProdutos().get(0).getProduto().getOrigem());
        Assertions.assertEquals("223435780", result.getRetorno().getProdutos().get(0).getProduto().getGtin());
        Assertions.assertEquals("54546", result.getRetorno().getProdutos().get(0).getProduto().getGtinEmbalagem());
        Assertions.assertEquals("11", result.getRetorno().getProdutos().get(0).getProduto().getLargura());
        Assertions.assertEquals("21", result.getRetorno().getProdutos().get(0).getProduto().getAltura());
        Assertions.assertEquals("31", result.getRetorno().getProdutos().get(0).getProduto().getProfundidade());
        Assertions.assertEquals(BigDecimal.valueOf(1), result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMinimo());
        Assertions.assertEquals(BigDecimal.valueOf(100), result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMaximo());
        Assertions.assertEquals("1000.01.01", result.getRetorno().getProdutos().get(0).getProduto().getClass_fiscal());
        Assertions.assertEquals("28.040.00", result.getRetorno().getProdutos().get(0).getProduto().getCest());
        Assertions.assertEquals(BigDecimal.valueOf(12345), result.getRetorno().getProdutos().get(0).getProduto().getIdGrupoProduto());
        Assertions.assertEquals("Usado", result.getRetorno().getProdutos().get(0).getProduto().getCondicao());
        Assertions.assertEquals("S", result.getRetorno().getProdutos().get(0).getProduto().getFreteGratis());
        Assertions.assertEquals("https://minhaloja.com.br/meu-produto", result.getRetorno().getProdutos().get(0).getProduto().getLinkExterno());
        Assertions.assertEquals("Observações do meu produto", result.getRetorno().getProdutos().get(0).getProduto().getObservacoes());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getProducao());
        Assertions.assertEquals("20/11/2019", result.getRetorno().getProdutos().get(0).getProduto().getDataValidade());
        Assertions.assertEquals("Descrição do fornecedor", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoFornecedor());
        Assertions.assertEquals(BigDecimal.valueOf(0), result.getRetorno().getProdutos().get(0).getProduto().getIdFabricante());
        Assertions.assertEquals("123", result.getRetorno().getProdutos().get(0).getProduto().getCodigoFabricante());
        Assertions.assertEquals("Centímetros", result.getRetorno().getProdutos().get(0).getProduto().getUnidadeMedida());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getCrossdocking());
        Assertions.assertEquals(4, result.getRetorno().getProdutos().get(0).getProduto().getGarantia());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getItensPorCaixa());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getVolumes());
        Assertions.assertEquals("https://www.youtube.com/watch?v=zKKL-SgC5lY", result.getRetorno().getProdutos().get(0).getProduto().getUrlVideo());
        Assertions.assertEquals(BigDecimal.valueOf(1234), result.getRetorno().getProdutos().get(0).getProduto().getIdCategoria());
        //VariacaoRequest
        Assertions.assertEquals("Cor:Preto", result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getNome());
        Assertions.assertEquals("223435780", result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getCodigo());
        Assertions.assertEquals("S", result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getClonarDadosPai());
        Assertions.assertEquals(BigDecimal.valueOf(120), result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getEstoque());
        Assertions.assertEquals("1", result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getUn());
        Assertions.assertEquals(BigDecimal.valueOf(150), result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getVlr_unit());
        //DepositoRequest
        Assertions.assertEquals(BigDecimal.valueOf(123456), result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getDeposito().getId());
        Assertions.assertEquals(BigDecimal.valueOf(200), result.getRetorno().getProdutos().get(0).getProduto().getVariacoes().getVariacao().get(0).getDeposito().getEstoque());
        //ImagemRequest
        Assertions.assertEquals("https://www.bling.com.br/bling.jpg", result.getRetorno().getProdutos().get(0).getProduto().getImagens().get(0).getUrl());

        System.out.println("TEST POST UPDATE PRODUCT WITH VARIATION: " + result);

    }

    @Test
    void testCreateUpdateComposition() {     //TESTE PRODUTO COM COMPOSIÇÃO.
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtos\":[{\"produto\":{\"codigo\":456489798,\"descricao\":\"Kit 001\",\"descricaoCurta\":\"Kit caneca e copo\",\"descricaoComplementar\":\"Descrição complementar\",\"un\":\"Pc\",\"vlr_unit\":1.68,\"preco_custo\":1.23,\"peso_bruto\":0.2,\"peso_liq\":0.18,\"class_fiscal\":\"1000.01.01\",\"marca\":\"Marca da Caneta\",\"origem\":0,\"gtin\":223435780,\"gtinEmbalagem\":54546,\"largura\":11,\"altura\":21,\"profundidade\":31,\"estoqueMinimo\":1,\"estoqueMaximo\":100,\"cest\":\"28.040.00\",\"idGrupoProduto\":12345,\"condicao\":\"Usado\",\"freteGratis\":\"S\",\"linkExterno\":\"https://minhaloja.com.br/meu-produto\",\"observacoes\":\"Observações do meu produto\",\"producao\":\"P\",\"dataValidade\":\"20/11/2019\",\"descricaoFornecedor\":\"Descrição do fornecedor\",\"idFabricante\":0,\"codigoFabricante\":123,\"unidadeMedida\":\"Centímetros\",\"crossdocking\":2,\"garantia\":4,\"itensPorCaixa\":2,\"volumes\":2,\"urlVideo\":\"https://www.youtube.com/watch?v=zKKL-SgC5lY\",\"idCategoria\":1234,\"imagens\":[{\"url\":\"https://www.bling.com.br/bling.jpg\"}],\"estrutura\":{\"tipoEstoque\":\"F\",\"lancarEstoque\":\"P\",\"componente\":[{\"nome\":\"Caneca Vermelha\",\"codigo\":\"caneca123\",\"quantidade\":2},{\"nome\":\"Copo 300 ml\",\"codigo\":\"copo123\",\"quantidade\":2}]}}}]}}";
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        JsonRequest result = produtoServiceImpl.updateProduct("xml", "codigo");

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        Assertions.assertEquals("456489798", result.getRetorno().getProdutos().get(0).getProduto().getCodigo());
        Assertions.assertEquals("Kit 001", result.getRetorno().getProdutos().get(0).getProduto().getDescricao());
        Assertions.assertEquals("Kit caneca e copo", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoCurta());
        Assertions.assertEquals("Descrição complementar", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoComplementar());
        Assertions.assertEquals("Pc", result.getRetorno().getProdutos().get(0).getProduto().getUn());
        Assertions.assertEquals(BigDecimal.valueOf(1.68), result.getRetorno().getProdutos().get(0).getProduto().getVlr_unit());
        Assertions.assertEquals(BigDecimal.valueOf(1.23), result.getRetorno().getProdutos().get(0).getProduto().getPreco_custo());
        Assertions.assertEquals(BigDecimal.valueOf(0.2), result.getRetorno().getProdutos().get(0).getProduto().getPeso_bruto());
        Assertions.assertEquals(BigDecimal.valueOf(0.18), result.getRetorno().getProdutos().get(0).getProduto().getPeso_liq());
        Assertions.assertEquals("1000.01.01", result.getRetorno().getProdutos().get(0).getProduto().getClass_fiscal());
        Assertions.assertEquals("Marca da Caneta", result.getRetorno().getProdutos().get(0).getProduto().getMarca());
        Assertions.assertEquals("0", result.getRetorno().getProdutos().get(0).getProduto().getOrigem());
        Assertions.assertEquals("223435780", result.getRetorno().getProdutos().get(0).getProduto().getGtin());
        Assertions.assertEquals("54546", result.getRetorno().getProdutos().get(0).getProduto().getGtinEmbalagem());
        Assertions.assertEquals("11", result.getRetorno().getProdutos().get(0).getProduto().getLargura());
        Assertions.assertEquals("21", result.getRetorno().getProdutos().get(0).getProduto().getAltura());
        Assertions.assertEquals("31", result.getRetorno().getProdutos().get(0).getProduto().getProfundidade());
        Assertions.assertEquals(BigDecimal.valueOf(1), result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMinimo());
        Assertions.assertEquals(BigDecimal.valueOf(100), result.getRetorno().getProdutos().get(0).getProduto().getEstoqueMaximo());
        Assertions.assertEquals("28.040.00", result.getRetorno().getProdutos().get(0).getProduto().getCest());
        Assertions.assertEquals(BigDecimal.valueOf(12345), result.getRetorno().getProdutos().get(0).getProduto().getIdGrupoProduto());
        Assertions.assertEquals("Usado", result.getRetorno().getProdutos().get(0).getProduto().getCondicao());
        Assertions.assertEquals("S", result.getRetorno().getProdutos().get(0).getProduto().getFreteGratis());
        Assertions.assertEquals("https://minhaloja.com.br/meu-produto", result.getRetorno().getProdutos().get(0).getProduto().getLinkExterno());
        Assertions.assertEquals("Observações do meu produto", result.getRetorno().getProdutos().get(0).getProduto().getObservacoes());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getProducao());
        Assertions.assertEquals("20/11/2019", result.getRetorno().getProdutos().get(0).getProduto().getDataValidade());
        Assertions.assertEquals("Descrição do fornecedor", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoFornecedor());
        Assertions.assertEquals(BigDecimal.valueOf(0), result.getRetorno().getProdutos().get(0).getProduto().getIdFabricante());
        Assertions.assertEquals("123", result.getRetorno().getProdutos().get(0).getProduto().getCodigoFabricante());
        Assertions.assertEquals("Centímetros", result.getRetorno().getProdutos().get(0).getProduto().getUnidadeMedida());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getCrossdocking());
        Assertions.assertEquals(4, result.getRetorno().getProdutos().get(0).getProduto().getGarantia());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getItensPorCaixa());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getVolumes());
        Assertions.assertEquals("https://www.youtube.com/watch?v=zKKL-SgC5lY", result.getRetorno().getProdutos().get(0).getProduto().getUrlVideo());
        Assertions.assertEquals(BigDecimal.valueOf(1234), result.getRetorno().getProdutos().get(0).getProduto().getIdCategoria());
        //ImagemRequest
        Assertions.assertEquals("https://www.bling.com.br/bling.jpg", result.getRetorno().getProdutos().get(0).getProduto().getImagens().get(0).getUrl());
        //ComponenteRequest
        Assertions.assertEquals("F", result.getRetorno().getProdutos().get(0).getProduto().getEstrutura().getTipoEstoque());
        Assertions.assertEquals("P", result.getRetorno().getProdutos().get(0).getProduto().getEstrutura().getLancarEstoque());
        Assertions.assertEquals("Caneca Vermelha", result.getRetorno().getProdutos().get(0).getProduto().getEstrutura().getComponente().get(0).getNome());
        Assertions.assertEquals("caneca123", result.getRetorno().getProdutos().get(0).getProduto().getEstrutura().getComponente().get(0).getCodigo());
        Assertions.assertEquals(BigDecimal.valueOf(2), result.getRetorno().getProdutos().get(0).getProduto().getEstrutura().getComponente().get(0).getQuantidade());


        System.out.println("TEST POST UPDATE PRODUCT WITH COMPOSITION: " + result);

    }

    @Test
    void testUpdateProductService() {     //TESTE PRODUTO SERVIÇO.
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"produtos\":[{\"produto\":{\"codigo\":123456,\"tipo\":\"S\",\"situacao\":\"Ativo\",\"descricao\":\"Formatação PC\",\"descricaoCurta\":\"Descrição curta do serviço\",\"descricaoComplementar\":\"Descrição complementar do serviço\",\"un\":\"UN\",\"idGrupoProduto\":1675,\"linkExterno\":\"https://site.com.br/servico\",\"observacoes\":\"Observações do serviço\",\"vlr_unit\":85,\"preco_custo\":50,\"urlVideo\":\"https://www.youtube.com/watch?v=zKKL-SgC5lY\"}}]}}";
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        JsonRequest result = produtoServiceImpl.updateProduct("xml", "codigo");

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        Assertions.assertEquals("123456", result.getRetorno().getProdutos().get(0).getProduto().getCodigo());
        Assertions.assertEquals("S", result.getRetorno().getProdutos().get(0).getProduto().getTipo());
        Assertions.assertEquals("Ativo", result.getRetorno().getProdutos().get(0).getProduto().getSituacao());

        Assertions.assertEquals("Formatação PC", result.getRetorno().getProdutos().get(0).getProduto().getDescricao());
        Assertions.assertEquals("Descrição curta do serviço", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoCurta());
        Assertions.assertEquals("Descrição complementar do serviço", result.getRetorno().getProdutos().get(0).getProduto().getDescricaoComplementar());
        Assertions.assertEquals("UN", result.getRetorno().getProdutos().get(0).getProduto().getUn());

        Assertions.assertEquals(BigDecimal.valueOf(1675), result.getRetorno().getProdutos().get(0).getProduto().getIdGrupoProduto());
        Assertions.assertEquals("https://site.com.br/servico", result.getRetorno().getProdutos().get(0).getProduto().getLinkExterno());
        Assertions.assertEquals("Observações do serviço", result.getRetorno().getProdutos().get(0).getProduto().getObservacoes());
        Assertions.assertEquals(BigDecimal.valueOf(85), result.getRetorno().getProdutos().get(0).getProduto().getVlr_unit());
        Assertions.assertEquals(BigDecimal.valueOf(50), result.getRetorno().getProdutos().get(0).getProduto().getPreco_custo());
        Assertions.assertEquals("https://www.youtube.com/watch?v=zKKL-SgC5lY", result.getRetorno().getProdutos().get(0).getProduto().getUrlVideo());

        System.out.println("TEST POST UPDATE PRODUCT SERVICE: " + result);

    }
}