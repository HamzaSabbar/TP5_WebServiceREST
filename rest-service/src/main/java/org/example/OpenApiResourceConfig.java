package org.example;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.server.ResourceConfig;

public class OpenApiResourceConfig extends ResourceConfig {
    public OpenApiResourceConfig() {
        packages("org.example.resource");
        register(OpenApiResource.class);
    }
}