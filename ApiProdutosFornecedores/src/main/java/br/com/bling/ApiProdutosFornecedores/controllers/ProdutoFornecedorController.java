package br.com.bling.ApiProdutosFornecedores.controllers;

import br.com.bling.ApiProdutosFornecedores.controllers.request.JsonRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.response.FornecedoresResponse;
import br.com.bling.ApiProdutosFornecedores.controllers.response.JsonResponse;
import br.com.bling.ApiProdutosFornecedores.controllers.response.RetornoResponse;
import br.com.bling.ApiProdutosFornecedores.exceptions.*;
import br.com.bling.ApiProdutosFornecedores.service.ProdutoFornecedorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1")                  //Padrão para os métodos /api/...
@Api(value = "API REST PRODUTOS FORNECEDORES")      //Swagger
@CrossOrigin(origins = "*")                         //Liberar os dominios da API
public class ProdutoFornecedorController {

    @Autowired
    private ProdutoFornecedorService produtoFornecedorService;

    /**
     * GET "BUSCAR LISTA DE PRODUTOS FORNECEDORES".
     */
    @GetMapping("/produtosfornecedores")
    @ApiOperation(value = "Retorna uma lista de produtos fornecedors")
    public ResponseEntity<JsonResponse> getAllProducts() {
        try {
            JsonResponse request = produtoFornecedorService.getAllProducts();

            if (request.retorno.produtosfornecedores == null && request.retorno.erros == null) {
                throw new ProdutoFornecedorListaException("Não foi possível localizar a lista de produto fornecedor");
            }

            if (request.retorno.produtosfornecedores != null) {
                for (RetornoResponse.Produtosfornecedores listaProdutoFornecedor : request.getRetorno().getProdutosfornecedores()) {
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("Id Produto: " + listaProdutoFornecedor.produtofornecedores.idProduto);
                    System.out.println("-----------------------------------------------------------------------------------");

                    for (FornecedoresResponse fornecedor : listaProdutoFornecedor.produtofornecedores.fornecedores) {
                        System.out.println("idProdutoFornecedor: " + fornecedor.produtoFornecedor.idProdutoFornecedor);
                        System.out.println("idFornecedor " + fornecedor.produtoFornecedor.idFornecedor);
                        System.out.println("produtoDescricao: " + fornecedor.produtoFornecedor.produtoDescricao);
                        System.out.println("produtoCodigo: " + fornecedor.produtoFornecedor.produtoCodigo);
                        System.out.println("precoCompra: " + fornecedor.produtoFornecedor.precoCompra);
                        System.out.println("precoCusto: " + fornecedor.produtoFornecedor.precoCusto);
                        System.out.println("produtoGarantia : " + fornecedor.produtoFornecedor.produtoGarantia);
                        System.out.println("padrao: " + fornecedor.produtoFornecedor.padrao);
                        System.out.println("-----------------------------------------------------------------------------------");
                    }
                }
            }

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new ApiProdutoFornecedorException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO FORNECEDOR PELO IDPRODUTOFORNECEDOR".
     */
    @GetMapping("/produtosfornecedores/{idProdutoFornecedor}")
    @ApiOperation(value = "Retorna um produto fornecedor pelo idProdutoFornecedor")
    public ResponseEntity<JsonResponse> getProducId(@PathVariable String idProdutoFornecedor) {
        try {
            JsonResponse request = produtoFornecedorService.getProducId(idProdutoFornecedor);

            if (request.retorno.produtosfornecedores == null && request.retorno.erros == null) {
                throw new ProdutoFornecedorIdException("Não foi possível localizar o produto fornecedor pelo Id: " + idProdutoFornecedor);
            }

            if (request.retorno.produtosfornecedores != null) {
                for (RetornoResponse.Produtosfornecedores listaProdutoFornecedor : request.getRetorno().getProdutosfornecedores()) {
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("Id Produto: " + listaProdutoFornecedor.produtofornecedores.idProduto);
                    System.out.println("-----------------------------------------------------------------------------------");

                    for (FornecedoresResponse fornecedor : listaProdutoFornecedor.produtofornecedores.fornecedores) {
                        System.out.println("idProdutoFornecedor: " + fornecedor.produtoFornecedor.idProdutoFornecedor);
                        System.out.println("idFornecedor " + fornecedor.produtoFornecedor.idFornecedor);
                        System.out.println("produtoDescricao: " + fornecedor.produtoFornecedor.produtoDescricao);
                        System.out.println("produtoCodigo: " + fornecedor.produtoFornecedor.produtoCodigo);
                        System.out.println("precoCompra: " + fornecedor.produtoFornecedor.precoCompra);
                        System.out.println("precoCusto: " + fornecedor.produtoFornecedor.precoCusto);
                        System.out.println("produtoGarantia : " + fornecedor.produtoFornecedor.produtoGarantia);
                        System.out.println("padrao: " + fornecedor.produtoFornecedor.padrao);
                        System.out.println("-----------------------------------------------------------------------------------");
                    }
                }
            }

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new ApiProdutoFornecedorException("Houve algum erro sistemico, tente novamente", e);
        }
    }

    /**
     * POST "CADASTRA UM NOVO PRODUTO FORNECEDOR" UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrarprodutofornecedor", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastra um novo produto fornecedor")
    public ResponseEntity<JsonRequest> createProduct(@RequestBody String xmlProdutoFornecedor) {
        try {
            JsonRequest request = produtoFornecedorService.createProduct(xmlProdutoFornecedor);

            if (request.retorno.produtosfornecedores == null && request.retorno.erros == null) {
                throw new ProdutoFornecedorCadastroException("Cadastro não efetuado, revise os campos e tente novamente!");
            }

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);

        } catch (Exception e) {
            throw new ApiProdutoFornecedorException("Houve algum erro sistemico, possivelmente o produto fornecedor está associado a algum produto, tente novamente", e);
        }
    }

    /**
     * PUT "ATUALIZA PRODUTO FORNECEDOR EXISTENTE PELO IDPRODUTOFORNECEDOR UTILIZANDO XML".
     */
    @PutMapping(path = "/atualizarprodutofornecedor/{idProdutoFornecedor}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualiza um produto fornecedor existente")
    public ResponseEntity<JsonRequest> updateProduct(@RequestBody String xmlProdutoFornecedor, @PathVariable("idProdutoFornecedor") String idProdutoFornecedor) {
        try {
            JsonRequest request = produtoFornecedorService.updateProduct(xmlProdutoFornecedor, idProdutoFornecedor);

            if (request.retorno.produtosfornecedores == null && request.retorno.erros == null) {
                throw new ProdutoFornecedorAtualizarException("Não foi possível atualizar o produto fornecedor pelo Id: " + idProdutoFornecedor);
            }

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);

        } catch (Exception e) {
            throw new ApiProdutoFornecedorException("Houve algum erro sistemico, tente novamente", e);
        }
    }
}