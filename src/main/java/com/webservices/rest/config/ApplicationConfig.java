package com.webservices.rest.config;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationPath("webapi")
public class ApplicationConfig extends ResourceConfig {

    private final Logger LOGGER = Logger.getLogger("MyResource");

    public ApplicationConfig() {
        packages("com.webservices.rest");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(new LoggingFeature(LOGGER, Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 5000));
        property(ServerProperties.MONITORING_ENABLED, true);
        property(ServerProperties.TRACING, "ALL");
    }
}
