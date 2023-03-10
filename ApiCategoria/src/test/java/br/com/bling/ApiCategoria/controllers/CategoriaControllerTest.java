package br.com.bling.ApiCategoria.controllers;

import br.com.bling.ApiCategoria.controllers.request.CategoriaRequest;
import br.com.bling.ApiCategoria.controllers.request.JsonRequest;
import br.com.bling.ApiCategoria.controllers.request.RetornoRequest;
import br.com.bling.ApiCategoria.controllers.response.CategoriaResponse;
import br.com.bling.ApiCategoria.controllers.response.JsonResponse;
import br.com.bling.ApiCategoria.controllers.response.RetornoResponse;
import br.com.bling.ApiCategoria.exceptions.CategoriaIdCategoriaException;
import br.com.bling.ApiCategoria.exceptions.CategoriaListaException;
import br.com.bling.ApiCategoria.service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CategoriaControllerTest {
    @Mock
    CategoriaService categoriaService;
    @InjectMocks
    CategoriaController categoriaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * TESTE CONTROLLER - GET "BUSCAR A LISTA DE CATEGORIA CADASTRADOS NO BLING".
     */
    @Test
    void testGetAllCategory() throws Exception {
        // Cria uma resposta simulada do serviço
        RetornoResponse.Categorias categoria1 = new RetornoResponse.Categorias();
        categoria1.categoria = new CategoriaResponse();
        categoria1.categoria.setId("1");
        categoria1.categoria.setDescricao("Categoria 1");
        categoria1.categoria.setIdCategoriaPai("0");

        RetornoResponse.Categorias categoria2 = new RetornoResponse.Categorias();
        categoria2.categoria = new CategoriaResponse();
        categoria2.categoria.setId("2");
        categoria2.categoria.setDescricao("Categoria 2");
        categoria2.categoria.setIdCategoriaPai("1");

        RetornoResponse retorno = new RetornoResponse();
        retorno.categorias = new ArrayList<>();
        retorno.categorias.add(categoria1);
        retorno.categorias.add(categoria2);

        JsonResponse resposta = new JsonResponse();
        resposta.retorno = retorno;

        // Configura o comportamento do serviço simulado
        when(categoriaService.getAllCategory()).thenReturn(resposta);

        // Chama o método sendo testado
        JsonResponse result = categoriaController.getAllCategory().getBody();

        // Verifica se o serviço simulado foi chamado corretamente e se o resultado foi o esperado
        verify(categoriaService).getAllCategory();
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCAR A LISTA DE CATEGORIA A ENTRAR NO EXCEPTION".
     */
//    @Test
//    void testGetAllCategoryException() {
//        when(categoriaService.getAllCategory()).thenReturn(null);
//
//        // Chama o método sendo testado
//        assertThrows(CategoriaListaException.class, () -> {
//            categoriaController.getAllCategory();
//        });
//
//        // Verifica se o serviço foi chamado
//        verify(categoriaService).getAllCategory();
//    }

    /**
     * TESTE CONTROLLER - GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @Test
    void testGetCategoryByIdCategory() {
        String idCategoria = "123";

        JsonResponse resposta = new JsonResponse();
        RetornoResponse retorno = new RetornoResponse();

        ArrayList<RetornoResponse.Categorias> categorias = new ArrayList<>();
        RetornoResponse.Categorias categoria = new RetornoResponse.Categorias();

        categoria.categoria = new CategoriaResponse();
        categoria.categoria.setId(idCategoria);
        categoria.categoria.setDescricao("Categoria 1");
        categoria.categoria.setIdCategoriaPai("456");

        categorias.add(categoria);
        retorno.setCategorias(categorias);
        resposta.setRetorno(retorno);

        when(categoriaService.getCategoryByIdCategoria(idCategoria)).thenReturn(resposta);

        JsonResponse result = categoriaController.getCategoryByIdCategory(idCategoria).getBody();
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCA CATEGORIA PELO IDCATEGORIA A ENTRAR NO EXCEPTION".
     */
//    @Test
//    void testGetCategoryByIdCategoryException() {
//        String idCategoria = "123";
//        when(categoriaService.getCategoryByIdCategoria(idCategoria)).thenReturn(null);
//
//        // Chama o método sendo testado
//        assertThrows(CategoriaIdCategoriaException.class, () -> {
//            categoriaController.getCategoryByIdCategory(idCategoria);
//        });
//
//        // Verifica se o serviço foi chamado
//        verify(categoriaService).getCategoryByIdCategoria(idCategoria);
//    }

    /**
     * TESTE CONTROLLER - POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML/JSON".
     */
    @Test
    void testCreateCategory() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<categorias>\n" +
                "     <categoria>\n" +
                "          <descricao>Calçado</descricao>\n" +
                "          <idCategoriaPai>0</idCategoriaPai>\n" +
                "      </categoria>\n" +
                "   </categorias>";

        // Simula a resposta da chamada para o serviço de categoria
        JsonRequest resposta = new JsonRequest();
        RetornoRequest retorno = new RetornoRequest();

        ArrayList<ArrayList<RetornoRequest.Categorias>> categorias = new ArrayList<>();
        ArrayList<RetornoRequest.Categorias> categoriasList = new ArrayList<>();
        RetornoRequest.Categorias categoria = new RetornoRequest.Categorias();

        categoria.categoria = new CategoriaRequest();
        categoria.categoria.setId("01");
        categoria.categoria.setDescricao("Calçado");
        categoria.categoria.setIdCategoriaPai(0);

        categoriasList.add(categoria);
        categorias.add(categoriasList);
        retorno.setCategorias(categorias);
        resposta.setRetorno(retorno);

        when(categoriaService.createCategory(xml)).thenReturn(resposta);

        JsonRequest result = (JsonRequest) categoriaController.createCategory(xml).getBody();
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - POST "FORÇA O METODO DE CADASTRO DE CATEGORIA A ENTRAR NO EXCEPTION".
     */
//    @Test
//    void testCreateCategoryException() {
//        // Cria o XML de categoria a ser enviado na requisição
//        String xml = "<categorias>\n" +
//                "     <categoria>\n" +
//                "          <descricao>Calçado</descricao>\n" +
//                "          <idCategoriaPai>0</idCategoriaPai>\n" +
//                "      </categoria>\n" +
//                "   </categorias>";
//
//        // Cria um mock do serviço que retorna null
//        when(categoriaService.createCategory(xml)).thenReturn(null);
//
//        // Chama o método sendo testado e espera a exceção correta
//        ResponseEntity<?> response = categoriaController.createCategory(xml);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//
//        // Verifica se o serviço foi chamado
//        verify(categoriaService).createCategory(xml);
//    }

    /**
     * TESTE CONTROLLER - PUT "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @Test
    void testUpdateCategory() {
        // Cria o XML de deposito a ser enviado na requisição
        String idCategoria = "158365";
        String xml = "<categorias>\n" +
                "     <categoria>\n" +
                "          <descricao>Calçado</descricao>\n" +
                "          <idCategoriaPai>0</idCategoriaPai>\n" +
                "      </categoria>\n" +
                "   </categorias>";

        // Simula a resposta da chamada para o serviço de categoria
        JsonRequest resposta = new JsonRequest();
        RetornoRequest retorno = new RetornoRequest();

        ArrayList<ArrayList<RetornoRequest.Categorias>> categorias = new ArrayList<>();
        ArrayList<RetornoRequest.Categorias> categoriasList = new ArrayList<>();
        RetornoRequest.Categorias categoria = new RetornoRequest.Categorias();

        categoria.categoria = new CategoriaRequest();
        categoria.categoria.setId(idCategoria);
        categoria.categoria.setDescricao("Calçado");
        categoria.categoria.setIdCategoriaPai(0);

        categoriasList.add(categoria);
        categorias.add(categoriasList);
        retorno.setCategorias(categorias);
        resposta.setRetorno(retorno);

        when(categoriaService.updateCategory(xml, idCategoria)).thenReturn(resposta);

        JsonRequest result = categoriaController.updateCategory(xml, idCategoria).getBody();
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - PUT "FORÇA O METODO DE CADASTRO DE CATEGORIA A ENTRAR NO EXCEPTION".
     */
//    @Test
//    void testUpdateCategoryException() {
//        String idCategoria = "159357";
//        String xml = "<categorias>\n" +
//                "     <categoria>\n" +
//                "          <descricao>Calçado</descricao>\n" +
//                "          <idCategoriaPai>0</idCategoriaPai>\n" +
//                "      </categoria>\n" +
//                "   </categorias>";
//
//        // Cria um mock do serviço que retorna null
//        when(categoriaService.updateCategory(xml, idCategoria)).thenReturn(null);
//
//        // Chama o método sendo testado e espera a exceção correta
//        ResponseEntity<?> response = categoriaController.updateCategory(xml, idCategoria);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//
//        // Verifica se o serviço foi chamado
//        verify(categoriaService).updateCategory(xml, idCategoria);
//    }
}