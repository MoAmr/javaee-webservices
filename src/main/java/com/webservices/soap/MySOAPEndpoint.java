package com.webservices.soap;

import com.webservices.model.SalutationRequest;
import com.webservices.model.SalutationResponse;

import javax.jws.WebService;

@WebService
public class MySOAPEndpoint {

    public String getIt() {
        return "Got it!";
    }

    public SalutationResponse salute(SalutationRequest request, String guest) {
        SalutationResponse response = new SalutationResponse();
        response.setSalutationResponse("Hello, " + request.getSalutation() + " " + guest);
        return response;
    }

}
