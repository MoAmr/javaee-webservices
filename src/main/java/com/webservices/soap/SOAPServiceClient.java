package com.webservices.soap;

import com.webservices.soap.stubs.MySOAPEndpoint;
import com.webservices.soap.stubs.MySOAPEndpointService;
import com.webservices.soap.stubs.SalutationRequest;
import com.webservices.soap.stubs.SalutationResponse;

import java.util.logging.Logger;

public class SOAPServiceClient {

    private static Logger log = Logger.getLogger(SOAPServiceClient.class.getName());

    public static void main(String[] args) {

        MySOAPEndpointService serviceClient = new MySOAPEndpointService();
        MySOAPEndpoint port = serviceClient.getMySOAPEndpointPort();

        SalutationRequest request = new SalutationRequest();
        request.setSalutation("Your Honor");

        SalutationResponse response = port.salute(request, "Mohammed");

        Logger.getAnonymousLogger().info("The response is: " + response.getSalutationResponse());
    }
}
