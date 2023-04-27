package br.com.bling.ApiPedidos.controllers;


import br.com.bling.ApiPedidos.controllers.request.JsonRequest;
import br.com.bling.ApiPedidos.controllers.response.JsonResponse;
import br.com.bling.ApiPedidos.service.PedidoService;
import br.com.bling.ApiPedidos.exceptions.ApiPedidoException;
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
@Api(value = "API REST PEDIDOS")    //Swagger
@CrossOrigin(origins = "*")        // Liberar os dominios da API
@Validated
public class PedidoController {

    @Autowired
    public PedidoService pedidoService;


    /**
     * GET "BUSCA A LISTA DE CATEGORIAS".
     */
    @GetMapping("/pedidos")
    @ApiOperation(value = "Retorna uma lista de categorias")
    public ResponseEntity<JsonResponse> getAllCategory() {
        try {
            JsonResponse request = pedidoService.getAllPedido();

//            if (request.retorno.pedidos == null && request.retorno.erros == null) {
//                throw new CategoriaListaException("Não foi possível localizar a lista de categorias");
//            }
//
//            if (request.retorno.categorias != null) {
//                for (RetornoResponse.Categorias listaCategoria : request.getRetorno().getCategorias()) {
//                    System.out.println("-------------------------------------------------------------------");
//                    System.out.println("Id Categoria: " + listaCategoria.categoria.id);
//                    System.out.println("Descrição: " + listaCategoria.categoria.descricao);
//                    System.out.println("Id Categoria Pai: " + listaCategoria.categoria.idCategoriaPai);
//                    System.out.println("-------------------------------------------------------------------");
//                }
//            }

            System.out.println("GET: " + request);

            return ResponseEntity.ok(request);

        } catch (Exception e) {
            throw new ApiPedidoException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @GetMapping("/pedido/{numero}")
    @ApiOperation(value = "Retorna uma categoria pelo numero")
    public ResponseEntity<JsonResponse> getCategoryByIdCategory(@PathVariable("numero") String numero) {
        try {
            JsonResponse request = pedidoService.getPedidoByIdPedido(numero);

//            if (request.retorno.categorias == null && request.retorno.erros == null) {
//                throw new CategoriaIdCategoriaException("Contato com o número de CPF/CNPJ: " + idPedido + " não encontrado.");
//            }
//            if (request.retorno.categorias != null) {
//                for (RetornoResponse.Categorias listaCategoria : request.getRetorno().getCategorias()) {
//                    System.out.println("-------------------------------------------------------------------");
//                    System.out.println("Id Categoria: " + listaCategoria.categoria.id);
//                    System.out.println("Descrição: " + listaCategoria.categoria.descricao);
//                    System.out.println("Id Categoria Pai: " + listaCategoria.categoria.idCategoriaPai);
//                    System.out.println("-------------------------------------------------------------------");
//                }
//            }

            System.out.println("GET ID: " + request);

            return ResponseEntity.ok(request);

        } catch (Exception e) {
            throw new ApiPedidoException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @PostMapping(path = "/cadastrarpedido", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um pedido")
    public ResponseEntity<JsonRequest> createCategory(@RequestBody @Valid String xmlPedido) {
        try {
            JsonRequest request = pedidoService.createPedido(xmlPedido);

//            if (request.retorno.categorias == null && request.retorno.erros == null) {
//                throw new CategoriaCadastroException("Cadastro não efetuado, revise os campos e tente novamente!");
//            }

            System.out.println("POST: " + request);

            return ResponseEntity.ok(request);

        } catch (Exception e) {
            throw new ApiPedidoException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * PUT "ATUALIZA UMA CATEGORIA EXISTENTE UTILIZANDO XML".
     */
    @PutMapping(path = "/atualizarpedido/{numero}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualiza um pedido")
    public ResponseEntity<JsonRequest> updatePedido(@RequestBody String xmlPedido, @PathVariable("numero") String numero) {
        try {
            JsonRequest request = pedidoService.updatePedido(xmlPedido, numero);

//            if (request.retorno.categorias == null && request.retorno.erros == null) {
//                throw new CategoriaAtualizarException("Não foi possível atualizar a categoria pelo Id: " + numero);
//            }
//            System.out.println("UPDATE: " + request);

            return ResponseEntity.ok(request);

        } catch (Exception e) {
            throw new ApiPedidoException("Houve algum erro sistemico, tente novamente", e);
        }
    }
}