package br.com.bling.ApiCategorias.service;

import br.com.bling.ApiCategorias.controllers.request.JsonRequest;
import br.com.bling.ApiCategorias.controllers.response.CategoriaResponse;
import br.com.bling.ApiCategorias.controllers.response.JsonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CategoriaService {

//    public JsonResponse getAllCategory();

    public List<CategoriaResponse> getAllCategory();

    public JsonResponse getCategoryByIdCategoria(@PathVariable("idCategoria") String idCategoria);

    public JsonRequest createCategory(@RequestBody String xmlCategoria);

    public JsonRequest updateCategory(@RequestBody String xmlCategoria, @PathVariable("idCategoria") String idCategoria);
}