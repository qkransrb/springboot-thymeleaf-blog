package com.example.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;

    @NotEmpty(message = "Post title should not be empty")
    private String title;

    private String url;

    @NotEmpty(message = "Post content should not be empty")
    private String content;

    @NotEmpty(message = "Post short description should not be empty")
    private String shortDescription;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
