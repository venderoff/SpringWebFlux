package com.webflux.SpringWebflux.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Post {
@Id
    private String id ;
private String title ;
private String description ;
private String body ;
@Field(name = "created_on")
    private LocalDateTime createdOn;

@Field(name = "updated_on")
    private LocalDateTime updated_on ;

}
