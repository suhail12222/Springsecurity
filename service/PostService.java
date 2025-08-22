package com.practices.demo.service;

import com.practices.demo.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPosts();
    PostDto createPosts(PostDto postDto);
    PostDto getPostById(long id);
}
