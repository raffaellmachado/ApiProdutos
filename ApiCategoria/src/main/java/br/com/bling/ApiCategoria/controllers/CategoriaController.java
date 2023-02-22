package br.com.bling.ApiCategoria.controllers;

import br.com.bling.ApiCategoria.exceptions.ApiCategoriaException;
import br.com.bling.ApiCategoria.exceptions.CategoriaCadastroException;
import br.com.bling.ApiCategoria.exceptions.CategoriaIdCategoriaNaoEncontradoException;
import br.com.bling.ApiCategoria.exceptions.CategoriaListaNaoEncontradoException;
import br.com.bling.ApiCategoria.models.Resposta;
import br.com.bling.ApiCategoria.models.Retorno;
import br.com.bling.ApiCategoria.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/v1")        //Padrão para os métodos /api/...
@Api(value = "API REST CATEGORIA")    //Swagger
@CrossOrigin(origins = "*")        // Liberar os dominios da API
@Validated
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    private String idCategoria;

    private String id;

    /**
     * GET "BUSCA A LISTA DE CATEGORIAS".
     */
    @GetMapping("/categorias")
    @ApiOperation(value = "Retorna uma lista de categorias")
    public Resposta getCategory() {
        Resposta request = categoriaService.getCategory();

        if (request.retorno.getCategorias() == null || request.getRetorno() == null) {
            throw new CategoriaListaNaoEncontradoException();
        }

        for (Retorno.Categoria listaCategoria : request.getRetorno().getCategorias()) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Id Categoria: " + listaCategoria.categoria.id);
            System.out.println("Descrição: " + listaCategoria.categoria.descricao);
            System.out.println("Id Categoria Pai: " + listaCategoria.categoria.idCategoriaPai);
            System.out.println("-------------------------------------------------------------------");
        }

        System.out.println(request);

        return request;
    }

    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @GetMapping("/categoria/{idCategoria}")
    @ApiOperation(value = "Retorna uma categoria pelo idCategoria")
    public Resposta getCategoryByIdCategory(@PathVariable String idCategoria) {
        Resposta request = categoriaService.getCategoryByIdCategoria(idCategoria);

        if (request.retorno.categorias == null || request.getRetorno() == null) {
            throw new CategoriaIdCategoriaNaoEncontradoException(idCategoria);
        }

        for (Retorno.Categoria listaCategoria : request.getRetorno().getCategorias()) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Id Categoria: " + listaCategoria.categoria.id);
            System.out.println("Descrição: " + listaCategoria.categoria.descricao);
            System.out.println("Id Categoria Pai: " + listaCategoria.categoria.idCategoriaPai);
            System.out.println("-------------------------------------------------------------------");
        }

        System.out.println(request);

        return request;
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML". -----> CRIAR EXCEPTION
     */
    @PostMapping(path = "/cadastrarcategoria", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public String createCategory(@RequestBody String xml) {

        String request = categoriaService.createCategory(xml);

        return request;
    }

    /**
     * PUT "ATUALIZA UMA CATEGORIA EXISTENTE UTILIZANDO XML".  -----> CORRIGIR ESTA DANDO ERRO 500 AO TESTAR NO POSTMAN
     */
    @PutMapping(path = "/atualizarcategoria/{idCategoriaPai}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public String updateCategory(@RequestBody String xml, @PathVariable String idCategoria) {

        String request = categoriaService.updateCategory(xml, id);
        System.out.println(request);

        return request;
    }
}