package br.com.bling.ApiProdutos.repositories;

import br.com.bling.ApiProdutos.controllers.request.ProdutoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRequestRepository extends JpaRepository<ProdutoRequest, Long> {

}
