package com.example.blog.controller;

import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final PostService postService;

    @GetMapping("/")
    public String viewBlogPosts(Model model) {
        model.addAttribute("postsResponse", postService.findAllPosts());
        return "blog/view_posts";
    }

    @GetMapping("/post/{postUrl}")
    public String showPost(@PathVariable("postUrl") String postUrl, Model model) {
        model.addAttribute("post", postService.findPostByUrl(postUrl));
        return "blog/blog_post";
    }

    @GetMapping("/page/search")
    public String searchPosts(@RequestParam(value = "query") String query, Model model) {
        model.addAttribute("postsResponse", postService.searchPosts(query));
        return "/blog/view_posts";
    }
}
