package uk.petdev.oauthclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OauthclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthclientApplication.class, args);
	}

	@Bean
	RouteLocator gateway(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r
						.path("/") //  oauth/**")
						.filters(GatewayFilterSpec::tokenRelay) //  f -> f.rewritePath("/oauth/(?<segment>.*)", "/${segment}"))
						.uri("http://localhost:8081"))
				.build();
	}
}
