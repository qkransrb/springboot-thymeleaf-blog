package com.example.blog.service;

import com.example.blog.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> findAllPosts();

    void createPost(PostDto postDto);
}
