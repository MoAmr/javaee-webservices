package com.webservices.rest;

import com.webservices.model.SalutationRequest;
import com.webservices.model.SalutationResponse;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "myresource" path)
 */

//@Singleton
@Path("myresource")
public class MyResource {

    @PathParam("guest")
    String guestName;

    @DefaultValue("Anonymous")
    @QueryParam("guestFromQuery")
    String queryParamGuest;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@Context UriInfo urlInformation) {
        return "Got it! "+urlInformation.getAbsolutePath();
    }

    @Path("/guest/{guest:[a-zA-Z][a-zA-Z_0-9]*}/salute")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salute(SalutationRequest request, @PathParam("guest") String guest) {
        SalutationResponse response = new SalutationResponse();

        response.setSalutationResponse("Hello, "+request.getSalutation()+" "+guest);
        Response responseWrapper = Response.ok(response).build();
        return responseWrapper;
    }

    @Path("/guest/salute")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response saluteWithForm(@FormParam("salutation") String salutation, @FormParam("guest") String guest) {
        SalutationResponse response = new SalutationResponse();

        response.setSalutationResponse("Hello, "+salutation+" "+guest);
        Response responseWrapper = Response.ok(response).build();
        return responseWrapper;
    }
}
