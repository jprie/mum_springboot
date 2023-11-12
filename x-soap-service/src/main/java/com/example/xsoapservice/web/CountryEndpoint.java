package com.example.xsoapservice.web;

import at.or.mum.soap.gs_producing_web_service.GetCountryRequest;
import at.or.mum.soap.gs_producing_web_service.GetCountryResponse;
import com.example.xsoapservice.data.CountryRepository;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

// Registriert die Klasse in WS als Bearbeiter von SOAP requests
@Endpoint
public class CountryEndpoint {

    // TODO: step by step!!
    public static final String NAMESPACE = "http://mum.or.at/soap/gs-producing-web-service";

    private final CountryRepository countryRepository;

    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(
            namespace = NAMESPACE,
            localPart = "getCountryRequest"
    )
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {

        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findByName(request.getName()));

        return response;
    }
}
