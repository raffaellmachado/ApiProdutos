package br.com.bling.ApiCategoria.service;

import br.com.bling.ApiCategoria.controllers.request.JsonRequest;
import br.com.bling.ApiCategoria.controllers.response.JsonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CategoriaService {

    public JsonResponse getAllCategory();

    public JsonResponse getCategoryByIdCategoria(@PathVariable("idCategoria") String idCategoria);

    public Object createCategory(@RequestBody String xmlCategoria);

    public Object updateCategory(@RequestBody String xmlCategoria, @PathVariable("idCategoria") String idCategoria);
}