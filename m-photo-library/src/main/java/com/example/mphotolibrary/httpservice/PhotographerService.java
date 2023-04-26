package com.example.mphotolibrary.httpservice;

import com.example.mphotolibrary.model.Photographer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
public class PhotographerService {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public List<Photographer> readAll() throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request =
                HttpRequest.newBuilder()
                        .GET()
                        .uri(new URI("http://localhost:8080/photographers"))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("Response: {}", response);
        String responseBody = response.body();
        log.info("Body: {}", responseBody);

        List<Photographer> photographers = objectMapper.readValue(responseBody, new TypeReference<List<Photographer>>() {});
        return photographers;
    }

}
