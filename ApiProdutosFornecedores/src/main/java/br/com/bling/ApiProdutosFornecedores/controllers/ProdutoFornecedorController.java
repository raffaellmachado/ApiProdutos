package br.com.bling.ApiProdutosFornecedores.controllers;

import br.com.bling.ApiProdutosFornecedores.controllers.request.RespostaRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.request.RetornoRequest;
import br.com.bling.ApiProdutosFornecedores.controllers.response.FornecedoreResponse;
import br.com.bling.ApiProdutosFornecedores.controllers.response.RespostaResponse;
import br.com.bling.ApiProdutosFornecedores.controllers.response.RetornoResponse;
import br.com.bling.ApiProdutosFornecedores.exceptions.*;
import br.com.bling.ApiProdutosFornecedores.service.ProdutoFornecedorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
    public RespostaResponse getAllProducts() {
        RespostaResponse request = produtoFornecedorService.getAllProducts();
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

        return request;
    } catch (Exception e) {
        throw new ProdutoFornecedorListaException();
    }
    }

    /**
     * GET "BUSCAR UM PRODUTO FORNECEDOR PELO ID".
     */
    @GetMapping("/produtosfornecedores/{idProdutoFornecedor}")
    @ApiOperation(value = "Retorna um produto fornecedor pelo ID")
    public RespostaResponse getProducId(@PathVariable String idProdutoFornecedor) {
        try {
            RespostaResponse request = produtoFornecedorService.getProducId(idProdutoFornecedor);

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

            return request;
        } catch (Exception e) {
            throw new ProdutoFornecedorIdException(idProdutoFornecedor);
        }
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO FORNECEDOR" UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrarprodutofornecedor", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um novo produto fornecedor")
    public RespostaRequest createProduct(@RequestBody String xml) {
        try {
            RespostaRequest request = produtoFornecedorService.createProduct(xml);

            if (request.retorno.produtosfornecedores == null || request.getRetorno() == null) {
                throw new ApiProdutoFornecedorException("Não foi possível cadastrar o produto");
            }

            for (RetornoRequest.Produtosfornecedore listaProdutoFornecedor : request.getRetorno().getProdutosfornecedores()) {
                    System.out.println("idProdutoFornecedor: " + listaProdutoFornecedor.produtoFornecedor.idFornecedor);
                    System.out.println("idFornecedor " + listaProdutoFornecedor.produtoFornecedor.idFornecedor);
                    System.out.println("produtoDescricao: " + listaProdutoFornecedor.produtoFornecedor.produtoDescricao);
                    System.out.println("produtoCodigo: " + listaProdutoFornecedor.produtoFornecedor.produtoCodigo);
                    System.out.println("precoCompra: " + listaProdutoFornecedor.produtoFornecedor.precoCompra);
                    System.out.println("precoCusto: " + listaProdutoFornecedor.produtoFornecedor.precoCusto);
                    System.out.println("produtoGarantia : " + listaProdutoFornecedor.produtoFornecedor.produtoGarantia);
                    System.out.println("padrao: " + listaProdutoFornecedor.produtoFornecedor.padrao);
                    System.out.println("-----------------------------------------------------------------------------------");
                }

            System.out.println("Produto cadastrado com sucesso!");

            return request;
        } catch (Exception e) {
            throw new ProdutoFornecedorCadastroException();
        }
    }

    /**
     * PUT "ATUALIZAR PRODUTO FORNECEDOR PELO ID" UTILIZANDO XML. -----> HttpClientErrorException$Unauthorized: 401 Unauthorized: [no body]
     */
    @PutMapping(path = "/atualizarprodutofornecedor/{idProdutoFornecedor}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualizar um produto fornecedor existente")
    public RespostaRequest updateProduct(@RequestBody String xml, @PathVariable String idProdutoFornecedor) {
        try {
            RespostaRequest request = produtoFornecedorService.updateProduct(xml, idProdutoFornecedor);

            if (request.retorno.produtosfornecedores == null || request.getRetorno() == null) {
                throw new ApiProdutoFornecedorException(idProdutoFornecedor);
            }
            System.out.println("Produto cadastrado com sucesso!");

            return request;
        } catch (Exception e) {
            throw new ProdutoFornecedorAtualizarException(idProdutoFornecedor);
        }
    }
}