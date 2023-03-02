package br.com.bling.ApiCategoria.controllers;

import static org.junit.Assert.*;
import br.com.bling.ApiCategoria.controllers.request.CategoriaRequest;
import br.com.bling.ApiCategoria.controllers.request.RespostaRequest;
import br.com.bling.ApiCategoria.controllers.request.RetornoRequest;
import br.com.bling.ApiCategoria.controllers.response.CategoriaResponse;
import br.com.bling.ApiCategoria.controllers.response.RespostaResponse;
import br.com.bling.ApiCategoria.controllers.response.RetornoResponse;
import br.com.bling.ApiCategoria.exceptions.CategoriaCadastroException;
import br.com.bling.ApiCategoria.exceptions.CategoriaIdCategoriaNaoEncontradoException;
import br.com.bling.ApiCategoria.exceptions.CategoriaListaNaoEncontradoException;
import br.com.bling.ApiCategoria.service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        categoria1.categoria.id = "1";
        categoria1.categoria.descricao = "Categoria 1";
        categoria1.categoria.idCategoriaPai = Integer.parseInt("0");

        RetornoResponse.Categorias categoria2 = new RetornoResponse.Categorias();
        categoria2.categoria = new CategoriaResponse();
        categoria2.categoria.id = "2";
        categoria2.categoria.descricao = "Categoria 2";
        categoria2.categoria.idCategoriaPai = Integer.parseInt("1");

        RetornoResponse retorno = new RetornoResponse();
        retorno.categorias = new ArrayList<>();
        retorno.categorias.add(categoria1);
        retorno.categorias.add(categoria2);

        RespostaResponse resposta = new RespostaResponse();
        resposta.retorno = retorno;

        // Configura o comportamento do serviço simulado
        when(categoriaService.getAllCategory()).thenReturn(resposta);

        // Chama o método sendo testado
        RespostaResponse result = categoriaController.getAllCategory();

        // Verifica se o serviço simulado foi chamado corretamente e se o resultado foi o esperado
        verify(categoriaService).getAllCategory();
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCAR A LISTA DE CATEGORIA A ENTRAR NO EXCEPTION".
     */
    @Test
    void testGetAllCategoryException() {
        String idProdutoFornecedor = "123";
        when(categoriaService.getAllCategory()).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(CategoriaListaNaoEncontradoException.class, () -> {
            categoriaController.getAllCategory();
        });

        // Verifica se o serviço foi chamado
        verify(categoriaService).getAllCategory();
    }

    /**
     * TESTE CONTROLLER - GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @Test
    void testGetCategoryByIdCategory() {
        String idCategoria = "123";

        RespostaResponse resposta = new RespostaResponse();
        RetornoResponse retorno = new RetornoResponse();

        ArrayList<RetornoResponse.Categorias> categorias = new ArrayList<>();
        RetornoResponse.Categorias categoria = new RetornoResponse.Categorias();

        categoria.categoria = new CategoriaResponse();
        categoria.categoria.id = idCategoria;
        categoria.categoria.descricao = "Categoria 1";
        categoria.categoria.idCategoriaPai = Integer.parseInt("456");

        categorias.add(categoria);
        retorno.setCategorias(categorias);
        resposta.setRetorno(retorno);

        when(categoriaService.getCategoryByIdCategoria(idCategoria)).thenReturn(resposta);

        RespostaResponse result = categoriaController.getCategoryByIdCategory(idCategoria);
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCA CATEGORIA PELO IDCATEGORIA A ENTRAR NO EXCEPTION".
     */
    @Test
    void testGetCategoryByIdCategoryException() {
        String idCategoria = "123";
        when(categoriaService.getCategoryByIdCategoria(idCategoria)).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(CategoriaIdCategoriaNaoEncontradoException.class, () -> {
            categoriaController.getCategoryByIdCategory(idCategoria);
        });

        // Verifica se o serviço foi chamado
        verify(categoriaService).getCategoryByIdCategoria(idCategoria);
    }
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
        RespostaRequest resposta = new RespostaRequest();
        RetornoRequest retorno = new RetornoRequest();

        ArrayList<ArrayList<RetornoRequest.Categorias>> categorias = new ArrayList<>();
        ArrayList<RetornoRequest.Categorias> categoriasList = new ArrayList<>();
        RetornoRequest.Categorias categoria = new RetornoRequest.Categorias();

        categoria.categoria = new CategoriaRequest();
        categoria.categoria.id = "01";
        categoria.categoria.descricao = "Calçado";
        categoria.categoria.idCategoriaPai = "0";

        categoriasList.add(categoria);
        categorias.add(categoriasList);
        retorno.setCategorias(categorias);
        resposta.setRetorno(retorno);

        when(categoriaService.createCategory(xml)).thenReturn(resposta);

        RespostaRequest result = categoriaController.createCategory(xml);
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - POST "FORÇA O METODO DE CADASTRO DE CATEGORIA A ENTRAR NO EXCEPTION".
     */
    @Test
    void testCreateProductException() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<categorias>\n" +
                "     <categoria>\n" +
                "          <descricao>Calçado</descricao>\n" +
                "          <idCategoriaPai>0</idCategoriaPai>\n" +
                "      </categoria>\n" +
                "   </categorias>";


        // Cria um mock do serviço que retorna null
        when(categoriaService.createCategory(xml)).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(CategoriaCadastroException.class, () -> {
            categoriaController.createCategory(xml);
        });

        // Verifica se o serviço foi chamado
        verify(categoriaService).createCategory(xml);
    }

    /**
     * TESTE CONTROLLER - PUT "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @Test
    void testUpdateCategory() {
//        when(categoriaService.updateCategory(anyString(), anyString())).thenReturn(new RespostaRequest());
//
//        RespostaRequest result = categoriaController.updateCategory("xml", "idCategoria");
//        Assertions.assertEquals(new RespostaRequest(), result);
    }
}