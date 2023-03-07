package br.com.bling.ApiProdutosFornecedores.controllers;

import br.com.bling.ApiProdutosFornecedores.controllers.request.JsonRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.response.FornecedoreResponse;
import br.com.bling.ApiProdutosFornecedores.controllers.response.JsonResponse;
import br.com.bling.ApiProdutosFornecedores.controllers.response.RetornoResponse;
import br.com.bling.ApiProdutosFornecedores.exceptions.ApiProdutoFornecedorException;
import br.com.bling.ApiProdutosFornecedores.exceptions.ProdutoFornecedorIdException;
import br.com.bling.ApiProdutosFornecedores.exceptions.ProdutoFornecedorListaException;
import br.com.bling.ApiProdutosFornecedores.service.ProdutoFornecedorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "/api/v1")        //Padrão para os métodos /api/...
@Api(value = "API REST PRODUTOS")    //Swagger
@CrossOrigin(origins = "*")        // Liberar os dominios da API
public class ProdutoFornecedorController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProdutoFornecedorService produtoFornecedorService;
    private String codigo;

    /**
     * GET "BUSCAR LISTA DE PRODUTOS FORNECEDORES".
     */
    @GetMapping("/produtosfornecedores")
    @ApiOperation(value = "Retorna uma lista de produtos fornecedors")
    public ResponseEntity<JsonResponse> getAllProducts() {
        JsonResponse request = produtoFornecedorService.getAllProducts();
        try {
            if (request.retorno.produtosfornecedores == null || request.getRetorno() == null) {
                throw new ApiProdutoFornecedorException("Não foi possível localizar a lista de produto fornecedor");
            }

            for (RetornoResponse.Produtosfornecedores listaProdutoFornecedor : request.getRetorno().getProdutosfornecedores()) {
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("Id Produto: " + listaProdutoFornecedor.produtofornecedores.idProduto);
                System.out.println("-----------------------------------------------------------------------------------");

                for (FornecedoreResponse fornecedor : listaProdutoFornecedor.produtofornecedores.fornecedores) {
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

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new ProdutoFornecedorListaException();
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO FORNECEDOR PELO ID".
     */
    @GetMapping("/produtosfornecedores/{idProdutoFornecedor}")
    @ApiOperation(value = "Retorna um produto fornecedor pelo ID")
    public ResponseEntity<JsonResponse> getProducId(@PathVariable String idProdutoFornecedor) {
        try {
            JsonResponse request = produtoFornecedorService.getProducId(idProdutoFornecedor);

            if (request.retorno.produtosfornecedores == null || request.getRetorno() == null) {
                throw new ApiProdutoFornecedorException("Não foi possível localizar produto fornecedor pelo Id");
            }

            for (RetornoResponse.Produtosfornecedores listaProdutoFornecedor : request.getRetorno().getProdutosfornecedores()) {
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("Id Produto: " + listaProdutoFornecedor.produtofornecedores.idProduto);
                System.out.println("-----------------------------------------------------------------------------------");

                for (FornecedoreResponse fornecedor : listaProdutoFornecedor.produtofornecedores.fornecedores) {
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

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception e) {
            throw new ProdutoFornecedorIdException(idProdutoFornecedor);
        }
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO FORNECEDOR" UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrarprodutofornecedor", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um novo produto fornecedor")
    public ResponseEntity<?> createProduct(@RequestBody String xmlProdutoFornecedor) {
        try {
            Object request = produtoFornecedorService.createProduct(xmlProdutoFornecedor);

            if (request == null || request.toString().contains("404")) {
                throw new ApiProdutoFornecedorException("Não foi possível cadastrar o produto fornecedor, " +
                        "este fornecedor está vinculado ao produto informado. \n"  +
                        "Desvincule o fornecedor do produto e tente novamente.");
            }

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);

        } catch (ApiProdutoFornecedorException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(new JsonRequest());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonRequest());
        }
    }

    /**
     * PUT "ATUALIZAR PRODUTO FORNECEDOR PELO ID" UTILIZANDO XML. -----> HttpClientErrorException$Unauthorized: 401 Unauthorized: [no body]
     */
    @PutMapping(path = "/atualizarprodutofornecedor/{idProdutoFornecedor}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualizar um produto fornecedor existente")
    public ResponseEntity<?> updateProduct(@RequestBody String xmlProdutoFornecedor, @PathVariable("idProdutoFornecedor") String idProdutoFornecedor) {
        try {
            Object request = produtoFornecedorService.updateProduct(xmlProdutoFornecedor, idProdutoFornecedor);

            if (request == null) {
                throw new ApiProdutoFornecedorException("Não foi possível atualizar o produto fornecedor");
            }

            System.out.println(request);

            return ResponseEntity.status(HttpStatus.OK).body(request);

        } catch (ApiProdutoFornecedorException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(new JsonRequest());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonRequest());
        }
    }
}