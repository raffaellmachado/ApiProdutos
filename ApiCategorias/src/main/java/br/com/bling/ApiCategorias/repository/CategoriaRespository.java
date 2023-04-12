package br.com.bling.ApiCategorias.repository;

import br.com.bling.ApiCategorias.controllers.response.CategoriaResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRespository extends JpaRepository<CategoriaResponse, String> {

}
