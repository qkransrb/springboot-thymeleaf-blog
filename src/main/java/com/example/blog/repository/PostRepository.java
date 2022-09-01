package com.example.blog.repository;

import com.example.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByUrl(String url);

    @Query("select p from Post p where p.title like concat('%', :query, '%') or p.shortDescription like concat('%', :query, '%')")
    List<Post> searchPosts(String query);
}
