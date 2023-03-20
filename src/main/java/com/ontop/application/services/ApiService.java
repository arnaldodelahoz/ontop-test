package com.ontop.application.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public interface ApiService {

    RestTemplate restTemplate = new RestTemplate();

    default <T> T get(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

    default <T> T getWithHeaders(String url, HttpHeaders headers, Class<T> responseType) {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
        return response.getBody();
    }

    default <T> T post(String url, Object request, Class<T> responseType) {
        return restTemplate.postForObject(url, request, responseType);
    }

    default <T> T postWithHeaders(String url, Object request, HttpHeaders headers, Class<T> responseType) {
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, entity, responseType);
        return response.getBody();
    }

    default <T> T put(String url, Object request, Class<T> responseType) {
        restTemplate.put(url, request);
        return restTemplate.getForObject(url, responseType);
    }

    default <T> T putWithHeaders(String url, Object request, HttpHeaders headers, Class<T> responseType) {
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.PUT, entity, responseType);
        return response.getBody();
    }

    default <T> T delete(String url, Class<T> responseType) {
        restTemplate.delete(url);
        return restTemplate.getForObject(url, responseType);
    }

    default <T> T deleteWithHeaders(String url, HttpHeaders headers, Class<T> responseType) {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, responseType);
        return response.getBody();
    }
}
