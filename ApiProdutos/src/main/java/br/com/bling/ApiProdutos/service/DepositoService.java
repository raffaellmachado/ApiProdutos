package br.com.bling.ApiProdutos.service;

import br.com.bling.ApiProdutos.models.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepositoService {

    private static final String APIKEY = "?apikey=f335209056ebf0f8542e371edbcffbc6c95fa2925903c63aa0175825c2ac2110ab01fd23";
    private static final String XML_PARAM = "&xml=";
    private static final String BLING_API_URL;
    private static final String BLING_API_URL_CODIGO;
    private static final String BLING_API_URL_POST;

    static {

        BLING_API_URL = "https://bling.com.br/Api/v2/depositos/json/" + APIKEY;
        BLING_API_URL_CODIGO = "https://bling.com.br/Api/v2/deposito/{idDeposito}/json/" + APIKEY;
        BLING_API_URL_POST = "https://bling.com.br/Api/v2/deposito/json/" + APIKEY + XML_PARAM;
    }

    @Autowired
    private RestTemplate restTemplate;
    private String codigo;
    private String idDeposito;
    private String xml;


    /**
     * MÉTODO GET "BUSCA LISTA DE DEPOSITOS".
     */
    public Resposta getDeposito() {
        return restTemplate.getForObject(BLING_API_URL, Resposta.class);
    }

    /**
     * MÉTODO GET "BUSCA DEPOSITO EXISTENTE PELO IDDEPOSITO".
     */
    public Resposta getDepositoIdDeposito(String idDeposito) {
        this.idDeposito = idDeposito;
        return restTemplate.getForObject(BLING_API_URL_CODIGO, Resposta.class, idDeposito);
    }

    /**
     * MÉTODO POST "CADASTRA UM NOVO DEPOSITO UTILIZANDO XML".
     */
    public String postDepositoXml(String xml) {
        this.xml = xml;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<>(xml, headers);
        String url = BLING_API_URL_POST + xml;
        return restTemplate.postForObject(url, request, String.class);
    }


}
