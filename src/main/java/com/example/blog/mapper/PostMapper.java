package com.example.blog.mapper;

import com.example.blog.dto.PostDto;
import com.example.blog.entity.Post;

public class PostMapper {

    // map Post entity to PostDto
    public static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    // map PostDto to Post entity
    public static Post mapToPost(PostDto postDto) {
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .url(postDto.getUrl())
                .content(postDto.getContent())
                .shortDescription(postDto.getShortDescription())
                .createdAt(postDto.getCreatedAt())
                .updatedAt(postDto.getUpdatedAt())
                .build();
    }
}
