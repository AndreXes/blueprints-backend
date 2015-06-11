package org.andrexes.blueprints.backend.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.wordnik.swagger.jaxrs.config.BeanConfig;

@Configuration
@Import(value = {})
@ComponentScan({ "org.andrexes.blueprints.backend.webapp.service" })
@PropertySource({ "classpath:swagger.properties" })
public class SpringConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public BeanConfig swaggerConfiguration() {
        final BeanConfig config = new BeanConfig();

        config.setVersion(env.getProperty("swagger.api.version"));
        config.setBasePath(env.getProperty("swagger.basepath"));
        config.setResourcePackage("org.andrexes.blueprints.backend.webapp.rest");
        config.setScan(true);

        return config;
    }

}
