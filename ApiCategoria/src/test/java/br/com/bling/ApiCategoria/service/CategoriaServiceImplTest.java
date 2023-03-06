package br.com.bling.ApiCategoria.service;

import br.com.bling.ApiCategoria.controllers.response.JsonResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;

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
        Mockito.when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);

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
        String jsonResponse = "{\"retorno\": {\"categorias\": [{\"categoria\": {\"id\": \"123\", \"descricao\": \"Categoria 1\"}}]}}";
        Mockito.when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaResponse
        JsonResponse result = categoriaServiceImpl.getCategoryByIdCategoria("idCategoria");

        // Verifica se a categoria foi corretamente convertida a partir da resposta da API
        Assertions.assertEquals(1, result.getRetorno().getCategorias().size());
        Assertions.assertEquals("123", result.getRetorno().getCategorias().get(0).getCategoria().getId());
        Assertions.assertEquals("Categoria 1", result.getRetorno().getCategorias().get(0).getCategoria().getDescricao());

        System.out.println("GET ID: " + result);
    }

    /**
     * TESTE SERVICE - POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML/JSON".
     */
//    @Test
//    void testCreateCategory() throws Exception {
//        // Simula a resposta da chamada para a API externa
//        String jsonResponse = "{\"retorno\": {\"categorias\": {\"categoria\": {\"id\": \"007\", \"descricao\": \"Moveis usados\"}}}}";
//        Mockito.when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
//                .thenReturn(new ResponseEntity<>(jsonResponse, HttpStatus.OK));
//
//        // Chama o método que deve converter a resposta em um objeto RespostaRequest
//        JsonRequest result = categoriaServiceImpl.createCategory("xml");
//
//        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
//        Assertions.assertEquals("007", result.getRetorno().getCategorias().get(0).get(0).getCategoria().getId());
//        Assertions.assertEquals("Moveis usados", result.getRetorno().getCategorias().get(0).get(0).getCategoria().getDescricao());
//
//        System.out.println("POST: " + result);
//    }

    /**
     * TESTE SERVICE - PUT "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @Test
    void testUpdateCategory() {
//        RespostaRequest result = categoriaServiceImpl.updateCategory("xml", "idCategoria");
//        Assertions.assertEquals(new RespostaRequest(), result);
    }
}