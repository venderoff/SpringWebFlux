package com.webflux.SpringWebflux;

import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import com.webflux.SpringWebflux.controller.PostHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@ComponentScan
@SpringBootApplication
public class SpringWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxApplication.class, args);
	}


	@Bean
	RouterFunction<ServerResponse> routes(PostHandler postHandler) {
		return
				nest(path("/api/posts"),
		          nest(accept(MediaType.APPLICATION_JSON),
						  route(method(HttpMethod.GET), postHandler::listsPosts)
								  .andRoute(DELETE("/{id}"), postHandler::deletePost)
								  .andRoute(POST("/"),postHandler::savePost)
								  .andRoute(PUT("/{id}"),postHandler::updatePost)));


	}
}
