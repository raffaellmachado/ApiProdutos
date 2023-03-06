package br.com.bling.ApiCategoria.controllers;

import br.com.bling.ApiCategoria.controllers.request.RespostaRequest;
import br.com.bling.ApiCategoria.controllers.request.RetornoRequest;
import br.com.bling.ApiCategoria.controllers.response.RetornoResponse;
import br.com.bling.ApiCategoria.exceptions.ApiCategoriaException;
import br.com.bling.ApiCategoria.exceptions.CategoriaCadastroException;
import br.com.bling.ApiCategoria.exceptions.CategoriaIdCategoriaException;
import br.com.bling.ApiCategoria.exceptions.CategoriaListaException;
import br.com.bling.ApiCategoria.controllers.response.RespostaResponse;
import br.com.bling.ApiCategoria.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    public ResponseEntity<RespostaResponse> getAllCategory() {
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

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new CategoriaListaException();
        }
    }

    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @GetMapping("/categoria/{idCategoria}")
    @ApiOperation(value = "Retorna uma categoria pelo idCategoria")
    public ResponseEntity<RespostaResponse> getCategoryByIdCategory(@PathVariable String idCategoria) {
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

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new CategoriaIdCategoriaException(idCategoria);
        }
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @PostMapping(path = "/cadastrarcategoria", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public ResponseEntity<RespostaRequest> createCategory(@RequestBody String xml) {
        try {
            RespostaRequest request = categoriaService.createCategory(xml);

            if (request.retorno.categorias == null || request.getRetorno() == null) {
                throw new ApiCategoriaException("Não foi possível cadastrar a categoria");
            }

            for (ArrayList<RetornoRequest.Categorias> listaCategoria : request.getRetorno().getCategorias()) {
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Id Categoria: " + listaCategoria.get(0).categoria.id);
                System.out.println("Descrição: " + listaCategoria.get(0).categoria.descricao);
                System.out.println("Id Categoria Pai: " + listaCategoria.get(0).categoria.idCategoriaPai);
                System.out.println("-------------------------------------------------------------------");
            }
            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new CategoriaCadastroException();
        }
    }

    /**
     * PUT "ATUALIZA UMA CATEGORIA EXISTENTE UTILIZANDO XML".  -----> HttpClientErrorException$Unauthorized: 401 Unauthorized: [no body]
     */
    @PutMapping(path = "/atualizarcategoria/{idCategoria}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public ResponseEntity<RespostaRequest> updateCategory(@RequestBody String xml, @PathVariable String idCategoria) {
        try {
            RespostaRequest request = categoriaService.updateCategory(xml, idCategoria);

            if (request.retorno.categorias == null || request.getRetorno() == null) {
                throw new ApiCategoriaException("Não foi possível atualizar a categoria");
            }
            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new CategoriaCadastroException();
        }
    }
}