package br.com.bling.ApiCategorias.controllers;

import br.com.bling.ApiCategorias.controllers.request.JsonRequest;
import br.com.bling.ApiCategorias.controllers.response.JsonResponse;
import br.com.bling.ApiCategorias.controllers.response.RetornoResponse;
import br.com.bling.ApiCategorias.exceptions.*;
import br.com.bling.ApiCategorias.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1")        //Padrão para os métodos /api/...
@Api(value = "API REST CATEGORIAS")    //Swagger
@CrossOrigin(origins = "*")        // Liberar os dominios da API
@Validated
public class CategoriaController {

    @Autowired
    public  CategoriaService categoriaService;


    /**
     * GET "BUSCA A LISTA DE CATEGORIAS".
     */
    @GetMapping("/categorias")
    @ApiOperation(value = "Retorna uma lista de categorias")
    public ResponseEntity<JsonResponse> getAllCategory() {
        try {
            JsonResponse request = categoriaService.getAllCategory();

            if (request.retorno.categorias == null && request.retorno.erros == null) {
                throw new CategoriaListaException("Não foi possível localizar a lista de categorias");
            }

            if (request.retorno.categorias != null) {
                for (RetornoResponse.Categorias listaCategoria : request.getRetorno().getCategorias()) {
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("Id Categoria: " + listaCategoria.categoria.id);
                    System.out.println("Descrição: " + listaCategoria.categoria.descricao);
                    System.out.println("Id Categoria Pai: " + listaCategoria.categoria.idCategoriaPai);
                    System.out.println("-------------------------------------------------------------------");
                }
            }

            System.out.println("GET: " + request);

            return ResponseEntity.ok(request);

        } catch (Exception e) {
            throw new ApiCategoriaException("Houve algum erro sistemico, tente novamente", e);
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

            if (request.retorno.categorias == null && request.retorno.erros == null) {
                throw new CategoriaIdCategoriaException("Contato com o número de CPF/CNPJ: " + idCategoria + " não encontrado.");
            }
            if (request.retorno.categorias != null) {
                for (RetornoResponse.Categorias listaCategoria : request.getRetorno().getCategorias()) {
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("Id Categoria: " + listaCategoria.categoria.id);
                    System.out.println("Descrição: " + listaCategoria.categoria.descricao);
                    System.out.println("Id Categoria Pai: " + listaCategoria.categoria.idCategoriaPai);
                    System.out.println("-------------------------------------------------------------------");
                }
            }

            System.out.println("GET ID: " + request);

            return ResponseEntity.ok(request);

        } catch (Exception e) {
            throw new ApiCategoriaException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @PostMapping(path = "/cadastrarcategoria", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar uma categoria")
    public ResponseEntity<JsonRequest> createCategory(@RequestBody @Valid String xmlCategoria) {
        try {
            JsonRequest request = categoriaService.createCategory(xmlCategoria);

            if (request.retorno.categorias == null && request.retorno.erros == null) {
                throw new CategoriaCadastroException("Cadastro não efetuado, revise os campos e tente novamente!");
            }

            System.out.println("POST: " + request);

            return ResponseEntity.ok(request);

        } catch (Exception e) {
            throw new ApiCategoriaException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * PUT "ATUALIZA UMA CATEGORIA EXISTENTE UTILIZANDO XML".
     */
    @PutMapping(path = "/atualizarcategoria/{idCategoria}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualiza uma categoria")
    public ResponseEntity<JsonRequest> updateCategory(@RequestBody String xmlCategoria, @PathVariable("idCategoria") String idCategoria) {
        try {
            JsonRequest request = categoriaService.updateCategory(xmlCategoria, idCategoria);

            if (request.retorno.categorias == null && request.retorno.erros == null) {
                throw new CategoriaAtualizarException("Não foi possível atualizar a categoria pelo Id: " + idCategoria);
            }
            System.out.println("UPDATE: " + request);

            return ResponseEntity.ok(request);

        } catch (Exception e) {
            throw new ApiCategoriaException("Houve algum erro sistemico, tente novamente", e);
        }
    }
}