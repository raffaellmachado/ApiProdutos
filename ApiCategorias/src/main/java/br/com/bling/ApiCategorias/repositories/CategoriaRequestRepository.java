package br.com.bling.ApiCategorias.repositories;

import br.com.bling.ApiCategorias.controllers.request.CategoriaRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRequestRepository extends JpaRepository<CategoriaRequest, Long> {

    Optional<CategoriaRequest> findById(Long idCategoria);
    @Query("SELECT c FROM CategoriaRequest c WHERE c.descricao = :descricao")
    List<CategoriaRequest> findByDescricao(@Param("descricao") String descricao);


}


