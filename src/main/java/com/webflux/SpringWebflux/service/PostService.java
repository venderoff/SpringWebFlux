package com.webflux.SpringWebflux.service;

import com.webflux.SpringWebflux.dto.PostDto;
import com.webflux.SpringWebflux.entity.Post;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {

    Mono<PostDto> save(PostDto postDto) ;

    Flux<PostDto> findAllPosts() ;

    Mono<PostDto> update(PostDto postDto , String id) ;

    Mono<Void> delete(String id) ;
}
