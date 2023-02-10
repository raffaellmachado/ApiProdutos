package br.com.bling.ApiProdutos;

import br.com.bling.ApiProdutos.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiProdutosApplicationTests {

	@Autowired
	private ProdutoService produtoService;

	// Teste para trazer a lista de produtos cadastrados.
	@Test
	public void getProduct() {

		produtoService.getProduct();
	}
	// Teste para trazer um produto pelo numero do código e nome do fornecedor.
	@Test
	public void getProductByCodigoFornecedor() {

		String codigo = "159357";
		String nomeFornecedor = "Rafael";

		produtoService.getProductByCodigoFornecedor(codigo, nomeFornecedor);
	}
	// Teste para trazer um produto pelo numero do código.
	@Test
	public void getProductByCodigo() {

		String codigo = "159357";
		produtoService.getProductByCodigo(codigo);
	}

	//Teste para Excluir um produto pelo código;
	@Test
	public void deleteProductByCodigoTest() {

		String codigo = "12345";
		produtoService.deleteProductByCodigo(codigo);

	}
}
