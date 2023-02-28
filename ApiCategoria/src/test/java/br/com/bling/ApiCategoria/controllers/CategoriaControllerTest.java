package br.com.bling.ApiCategoria.controllers;

import br.com.bling.ApiCategoria.controllers.request.CategoriaRequest;
import br.com.bling.ApiCategoria.controllers.request.RespostaRequest;
import br.com.bling.ApiCategoria.controllers.request.RetornoRequest;
import br.com.bling.ApiCategoria.controllers.response.CategoriaResponse;
import br.com.bling.ApiCategoria.controllers.response.RespostaResponse;
import br.com.bling.ApiCategoria.controllers.response.RetornoResponse;
import br.com.bling.ApiCategoria.service.CategoriaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void testCreateCategory() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<categorias>\n" +
                "     <categoria>\n" +
                "          <descricao>CHUPA MEUS OVOS</descricao>\n" +
                "     </categoria>\n" +
                "</categorias>";


        // Simula a resposta da chamada para o serviço de categoria
        RespostaRequest respostaEsperada = new RespostaRequest();
        when(categoriaService.createCategory(xml)).thenReturn(respostaEsperada);

        // Chama o método de criação de categoria
        RespostaRequest resultado = categoriaController.createCategory(xml);

        // Verifica se a resposta do método está correta
        assertEquals(respostaEsperada, resultado);
        verify(categoriaService).createCategory(xml);

        System.out.println(resultado);
        System.out.println(respostaEsperada);
    }



//    @Test
//    void testUpdateCategory() {
//        when(categoriaService.updateCategory(anyString(), anyString())).thenReturn(new RespostaRequest());
//
//        RespostaRequest result = categoriaController.updateCategory("xml", "idCategoria");
//        Assertions.assertEquals(new RespostaRequest(), result);
//    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme