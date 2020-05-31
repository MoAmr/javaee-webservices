package com.webservices.rest;

import com.webservices.model.SalutationRequest;
import com.webservices.model.SalutationResponse;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
@Singleton
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @Path("/guest/{guest}/salute")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salute(SalutationRequest request, @PathParam("guest") String guest) {
        SalutationResponse response = new SalutationResponse();

        response.setSalutationResponse("Hello, " + request.getSalutation() + " " + guest);
        Response responseWrapper = Response.ok(response).build();
        return responseWrapper;

    }
}
