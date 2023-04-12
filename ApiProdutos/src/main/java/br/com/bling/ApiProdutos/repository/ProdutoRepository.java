package br.com.bling.ApiProdutos.repository;

import br.com.bling.ApiProdutos.controllers.response.ProdutoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoResponse, Long> {

}
