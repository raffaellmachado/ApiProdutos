package br.com.bling.ApiCategoria.service;

import br.com.bling.ApiCategoria.controllers.request.CategoriaRequest;
import br.com.bling.ApiCategoria.models.Resposta;
import org.springframework.web.bind.annotation.RequestBody;

public interface CategoriaService {

    public Resposta getCategory();

    public Resposta getCategoryByIdCategoria(String idCategoria);

    public String createCategory(String xml);

    public String updateCategory(String xml, String idCategoriaPai);

}
