package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.payload.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto postDto);
	
	List<PostDto> getAllPost();
	
	PostDto getPostById(long id);
	
	PostDto updatePost(PostDto postDto, long id);
	
	String deletePost(long id);
}
