package com.example.endereco.service;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {
    private final RestTemplate rest;

    public ViaCepService() {
        // small timeout config
        SimpleClientHttpRequestFactory rf = new SimpleClientHttpRequestFactory();
        rf.setConnectTimeout((int) Duration.ofSeconds(5).toMillis());
        rf.setReadTimeout((int) Duration.ofSeconds(5).toMillis());
        this.rest = new RestTemplate(rf);
    }

    public Map<String, Object> buscarCep(String cep) {
        if (cep == null) return Map.of("erro", true);
        String onlyDigits = cep.replaceAll("[^0-9]", "");
        if (onlyDigits.length() != 8) return Map.of("erro", true, "message", "CEP inválido");
        String url = String.format("https://viacep.com.br/ws/%s/json/", onlyDigits);
        try {
            Map resp = rest.getForObject(URI.create(url), Map.class);
            if (resp == null) return Map.of("erro", true);
            if (resp.containsKey("erro")) return Map.of("erro", true);
            // convert response values to strings
            Map<String, Object> result = new HashMap<>();
            resp.forEach((k,v)-> result.put(String.valueOf(k), v));
            return result;
        } catch (RestClientException ex) {
            return Map.of("erro", true, "message", ex.getMessage());
        }
    }

    public boolean isCepValido(String cep) {
        Map<String, Object> resp = buscarCep(cep);
        if (resp == null) return false;
        if (resp.containsKey("erro")) return false;
        return resp.get("cep") != null;
    }
}
