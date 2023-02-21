package service;

import models.Resposta;

public interface CategoriaService {

    public Resposta getCategory();

    public Resposta getCategoryByIdCategoria(String idCategoria);

    public Resposta createCategory(String xml);

}
