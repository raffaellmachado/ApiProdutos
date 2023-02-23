package br.com.bling.ApiProdutosFornecedores.service;

import br.com.bling.ApiProdutosFornecedores.exceptions.ApiProdutoFornecedorException;
import br.com.bling.ApiProdutosFornecedores.models.Resposta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProdutoFornecedorServiceImpl implements ProdutoFornecedorService {

    @Value("${external.api.url}")
    private String apiBaseUrl;

    @Value("${external.api.apikey}")
    private String apiKey;

    @Value("${external.api.xmlparam}")
    private String apiXmlParam;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * GET "BUSCAR A LISTA DE PRODUTOS CADASTRADO NO BLING".
     */
    @Override
    public Resposta getAllProducts() throws ApiProdutoFornecedorException {
        try {
            String json = restTemplate.getForObject(apiBaseUrl + "/produtosfornecedores/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Resposta request =  objectMapper.readValue(json, Resposta.class);

            return request;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoFornecedorException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiProdutoFornecedorException("Erro ao chamar API", e);
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (SKU)".
     */
    @Override
    public Resposta getProductByCode(String id) throws ApiProdutoFornecedorException {
        try {
            Resposta request = restTemplate.getForObject(apiBaseUrl + "/produtofornecedor/" + id + "/json/" + apiKey, Resposta.class);

            return request;

        } catch (RestClientException e) {
            throw new ApiProdutoFornecedorException("Erro ao chamar API", e);
        }
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @Override
    public Resposta createProduct(String xml) throws ApiProdutoFornecedorException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url  = apiBaseUrl + "/produtofornecedor/json/" + apiKey + apiXmlParam + xml;
            String json = restTemplate.postForObject(url, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            Resposta result = objectMapper.readValue(json, Resposta.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoFornecedorException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiProdutoFornecedorException("Erro ao chamar API", e);
        }
    }

    /**
     * POST "ATUALIZAR PRODUTO PELO CODIGO" UTILIZANDO XML.
     */
    @Override
    public Resposta updateProduct(String xml, String id) throws ApiProdutoFornecedorException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url  = apiBaseUrl + "/produtofornecedor/" + id + "/json/" + apiKey + apiXmlParam + xml;
            String json = restTemplate.postForObject(url, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            Resposta result = objectMapper.readValue(json, Resposta.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoFornecedorException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiProdutoFornecedorException("Erro ao chamar API", e);
        }
    }
}
