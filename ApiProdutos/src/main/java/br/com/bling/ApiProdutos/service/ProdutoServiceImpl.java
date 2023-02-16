package br.com.bling.ApiProdutos.service;


import br.com.bling.ApiProdutos.models.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProdutoServiceImpl implements ProdutoService{

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
    public Resposta getAllProducts() {
        Resposta result = restTemplate.getForObject(apiBaseUrl + "/produtos/json/" + apiKey, Resposta.class);
        return result;
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (SKU)".
     */
    @Override
    public Resposta getProductByCode(String codigo) {
        Resposta result = restTemplate.getForObject(apiBaseUrl + "/produto/" + codigo + "/json/" + apiKey, Resposta.class);
        return result;
    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (SKU) E NOME DO FORNECEDOR".
     */
    @Override
    public Resposta getProductByCodeSupplier(String codigo, String nomeFornecedor) {
        Resposta result = restTemplate.getForObject(apiBaseUrl + "/produto/" + codigo + "/" +nomeFornecedor + "/json/" + apiKey, Resposta.class);
        return result;
    }

    /**
     * DELETE "APAGAR UM PRODUTO PELO CÓDIGO (SKU)".
     */
    @Override
    public void deleteProductByCode(String codigo) {
        restTemplate.delete(apiBaseUrl + "/produto/" + codigo + "/json/" + apiKey);
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @Override
    public String createProduct(String xml) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<String> request = new HttpEntity<>(xml, headers);
        String url = apiBaseUrl + "/produto/json/" + apiKey + apiXmlParam + xml;
        return restTemplate.postForObject(url, request, String.class);
    }



/* - ESSE É O OK DO VIDEO.
    @Override
    public ProdutoRequest createProduct(ProdutoRequest produtoRequest) {

        ProdutoRequest request = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<ProdutoRequest> httpEntity = new HttpEntity<>(produtoRequest, headers);
        ResponseEntity<ProdutoRequest> newPostEntity = restTemplate.postForEntity(apiBaseUrl + "/produto/json/" + APIKEY + XML_PARAM, httpEntity, ProdutoRequest.class);
        if(newPostEntity.getStatusCode() == HttpStatus.CREATED) {
            request = newPostEntity.getBody();
        }
        return request;
    }
*/

//    /**
//     * POST DE UM NOVO PRODUTO UTILIZANDO XML
//     */
//    public String postProductXml(String xml) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_XML);
//        HttpEntity<String> request = new HttpEntity<>(xml, headers);
//        String url = BLING_API_URL_POST + xml;
//        return restTemplate.postForObject(url, request, String.class);
//    }
//
//    /**
//     * POST ATUALIZA UM PRODUTO A PARTIR DO SEU CODIGO UTILIZANDO XML
//     */
//    public String postProductXmlByCode(String xml, String codigo) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_XML);
//        HttpEntity<String> request = new HttpEntity<>(xml, headers);
//        String url = BLING_API_URL_POST + xml + codigo;
//        return restTemplate.postForObject(url, request, String.class);
//    }
}