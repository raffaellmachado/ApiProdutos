package br.com.bling.ApiCategorias.service;

import br.com.bling.ApiCategorias.controllers.request.JsonRequest;
import br.com.bling.ApiCategorias.controllers.response.JsonResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoriaServiceImplTest {
    @Mock
    RestTemplate restTemplate;
    @Mock
    CategoriaService categoriaService;
    @InjectMocks
    CategoriaServiceImpl categoriaServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * TESTE SERVICE - GET "BUSCAR A LISTA DE CATEGORIA CADASTRADOS NO BLING".
     */
    @Test
    void testGetAllCategory() throws Exception {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\": {\"categorias\": [{\"categoria\": {\"id\": \"123\", \"descricao\": \"Categoria 1\"}}, {\"categoria\": {\"id\": \"456\", \"descricao\": \"Categoria 2\"}}]}}";
        ResponseEntity<String> responseEntity = ResponseEntity.ok(jsonResponse);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(String.class))).thenReturn(responseEntity);

        // Chama o método que deve converter a resposta em um objeto RespostaResponse
        JsonResponse result = categoriaServiceImpl.getAllCategory();

        // Verifica se a lista de categorias foi corretamente convertida a partir da resposta da API
        Assertions.assertEquals(2, result.getRetorno().getCategorias().size());

        Assertions.assertEquals("123", result.getRetorno().getCategorias().get(0).getCategoria().getId());
        Assertions.assertEquals("Categoria 1", result.getRetorno().getCategorias().get(0).getCategoria().getDescricao());

        Assertions.assertEquals("456", result.getRetorno().getCategorias().get(1).getCategoria().getId());
        Assertions.assertEquals("Categoria 2", result.getRetorno().getCategorias().get(1).getCategoria().getDescricao());

        System.out.println("GET LIST: " + result);
    }

    /**
     * TESTE SERVICE - GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @Test
    void testGetCategoryByIdCategoria() throws Exception {
        // Simula a resposta da chamada para a API externa
        String idCategoria = "159753";
        String jsonResponse = "{\"retorno\": {\"categorias\": [{\"categoria\": {\"id\": \"123\", \"descricao\": \"Categoria 1\"}}]}}";
        ResponseEntity<String> responseEntity = ResponseEntity.ok(jsonResponse);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(String.class))).thenReturn(responseEntity);

        // Chama o método que deve converter a resposta em um objeto RespostaResponse
        JsonResponse result = categoriaServiceImpl.getCategoryByIdCategoria(idCategoria);

        // Verifica se a categoria foi corretamente convertida a partir da resposta da API
        Assertions.assertEquals("123", result.getRetorno().getCategorias().get(0).getCategoria().getId());
        Assertions.assertEquals("Categoria 1", result.getRetorno().getCategorias().get(0).getCategoria().getDescricao());

        System.out.println("GET ID: " + result);
    }

    /**
     * TESTE SERVICE - POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML/JSON".
     */
    @Test
    void testCreateCategory() throws Exception {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\": {\"categorias\": {\"categoria\": {\"id\": \"007\", \"descricao\": \"Moveis usados\"}}}}";
        ResponseEntity<String> responseEntity = ResponseEntity.ok(jsonResponse);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(String.class))).thenReturn(responseEntity);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        JsonRequest result = (JsonRequest) categoriaServiceImpl.createCategory(jsonResponse);

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        Assertions.assertEquals("007", result.getRetorno().getCategorias().get(0).get(0).getCategoria().getId());
        Assertions.assertEquals("Moveis usados", result.getRetorno().getCategorias().get(0).get(0).getCategoria().getDescricao());

        System.out.println("POST: " + result);
    }

    /**
     * TESTE SERVICE - PUT "ATUALIZA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @Test
    void testUpdateCategory() {
        String idCategoria = "951357";
        String jsonResponse = "{\"retorno\": {\"categorias\": {\"categoria\": {\"id\": \"159\", \"descricao\": \"Canecas\"}}}}";
        ResponseEntity<String> responseEntity = ResponseEntity.ok(jsonResponse);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(String.class))).thenReturn(responseEntity);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        JsonRequest result = (JsonRequest) categoriaServiceImpl.updateCategory(jsonResponse, idCategoria);

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        Assertions.assertEquals("159", result.getRetorno().getCategorias().get(0).get(0).getCategoria().getId());
        Assertions.assertEquals("Canecas", result.getRetorno().getCategorias().get(0).get(0).getCategoria().getDescricao());

        System.out.println("POST: " + result);
    }
}