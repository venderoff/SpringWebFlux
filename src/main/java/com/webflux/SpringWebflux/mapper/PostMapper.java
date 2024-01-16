package com.webflux.SpringWebflux.mapper;


import com.webflux.SpringWebflux.dto.PostDto;
import com.webflux.SpringWebflux.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post mapToPost(PostDto postInput) {

        return Post.builder()
                .title(postInput.getTitle())
                .description(postInput.getDescription())
                .body(postInput.getBody())
                .build();
    }

    public PostDto  mapToDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .body(post.getBody())
                .title(post.getTitle())
                .description(post.getDescription())
                .build();
    }
}
