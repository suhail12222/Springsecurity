package com.practices.demo.service;

import com.practices.demo.dto.PostDto;
import com.practices.demo.entities.PostEntity;
import com.practices.demo.exception.ResourceNotFoundException;
import com.practices.demo.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements  PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    PostServiceImplementation(PostRepository postRepository,ModelMapper modelMapper){
        this.modelMapper=modelMapper;
        this.postRepository=postRepository;

    }
    @Override
    public List<PostDto> getAllPosts() {
       return postRepository.findAll().stream().
                map(postEntity -> modelMapper.map(postEntity,PostDto.class)).
                collect(Collectors.toList());
    }

    @Override
    public PostDto createPosts(PostDto postDto) {

        PostEntity postEntity=modelMapper.map(postDto,PostEntity.class);
        postRepository.save(postEntity);
        return modelMapper.map(postRepository.save(postEntity) ,PostDto.class);

    }

    @Override
    public PostDto getPostById(long id) {
        PostEntity postEntity=postRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("lath maan chai yate emi id sth gasya "));

        return modelMapper.map(postEntity,PostDto.class);

    }
}
