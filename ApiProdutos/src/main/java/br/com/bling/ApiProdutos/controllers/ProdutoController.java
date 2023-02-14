package br.com.bling.ApiProdutos.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.bling.ApiProdutos.models.Resposta;
import br.com.bling.ApiProdutos.service.ProdutoService;
import io.swagger.annotations.Api;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "/api")        //Padrão para os métodos /api/...
@Api(value = "API REST PRODUTOS")    //Swagger
@CrossOrigin(origins = "*")        // Liberar os dominios da API
public class ProdutoController {

    private final ProdutoService produtoService;
    private String codigo;
    private String nomeFornecedor;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /**
     * GET LISTA DE PRODUTO.
     */
    @GetMapping("/produto")
    @ApiOperation(value = "Retorna uma lista de produtos")
    public Resposta getProduct() {
        return produtoService.getProduct();
    }

    /**
     * GET PRODUTO POR CÒDIGO.
     */
    @GetMapping("/produto/{codigo}")
    @ApiOperation(value = "Retorna um produto pelo código")
    public Resposta getProductById(@PathVariable String codigo) {
        this.codigo = codigo;
        return produtoService.getProductByCodigo(codigo);
    }

    /**
     * GET PRODUTO UTILIZANDO O CÓDIGO E NOME DO FORNECEDOR.
     */
    @GetMapping("/produto/{codigo}/{id_fornecedor}")
    @ApiOperation(value = "Retorna um produto pelo código com o nome do forcedor")
    public Resposta getProductByIdFornecedor(@PathVariable String codigo, String nomeFornecedor) {
        this.codigo = codigo;
        this.nomeFornecedor = nomeFornecedor;
        return produtoService.getProductByCodigoFornecedor(codigo, nomeFornecedor);
    }

    /**
     * DELETE PROUTO PELO CÓDIGO.
     */
    @DeleteMapping("/produto/{codigo}")
    @ApiOperation(value = "Deleta um produto pelo código")
    public void deleteProductByCodigo(@PathVariable String codigo) {
        this.codigo = codigo;
        produtoService.deleteProductByCodigo(codigo);
    }

    /**
     * POST DE UM NOVO PRODUTO UTILIZANDO XML.
     */
    @PostMapping(path = "/cadastrarproduto", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cadastrar um produto")
    public void registerProduct(@RequestBody String xml) {
        RestTemplate restTemplate = new RestTemplate();
        produtoService.postProductXml(xml);
    }

    /**
     * POST ATUALIZA UM PRODUTO A PARTIR DO SEU CODIGO UTILIZANDO XML
     */
    @PostMapping(path = "/cadastrarproduto/{codigo}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualiza um produto pelo código")
    public void registerProductByCode(@RequestBody String xml, @PathVariable String codigo) {
        produtoService.postProductXmlByCode(xml, codigo);
    }

}