package br.com.bling.ApiCategoria.controllers;

import br.com.bling.ApiCategoria.controllers.request.JsonRequest;
import br.com.bling.ApiCategoria.controllers.response.JsonResponse;
import br.com.bling.ApiCategoria.controllers.response.RetornoResponse;
import br.com.bling.ApiCategoria.exceptions.ApiCategoriaException;
import br.com.bling.ApiCategoria.exceptions.CategoriaIdCategoriaException;
import br.com.bling.ApiCategoria.exceptions.CategoriaListaException;
import br.com.bling.ApiCategoria.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.validation.Valid;

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
    public ResponseEntity<JsonResponse> getAllCategory() {
        try {
            JsonResponse request = categoriaService.getAllCategory();

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

            return ResponseEntity.ok(request);
        } catch (Exception e) {
            throw new CategoriaListaException();
        }
    }

    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @GetMapping("/categoria/{idCategoria}")
    @ApiOperation(value = "Retorna uma categoria pelo idCategoria")
    public ResponseEntity<JsonResponse> getCategoryByIdCategory(@PathVariable("idCategoria") String idCategoria) {
        try {
            JsonResponse request = categoriaService.getCategoryByIdCategoria(idCategoria);

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

            return ResponseEntity.ok(request);
        } catch (Exception e) {
            throw new CategoriaIdCategoriaException(idCategoria);
        }
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @PostMapping(path = "/cadastrarcategoria", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public ResponseEntity<?> createCategory(@RequestBody @Valid String xmlCategoria) {
        try {
            Object request = categoriaService.createCategory(xmlCategoria);

            if (request == null) {
                throw new ApiCategoriaException("Não foi possível cadastrar a categoria");
            }
            System.out.println(request);

            return ResponseEntity.ok(request);

        } catch (ApiCategoriaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(new JsonRequest());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonRequest());
        }
    }

    /**
     * PUT "ATUALIZA UMA CATEGORIA EXISTENTE UTILIZANDO XML".  -----> HttpClientErrorException$Unauthorized: 401 Unauthorized: [no body]
     */
    @PutMapping(path = "/atualizarcategoria/{idCategoria}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public ResponseEntity<?> updateCategory(@RequestBody String xmlCategoria, @PathVariable("idCategoria") String idCategoria) {
        try {
            Object request = categoriaService.updateCategory(xmlCategoria, idCategoria);

            if (request == null) {
                throw new ApiCategoriaException("Não foi possível atualizar a categoria");
            }
            System.out.println(request);

            return ResponseEntity.ok(request);

        } catch (ApiCategoriaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(new JsonRequest());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonRequest());
        }
    }
}