package com.webflux.SpringWebflux.service.impl;


import com.webflux.SpringWebflux.dto.PostDto;
import com.webflux.SpringWebflux.entity.Post;
import com.webflux.SpringWebflux.mapper.PostMapper;
import com.webflux.SpringWebflux.repository.PostReactiveRepository;
import com.webflux.SpringWebflux.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {


    private PostReactiveRepository postReactiveRepository ;
    private PostMapper postMapper ;

    @Override
    public Mono<PostDto> save(PostDto postDto) {

        Post post = postMapper.mapToPost(postDto) ;

        post.setCreatedOn(LocalDateTime.now());
        post.setUpdated_on(LocalDateTime.now());


        return postReactiveRepository.save(post)
                .map(p -> {
                    postDto.setId(p.getId());
                    return postDto;
                });
    }

    @Override
    public Flux<PostDto> findAllPosts() {


        return postReactiveRepository.findAll()
                .map(postMapper :: mapToDto)
                .switchIfEmpty(Flux.empty()) ;

    }

//    public Boolean postExistsWithTitle(String title){
//        return postReactiveRepository.existByTitle(title) ;
//    }

    @Override
    public Mono<PostDto> update(PostDto postDto, String id) {
        return postReactiveRepository.findById(id)
                .flatMap(savedPost -> {
                    Post post = postMapper.mapToPost(postDto);
                    post.setId(savedPost.getId());
                    return postReactiveRepository.save(post);
                })
                .map(postMapper::mapToDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        return postReactiveRepository.deleteById(id) ;
    }
}
