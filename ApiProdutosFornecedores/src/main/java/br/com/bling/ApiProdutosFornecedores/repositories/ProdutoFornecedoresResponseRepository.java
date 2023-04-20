package br.com.bling.ApiProdutosFornecedores.repositories;

import br.com.bling.ApiProdutosFornecedores.controllers.response.ProdutoFornecedoresResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoFornecedoresResponseRepository extends JpaRepository<ProdutoFornecedoresResponse, Long> {
    Optional<ProdutoFornecedoresResponse> findById(Long id);
}

