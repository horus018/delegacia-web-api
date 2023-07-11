package br.com.delegacia.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;

import jakarta.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/delegacia-eletronica-api")
public class JerseyConfig extends ResourceConfig{
	public JerseyConfig() {
		this.register(RequestContextFilter.class);
		this.packages("br.com.delegacia.endpoint");
		this.packages("br.com.delegacia.config");
	}
}
