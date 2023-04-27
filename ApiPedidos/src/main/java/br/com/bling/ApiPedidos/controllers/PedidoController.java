package br.com.bling.ApiPedidos.controllers;


import br.com.bling.ApiPedidos.controllers.response.JsonResponse;
import br.com.bling.ApiPedidos.service.PedidoService;
import br.com.bling.ApiPedidos.exceptions.ApiPedidoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

//    /**
//     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
//     */
//    @GetMapping("/pedido/{idPedido}")
//    @ApiOperation(value = "Retorna uma categoria pelo idCategoria")
//    public ResponseEntity<JsonResponse> getCategoryByIdCategory(@PathVariable("idPedido") String idPedido) {
//        try {
//            JsonResponse request = categoriaService.getCategoryByIdCategoria(idPedido);
//
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
//
//            System.out.println("GET ID: " + request);
//
//            return ResponseEntity.ok(request);
//
//        } catch (Exception e) {
//            throw new ApiCategoriaException("Houve algum erro sistemico, tente novamente", e);
//        }
//    }
//
//    /**
//     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
//     */
//    @PostMapping(path = "/cadastrarpedido", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "Cadastrar um pedido")
//    public ResponseEntity<JsonRequest> createCategory(@RequestBody @Valid String xmlCategoria) {
//        try {
//            JsonRequest request = categoriaService.createCategory(xmlCategoria);
//
//            if (request.retorno.categorias == null && request.retorno.erros == null) {
//                throw new CategoriaCadastroException("Cadastro não efetuado, revise os campos e tente novamente!");
//            }
//
//            System.out.println("POST: " + request);
//
//            return ResponseEntity.ok(request);
//
//        } catch (Exception e) {
//            throw new ApiCategoriaException("Houve algum erro sistemico, tente novamente", e);
//        }
//    }
//
//    /**
//     * PUT "ATUALIZA UMA CATEGORIA EXISTENTE UTILIZANDO XML".
//     */
//    @PutMapping(path = "/atualizarpedido/{idPedido}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "Atualiza um pedido")
//    public ResponseEntity<JsonRequest> updateCategory(@RequestBody String xmlPedido, @PathVariable("idPedido") String idPedido) {
//        try {
//            JsonRequest request = categoriaService.updateCategory(xmlPedido, idPedido);
//
//            if (request.retorno.categorias == null && request.retorno.erros == null) {
//                throw new CategoriaAtualizarException("Não foi possível atualizar a categoria pelo Id: " + idPedido);
//            }
//            System.out.println("UPDATE: " + request);
//
//            return ResponseEntity.ok(request);
//
//        } catch (Exception e) {
//            throw new ApiCategoriaException("Houve algum erro sistemico, tente novamente", e);
//        }
//    }
}