package br.com.delegacia.config;

import java.io.IOException;
import java.util.List;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CorsInterceptor implements ContainerResponseFilter {
	private final Integer corsPreflightMaxAgeInSeconds = 30 * 60;

	@Override
	public void filter(ContainerRequestContext req, ContainerResponseContext resp) throws IOException {
		resp.getHeaders().add("Access-Control-Allow-Origin", req.getHeaderString("origin"));
		resp.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		resp.getHeaders().add("Access-Control-Allow-Credentials", "true");
		List<String> allowedHeaders = req.getHeaders().get("Access-Control-Request-Headers");
		if (allowedHeaders != null) {
			for (String allowedHeader : allowedHeaders) {
				resp.getHeaders().add("Access-Control-Allow-Headers", allowedHeader);
			}
		}
		resp.getHeaders().add("Access-Control-Max-Age", this.corsPreflightMaxAgeInSeconds);
	}
}