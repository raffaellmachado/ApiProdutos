package br.com.bling.ApiProdutos.resources;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.bling.ApiProdutos.models.Resposta;
import br.com.bling.ApiProdutos.service.ProdutoService;
import io.swagger.annotations.Api;
import org.springframework.web.client.RestTemplate;

import javax.xml.crypto.Data;


@RestController
@RequestMapping(value = "/api")		//Padrão para os métodos /api/...
@Api(value = "API REST PRODUTOS") 	//Swagger
@CrossOrigin(origins = "*") 		// Liberar os dominios da API
public class ProdutoResource {

    private final ProdutoService produtoService;
    private String codigo;
    private String nomeFornecedor;

    @Autowired
    public ProdutoResource(ProdutoService produtoService) {
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
        produtoService.deleteProductByCodigo(String.valueOf(codigo));
    }

    /**
     * POST DE UM NOVO PRODUTO UTILIZANDO XML.
     */
  /*  @PostMapping(path = "/produto", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Insere um produto")
    public ResponseEntity<Resposta> postXml(@RequestBody String xml)  {
        Resposta resposta = produtoService.postXml(xml);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
*/
    @PostMapping(path = "/cadastrarproduto", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendXML(@RequestBody String xml) {
        RestTemplate restTemplate = new RestTemplate();
        produtoService.postProductXml(xml);
    }

    /*
	@PostMapping("/cadastrarproduto")
	@ApiOperation(value = "Salva um produto")
	public Produto salvaProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}

	@DeleteMapping("/produto")
	@ApiOperation(value = "Deleta um produto")
	public void deletaProduto(@RequestBody Produto produto) {
		produtoRepository.delete(produto);
	}

	@PutMapping("/produto")
	@ApiOperation(value = "Atualiza um produto")
	public Produto atualizaProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
*/
}