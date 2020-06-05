package com.webservices.rest.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("webapi")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        packages("com.webservices.rest");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }
}
