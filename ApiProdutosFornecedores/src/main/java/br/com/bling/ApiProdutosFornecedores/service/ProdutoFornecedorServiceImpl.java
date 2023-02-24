package br.com.bling.ApiProdutosFornecedores.service;

import br.com.bling.ApiProdutosFornecedores.controllers.request.RespostaRequest;
import br.com.bling.ApiProdutosFornecedores.exceptions.ApiProdutoFornecedorException;
import br.com.bling.ApiProdutosFornecedores.controllers.response.RespostaResponse;
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
     * GET "BUSCAR LISTA DE PRODUTOS FORNECEDORES".
     */
    @Override
    public RespostaResponse getAllProducts() throws ApiProdutoFornecedorException {
        try {
            String request = restTemplate.getForObject(apiBaseUrl + "/produtosfornecedores/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            RespostaResponse response =  objectMapper.readValue(request, RespostaResponse.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoFornecedorException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiProdutoFornecedorException("Erro ao chamar API");
        }
    }

    /**
     * GET "BUSCAR UM PRODUTO FORNECEDOR PELO ID".
     */
    @Override
    public RespostaResponse getProducId(String id) throws ApiProdutoFornecedorException {
        try {
            String request = restTemplate.getForObject(apiBaseUrl + "/produtofornecedor/" + id + "/json/" + apiKey, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            RespostaResponse response =  objectMapper.readValue(request, RespostaResponse.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoFornecedorException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiProdutoFornecedorException("Erro ao chamar API");
        }
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO FORNECEDOR" UTILIZANDO XML.
     */
    @Override
    public RespostaRequest createProduct(String xml) throws ApiProdutoFornecedorException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url  = apiBaseUrl + "/produtofornecedor/json/" + apiKey + apiXmlParam + xml;
            String json = restTemplate.postForObject(url, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            RespostaRequest response = objectMapper.readValue(json, RespostaRequest.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoFornecedorException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiProdutoFornecedorException("Erro ao chamar API");
        }
    }

    /**
     * PUT "ATUALIZAR PRODUTO FORNECEDOR PELO ID" UTILIZANDO XML. --- ERRO 404 Not Found: "{"retorno":{"erros":{"erro":{"cod":12,"msg":"Parametro(s) e(sao) invalido(s)"}}}}"
     */
    @Override
    public RespostaRequest updateProduct(String xml, String id) throws ApiProdutoFornecedorException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(xml, headers);
            String url  = apiBaseUrl + "/produtofornecedor/" + id + "/json/" + apiKey + apiXmlParam + xml;
            String json = restTemplate.postForObject(url, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            RespostaRequest response = objectMapper.readValue(json, RespostaRequest.class);

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiProdutoFornecedorException("Erro ao processar JSON");
        } catch (RestClientException e) {
            throw new ApiProdutoFornecedorException("Erro ao chamar API");
        }
    }
}
