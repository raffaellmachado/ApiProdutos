package br.com.bling.ApiProdutos.service;

import br.com.bling.ApiProdutos.models.Categoria2;
import br.com.bling.ApiProdutos.models.Resposta;
import br.com.bling.ApiProdutos.models.Retorno;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Value("${external.api.url}")
    private String apiBaseUrl;

    @Value("${external.api.apikey}")
    private String apiKey;

    @Value("${external.api.xmlparam}")
    private String apiXmlParam;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * GET "BUSCAR A LISTA DE DEPOSITOS CADASTRADOS NO BLING".
     */
    @Override
    public Resposta getCategory() {

        String json = restTemplate.getForObject(apiBaseUrl + "/categorias/json/" + apiKey, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Resposta r =  objectMapper.readValue(json, Resposta.class);
            System.out.println(r);
            return r;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    @Override
    public Resposta getCategoryByIdCategoria(String idCategoria) {
        Resposta result = restTemplate.getForObject(apiBaseUrl + "/categoria/" + idCategoria + "/json/" + apiKey, Resposta.class);
        return result;    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    @Override
    public String createCategory(String xml) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<String> request = new HttpEntity<>(xml, headers);
        String url = apiBaseUrl + "/categoria/json/" + apiKey + apiXmlParam + xml;
        return restTemplate.postForObject(url, request, String.class);
    }
}
