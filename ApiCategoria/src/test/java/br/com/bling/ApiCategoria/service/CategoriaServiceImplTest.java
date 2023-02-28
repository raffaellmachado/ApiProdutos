package br.com.bling.ApiCategoria.service;

import br.com.bling.ApiCategoria.controllers.request.RespostaRequest;
import br.com.bling.ApiCategoria.controllers.response.RespostaResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.fail;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void testGetAllCategory() throws Exception {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\": {\"categorias\": [{\"categoria\": {\"id\": \"123\", \"descricao\": \"Categoria 1\"}}, {\"categoria\": {\"id\": \"456\", \"descricao\": \"Categoria 2\"}}]}}";
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaResponse
        RespostaResponse result = categoriaServiceImpl.getAllCategory();

        // Verifica se a lista de categorias foi corretamente convertida a partir da resposta da API
        assertEquals(2, result.getRetorno().getCategorias().size());
        assertEquals("123", result.getRetorno().getCategorias().get(0).getCategoria().getId());
        assertEquals("Categoria 1", result.getRetorno().getCategorias().get(0).getCategoria().getDescricao());
        assertEquals("456", result.getRetorno().getCategorias().get(1).getCategoria().getId());
        assertEquals("Categoria 2", result.getRetorno().getCategorias().get(1).getCategoria().getDescricao());

        System.out.println("GET LIST: " + result);
    }

    @Test
    void testGetAllCategoryWithException() throws Exception {
        // Simula uma exceção lançada pelo restTemplate
        when(restTemplate.getForObject(anyString(), eq(String.class)))
                .thenThrow(new RestClientException("Erro ao chamar API externa"));

        // Chama o método que deve lançar uma exceção
        try {
            categoriaServiceImpl.getAllCategory();
            fail("Expected RestClientException not thrown");
        } catch (RestClientException e) {
            // Verifica se a exceção foi lançada corretamente
            assertEquals("Erro ao chamar API externa", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }


    @Test
    void testGetCategoryByIdCategoria() throws Exception {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\": {\"categorias\": [{\"categoria\": {\"id\": \"123\", \"descricao\": \"Categoria 1\"}}]}}";
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaResponse
        RespostaResponse result = categoriaServiceImpl.getCategoryByIdCategoria("idCategoria");

        // Verifica se a categoria foi corretamente convertida a partir da resposta da API
        assertEquals(1, result.getRetorno().getCategorias().size());
        assertEquals("123", result.getRetorno().getCategorias().get(0).getCategoria().getId());
        assertEquals("Categoria 1", result.getRetorno().getCategorias().get(0).getCategoria().getDescricao());

        System.out.println("GET ID: " + result);
    }


    @Test
    void testCreateCategory() throws Exception {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\": {\"categorias\": {\"categoria\": {\"id\": \"123\", \"descricao\": \"Categoria 1\"}}}}";
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        RespostaRequest result = categoriaServiceImpl.createCategory("xml");

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        assertEquals("123", result.getRetorno().getCategorias().get(0).get(0).getCategoria().getId());
        assertEquals("Categoria 1", result.getRetorno().getCategorias().get(0).get(0).getCategoria().getDescricao());

        System.out.println("POST: " + result);
    }
}
//    @Test
//    void testUpdateCategory() {
//        RespostaRequest result = categoriaServiceImpl.updateCategory("xml", "idCategoria");
//        Assertions.assertEquals(new RespostaRequest(), result);
//    }
//}
//
////Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme