package br.com.bling.ApiProdutosFornecedores.repositories;

import br.com.bling.ApiProdutosFornecedores.controllers.response.ProdutoFornecedorResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoFornecedorResponseRepository extends JpaRepository<ProdutoFornecedorResponse, Long> {

    Optional<ProdutoFornecedorResponse> findById(Long id);

}

