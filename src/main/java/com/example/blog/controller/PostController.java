package com.example.blog.controller;

import com.example.blog.dto.PostDto;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/admin/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postService.findAllPosts());
        return "/admin/posts";
    }

    @GetMapping("/admin/posts/newpost")
    public String newPostForm(Model model) {
        model.addAttribute("post", new PostDto());
        return "/admin/create_post";
    }

    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postDto);
            return "/admin/create_post";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId, Model model) {
        model.addAttribute("post", postService.findPostById(postId));
        return "/admin/edit_post";
    }

    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId, @Valid @ModelAttribute("post") PostDto postDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postDto);
            return "/admin/edit_post";
        }
        postDto.setId(postId);
        postService.updatePost(postDto);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl, Model model) {
        model.addAttribute("post", postService.findPostByUrl(postUrl));
        return "/admin/view_post";
    }

    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value = "query") String query, Model model) {
        model.addAttribute("posts", postService.searchPosts(query));
        return "/admin/posts";
    }

    private static String getUrl(String postTitle) {
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;
    }
}
