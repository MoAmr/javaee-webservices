package com.webservices.rest.filter;

import javax.ws.rs.container.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
@PreMatching
public class CORSFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (isPreFlight(requestContext)) {
            requestContext.abortWith(Response.ok().build());
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        if (!isCors(requestContext)) {
            return;
        }

        if (isPreFlight(requestContext)) {
            responseContext.getHeaders().add("Access-Control-Allow-Credentials", true);
            responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET,PUT,POST,OPTIONS,HEAD");
            responseContext.getHeaders().add("Access-Control-Allow-Headers", "*");
        }
    }

    public boolean isPreFlight(ContainerRequestContext ctxt) {
        return ctxt.getHeaderString("Origin") != null && ctxt.getMethod().equalsIgnoreCase("OPTIONS");
    }

    public boolean isCors(ContainerRequestContext ctxt) {
        return ctxt.getHeaderString("Origin") != null;
    }
}
