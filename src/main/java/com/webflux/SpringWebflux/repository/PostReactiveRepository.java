package com.webflux.SpringWebflux.repository;

import com.webflux.SpringWebflux.entity.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PostReactiveRepository extends ReactiveCrudRepository<Post, String> {


//    Boolean existByTitle(String title) ;

}
