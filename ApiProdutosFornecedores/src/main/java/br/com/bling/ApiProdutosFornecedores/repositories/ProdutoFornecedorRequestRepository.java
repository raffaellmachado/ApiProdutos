package br.com.bling.ApiProdutosFornecedores.repositories;

import br.com.bling.ApiProdutosFornecedores.controllers.request.ProdutoFornecedorRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoFornecedorRequestRepository extends JpaRepository<ProdutoFornecedorRequest, Long> {

}

