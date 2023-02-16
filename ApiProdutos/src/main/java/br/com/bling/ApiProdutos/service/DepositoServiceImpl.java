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
public class DepositoServiceImpl implements DepositoService{

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
    public Resposta getAllDeposit() {
        Resposta result = restTemplate.getForObject(apiBaseUrl + "/depositos/json/" + apiKey, Resposta.class);
        return result;
    }

    /**
     * GET "BUSCAR UM DEPOSITO PELO CÒDIGO IDDEPOSITO".
     */
    @Override
    public Resposta getDepositByIdDeposit(String idDeposito) {
        Resposta result = restTemplate.getForObject(apiBaseUrl + "/deposito/" + idDeposito + "/json/" + apiKey, Resposta.class);
        return result;
    }

    /**
     * POST "CADASTRAR UM NOVO DEPOSITO" UTILIZANDO XML.
     */
    @Override
    public String createDeposit(String xml) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<String> request = new HttpEntity<>(xml, headers);
        String url = apiBaseUrl + "/deposito/json/" + apiKey + apiXmlParam + xml;
        return restTemplate.postForObject(url, request, String.class);
    }

}
