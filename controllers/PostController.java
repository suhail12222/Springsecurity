package com.practices.demo.controllers;

import com.practices.demo.dto.PostDto;
import com.practices.demo.service.PostService;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
private final PostService postService1;
public PostController( PostService postService1){
    this.postService1=postService1;
}





@GetMapping
    public List<PostDto> getAllPosts(){
    return postService1.getAllPosts();
}
@PostMapping
    public PostDto createPosts(@RequestBody PostDto postDto){
    return postService1.createPosts(postDto);
}


@GetMapping("/{id}")
    public PostDto getPostById(@PathVariable long id){
    return postService1.getPostById(id);
}


}
