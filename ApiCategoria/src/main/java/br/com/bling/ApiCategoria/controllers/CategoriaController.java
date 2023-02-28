package br.com.bling.ApiCategoria.controllers;

import br.com.bling.ApiCategoria.controllers.request.RespostaRequest;
import br.com.bling.ApiCategoria.controllers.response.RetornoResponse;
import br.com.bling.ApiCategoria.exceptions.ApiCategoriaException;
import br.com.bling.ApiCategoria.exceptions.CategoriaCadastroException;
import br.com.bling.ApiCategoria.exceptions.CategoriaIdCategoriaNaoEncontradoException;
import br.com.bling.ApiCategoria.exceptions.CategoriaListaNaoEncontradoException;
import br.com.bling.ApiCategoria.controllers.response.RespostaResponse;
import br.com.bling.ApiCategoria.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public RespostaResponse getAllCategory() {
        try {
            RespostaResponse request = categoriaService.getAllCategory();

            if (request.retorno.getCategorias() == null || request.getRetorno() == null) {
                throw new ApiCategoriaException("Não foi possível localizar a lista de categorias");
            }

            for (RetornoResponse.Categorias listaCategoria : request.getRetorno().getCategorias()) {
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Id Categoria: " + listaCategoria.categoria.id);
                System.out.println("Descrição: " + listaCategoria.categoria.descricao);
                System.out.println("Id Categoria Pai: " + listaCategoria.categoria.idCategoriaPai);
                System.out.println("-------------------------------------------------------------------");
            }

            System.out.println(request);

            return request;
        } catch (Exception e) {
            throw new CategoriaListaNaoEncontradoException();
        }
    }

    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @GetMapping("/categoria/{idCategoria}")
    @ApiOperation(value = "Retorna uma categoria pelo idCategoria")
    public RespostaResponse getCategoryByIdCategory(@PathVariable String idCategoria) {
        try {
            RespostaResponse request = categoriaService.getCategoryByIdCategoria(idCategoria);

            if (request.retorno.categorias == null || request.getRetorno() == null) {
                throw new ApiCategoriaException("Não foi possível localizar a categoria pelo idCategoria");
            }

            for (RetornoResponse.Categorias listaCategoria : request.getRetorno().getCategorias()) {
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Id Categoria: " + listaCategoria.categoria.id);
                System.out.println("Descrição: " + listaCategoria.categoria.descricao);
                System.out.println("Id Categoria Pai: " + listaCategoria.categoria.idCategoriaPai);
                System.out.println("-------------------------------------------------------------------");
            }

            System.out.println(request);

            return request;
        } catch (Exception e) {
            throw new CategoriaIdCategoriaNaoEncontradoException(idCategoria);
        }
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML". -----> CRIAR EXCEPTION
     */
    @PostMapping(path = "/cadastrarcategoria", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public RespostaRequest createCategory(@RequestBody String xml) {
        try {
            RespostaRequest request = categoriaService.createCategory(xml);

//            if (request.retorno.categorias == null || request.getRetorno() == null) {
//                throw new ApiCategoriaException("Não foi possível cadastrar a categoria");
//            }

            return request;
        } catch (Exception e) {
            throw new CategoriaCadastroException();
        }
    }

    /**
     * PUT "ATUALIZA UMA CATEGORIA EXISTENTE UTILIZANDO XML".  -----> HttpClientErrorException$Unauthorized: 401 Unauthorized: [no body]
     */
    @PutMapping(path = "/atualizarcategoria/{idCategoria}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public RespostaRequest updateCategory(@RequestBody String xml, @PathVariable String idCategoria) {
        try {
            RespostaRequest request = categoriaService.updateCategory(xml, idCategoria);

            if (request.retorno.categorias == null || request.getRetorno() == null) {
                throw new ApiCategoriaException("Não foi possível atualizar a categoria");
            }
            System.out.println(request);

            return request;
        } catch (Exception e) {
            throw new CategoriaCadastroException();
        }
    }
}