package br.com.bling.ApiCategorias.repositories;

import br.com.bling.ApiCategorias.controllers.request.CategoriaRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRequestRepository extends JpaRepository<CategoriaRequest, Long> {
}

