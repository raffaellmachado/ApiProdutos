package br.com.bling.ApiProdutos.service;


import br.com.bling.ApiProdutos.controllers.request.RespostaRequest;
import br.com.bling.ApiProdutos.exceptions.ApiProdutoException;
import br.com.bling.ApiProdutos.controllers.response.RespostaResponse;
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
public class ProdutoServiceImpl implements ProdutoService {

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
    public RespostaResponse getAllProducts() throws ApiProdutoException {
        try {
            String json = restTemplate.getForObject(apiBaseUrl + "/produtos/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            RespostaResponse request =  objectMapper.readValue(json, RespostaResponse.class);

            return request;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiProdutoException("Erro ao chamar API", e);
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (SKU)".
     */
    @Override
    public RespostaResponse getProductByCode(String codigo) throws ApiProdutoException {
        try {
            RespostaResponse request = restTemplate.getForObject(apiBaseUrl + "/produto/" + codigo + "/json/" + apiKey, RespostaResponse.class);

            return request;

        } catch (RestClientException e) {
            throw new ApiProdutoException("Erro ao chamar API", e);
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (SKU) E NOME DO FORNECEDOR".
     */
    @Override
    public RespostaResponse getProductByCodeSupplier(String codigo, String nomeFornecedor) throws ApiProdutoException {
        try {
            RespostaResponse request = restTemplate.getForObject(apiBaseUrl + "/produto/" + codigo + "/" + nomeFornecedor + "/json/" + apiKey, RespostaResponse.class);

            return request;

        } catch (RestClientException e) {
            throw new ApiProdutoException("Não foi possível recuperar o produto do fornecedor. Código: " + codigo + ", Nome do Fornecedor: " + nomeFornecedor, e);
        }
    }

    /**
     * DELETE "APAGAR UM PRODUTO PELO CÓDIGO (SKU)".
     */
    @Override
    public void deleteProductByCode(String codigo) throws ApiProdutoException {
        try {
            restTemplate.delete(apiBaseUrl + "/produto/" + codigo + "/json/" + apiKey);

        } catch (RestClientException e) {
            throw new ApiProdutoException("Erro ao chamar API", e);
        }
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @Override
    public RespostaRequest createProduct(String xml) throws ApiProdutoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url  = apiBaseUrl + "/produto/json/" + apiKey + apiXmlParam + xml;
            String json = restTemplate.postForObject(url, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            RespostaRequest result = objectMapper.readValue(json, RespostaRequest.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiProdutoException("Erro ao chamar API", e);
        }
    }

    /**
     * POST "ATUALIZAR PRODUTO PELO CODIGO" UTILIZANDO XML.
     */
    @Override
    public RespostaRequest updateProduct(String xml, String codigo) throws ApiProdutoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url  = apiBaseUrl + "/produto/" + codigo + "/json/" + apiKey + apiXmlParam + xml;
            String json = restTemplate.postForObject(url, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            RespostaRequest result = objectMapper.readValue(json, RespostaRequest.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoException("Erro ao processar JSON", e);
        } catch (RestClientException e) {
            throw new ApiProdutoException("Erro ao chamar API", e);
        }
    }
}
