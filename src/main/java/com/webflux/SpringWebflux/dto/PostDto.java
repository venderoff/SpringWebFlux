package com.webflux.SpringWebflux.dto;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostDto {
    private String id ;
    private String title ;
    private String description ;
    private String body ;
    private LocalDateTime createdOn;
    private LocalDateTime updated_on ;
}
