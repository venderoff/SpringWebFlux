package com.webflux.SpringWebflux.controller;

import com.webflux.SpringWebflux.dto.PostDto;
import com.webflux.SpringWebflux.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class PostHandler {

    private final PostServiceImpl postService ;

    public Mono<ServerResponse> listsPosts(ServerRequest serverRequest) {

        Flux<PostDto> allPosts = postService.findAllPosts();

        Mono<ServerResponse> notFound = ServerResponse.notFound().build() ;

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(allPosts,PostDto.class)
                .switchIfEmpty(notFound) ;
    }


    public Mono<ServerResponse> updatePost(ServerRequest serverRequest) {

        String id = serverRequest.pathVariable("id");
        Mono<PostDto> postDtoMono = serverRequest.bodyToMono(PostDto.class);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    return postDtoMono.flatMap(postDto -> ServerResponse
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(postService.update(postDto,id), PostDto.class))
            .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> savePost(ServerRequest serverRequest){

        Mono<PostDto> postDtoMono = serverRequest.bodyToMono(PostDto.class);
        Mono<ServerResponse> notFound   = ServerResponse.notFound().build() ;

        return postDtoMono.flatMap(postDto ->
                ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(postService.save(postDto), PostDto.class)
                        .switchIfEmpty(notFound)) ;

    }

    public Mono<ServerResponse> deletePost(ServerRequest  serverRequest){
        String id = serverRequest.pathVariable("id");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return ServerResponse
                .status(HttpStatus.OK)
                .build(postService.delete(id))
                .switchIfEmpty(notFound);

    }

}
