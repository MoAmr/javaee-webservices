package com.webservices.rest.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Logger;

@Provider
public class CustomLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private final Logger LOGGER = Logger.getLogger("CustomLoggingFilter");

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        LOGGER.info("Inbound request filter");
        BufferedInputStream buff = new BufferedInputStream(requestContext.getEntityStream());
        byte[] istream = new byte[buff.available()];
        buff.read(istream);
        String inboundBody = new String(istream, "utf-8");
        LOGGER.info("Inbound message " + inboundBody);
        requestContext.setEntityStream(new ByteArrayInputStream(istream));
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        LOGGER.info("Outbound message " + responseContext.getEntity());

    }
}
