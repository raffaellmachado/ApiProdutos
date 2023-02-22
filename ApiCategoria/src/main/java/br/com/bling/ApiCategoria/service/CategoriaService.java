package br.com.bling.ApiCategoria.service;

import br.com.bling.ApiCategoria.models.Resposta;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CategoriaService {

    public Resposta getCategory();

    public Resposta getCategoryByIdCategoria(@PathVariable String idCategoria);

    public String createCategory(@RequestBody String xml);

    public String updateCategory(@RequestBody String xml, @PathVariable String idCategoria);
}