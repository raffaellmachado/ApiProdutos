package br.com.bling.ApiCategorias.repositories;

import br.com.bling.ApiCategorias.controllers.response.CategoriaResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaResponseRepository extends JpaRepository<CategoriaResponse, Long> {

    Optional<CategoriaResponse> findById(Long id);

    @Query("SELECT c.descricao FROM CategoriaResponse c")
    List<String> findAllDescricao();

}
