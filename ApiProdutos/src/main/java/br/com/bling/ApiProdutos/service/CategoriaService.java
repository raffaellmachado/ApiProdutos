package br.com.bling.ApiProdutos.service;

import br.com.bling.ApiProdutos.models.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CategoriaService {

    private static final String APIKEY = "?apikey=f335209056ebf0f8542e371edbcffbc6c95fa2925903c63aa0175825c2ac2110ab01fd23";
    private static final String XML_PARAM = "&xml=";
    private static final String BLING_API_URL;
    private static final String BLING_API_URL_CODIGO;
    private static final String BLING_API_URL_POST;

    static {

        BLING_API_URL = "https://bling.com.br/Api/v2/categorias/json/" + APIKEY;
        BLING_API_URL_CODIGO = "https://bling.com.br/Api/v2/categoria/{idCategoria}/json/" + APIKEY;
        BLING_API_URL_POST = "https://bling.com.br/Api/v2/categoria/json/" + APIKEY + XML_PARAM;
    }

    @Autowired
    private RestTemplate restTemplate;
    private String codigo;
    private String idCategoria;
    private String xml;

    /**
     * GET "BUSCA A LISTA DE CATEGORIAS".
     */
    public Resposta getCategoria() {
        return restTemplate.getForObject(BLING_API_URL, Resposta.class);
    }


    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     */
    public Resposta getCategoriaIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
        return restTemplate.getForObject(BLING_API_URL_CODIGO, Resposta.class, idCategoria);
    }

    /**
     * POST "CADASTRA UMA NOVA CATEGORIA UTILIZANDO XML".
     */
    public String postCategoriaXml(String xml) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<>(xml, headers);
        String url = BLING_API_URL_POST + xml;
        return restTemplate.postForObject(url, request, String.class);
    }


}
