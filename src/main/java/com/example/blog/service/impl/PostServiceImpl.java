package com.example.blog.service.impl;

import com.example.blog.dto.PostDto;
import com.example.blog.entity.Post;
import com.example.blog.mapper.PostMapper;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> findAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createPost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        postRepository.save(post);
    }

    @Override
    @Transactional(readOnly = true)
    public PostDto findPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return PostMapper.mapToPostDto(post);
    }

    @Override
    @Transactional
    public void updatePost(PostDto postDto) {
        postRepository.save(PostMapper.mapToPost(postDto));
    }

    @Override
    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    @Transactional(readOnly = true)
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        return postRepository.searchPosts(query)
                .stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }
}
