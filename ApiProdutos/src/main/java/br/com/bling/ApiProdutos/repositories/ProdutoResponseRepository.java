package br.com.bling.ApiProdutos.repositories;

import br.com.bling.ApiProdutos.controllers.response.ProdutoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoResponseRepository extends JpaRepository<ProdutoResponse, Long> {

    Optional<ProdutoResponse> findById(Long id);

}
