package com.webservices.rest.client;

import com.webservices.model.SalutationRequest;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SyncRESTClient {

    public static void main(String[] args) {
        //syncClientCall();
        asyncRESTClient();
    }

    private static void syncClientCall() {

        /**
         * Call http://localhost:8080/java-ee-webservices/webapi/myresource/guest/Mohammed/salute
         * with an HTTP POST
         * retrieve and log the response
         */

        Logger logger = Logger.getLogger("Sync Client");

        HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.basic("admin", "password");

        Client client = ClientBuilder.newClient().register(authFeature);
        SalutationRequest request = new SalutationRequest();
        request.setSalutation("His Royal Codeness");
        Entity<SalutationRequest> entity = Entity.entity(request, MediaType.APPLICATION_JSON);
        String targetResource = "http://localhost:8080/java-ee-webservices/webapi/myresource/guest/{guest}/salute";
        Response response = client.target(targetResource)
                .register(new LoggingFeature(logger, Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 5000))
                .resolveTemplate("guest", "Mohammed")
                .request(MediaType.APPLICATION_JSON)
                //.get();
                .post(entity);
        Logger.getAnonymousLogger().info("Response code " + response.getStatus());
        Logger.getAnonymousLogger().info("Response from the server: " + response.readEntity(String.class));
    }

    private static void asyncRESTClient() {
        /**
         * Call http://localhost:8080/java-ee-webservices/webapi/myresource/guest/Mohammed/salute?makeItWait=true
         * with an HTTP POST
         * retrieve and log the response
         */

        Client client = ClientBuilder.newClient();
        SalutationRequest request = new SalutationRequest();
        request.setSalutation("His Royal Codeness");
        Entity<SalutationRequest> entity = Entity.entity(request, MediaType.APPLICATION_JSON);
        String targetResource = "http://localhost:8080/java-ee-webservices/webapi/myresource/guest/{guest}/salute";
        Future<Response> futureResponse = client.target(targetResource)
                .resolveTemplate("guest", "Mohammed")
                .queryParam("makeItWait", true)
                .request(MediaType.APPLICATION_JSON)
                .async()
                .post(entity);

        Logger logger = Logger.getLogger("Async REST Client");


        try {
            logger.info("Peter Piper picked a peck of pickled peppers,");
            Thread.sleep(1000);
            logger.info("A peck of pickled peppers Peter Piper picked;");
            Thread.sleep(1000);
            logger.info("If Peter Piper picked a peck of pickled peppers,");
            Thread.sleep(1000);
            logger.info("Where’s the peck of pickled peppers Peter Piper picked?");

        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        Response response = null;
        try {
            response = futureResponse.get(4, TimeUnit.SECONDS);
            futureResponse.isDone();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Logger.getAnonymousLogger().info("Response code " + response.getStatus());
        Logger.getAnonymousLogger().info("Response from the server: " + response.readEntity(String.class));

    }
}
