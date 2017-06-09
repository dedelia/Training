package training.common.configuration;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Configuration
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		packages("training");

		configureSwagger();
	}

	private void configureSwagger() {

		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);

		BeanConfig config = new BeanConfig();
		config.setTitle("Dede's training API");
		config.setSchemes(new String[] { "http", "https" });
		config.setBasePath("/training");

		config.setResourcePackage("training");
		config.setPrettyPrint(true);
		config.setScan(true);
	}
}
