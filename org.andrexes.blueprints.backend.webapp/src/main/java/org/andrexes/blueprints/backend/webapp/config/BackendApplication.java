package org.andrexes.blueprints.backend.webapp.config;

import org.andrexes.blueprints.backend.webapp.rest.PersonResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.wordnik.swagger.jersey.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jersey.listing.JerseyApiDeclarationProvider;
import com.wordnik.swagger.jersey.listing.JerseyResourceListingProvider;

public class BackendApplication extends ResourceConfig {

    public BackendApplication() {
        // Register Spring Integration
        register(RequestContextFilter.class);

        registerExceptionMappers();

        registerRESTResources();

        configureSwagger();
    }

    /**
     * Registers exception mappers for handling in-application exceptions.
     */
    private void registerExceptionMappers() {
        // TODO - insert exception mappers

    }

    /**
     * Registers REST Resources.
     */
    private void registerRESTResources() {
        register(PersonResource.class);
    }

    /**
     * Configures Swagger API documentation.
     */
    private void configureSwagger() {
        register(ApiListingResourceJSON.class);
        register(JerseyApiDeclarationProvider.class);
        register(JerseyResourceListingProvider.class);
    }

}
