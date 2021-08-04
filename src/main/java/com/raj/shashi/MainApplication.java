package com.raj.shashi;

import com.raj.shashi.config.MyConfig;
import com.raj.shashi.resource.TestResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class MainApplication  extends Application<MyConfig> {

    public static void main(String args[]) throws Exception {
        new MainApplication().run(args);
    }

    @Override
    public void run(MyConfig configuration, Environment environment) throws Exception {

        environment.jersey().register(new TestResource());

    }

    @Override
    public void initialize(Bootstrap<MyConfig> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<MyConfig>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(MyConfig configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

}
