package br.com.bling.ApiCategorias.service;

import br.com.bling.ApiCategorias.controllers.request.JsonRequest;
import br.com.bling.ApiCategorias.controllers.response.JsonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface CategoriaService {

    public JsonResponse getAllCategory();

    public JsonResponse getCategoryByIdCategoria(@PathVariable("idCategoria") String idCategoria);

    public JsonRequest createCategory(@RequestBody String xmlCategoria);

    public JsonRequest updateCategory(@RequestBody String xmlCategoria, @PathVariable("idCategoria") String idCategoria) throws ParserConfigurationException, IOException, SAXException;
}