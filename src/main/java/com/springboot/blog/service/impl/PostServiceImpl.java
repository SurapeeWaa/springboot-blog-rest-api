package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	private PostRepository postRepository;
	

	public PostServiceImpl(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}



	@Override
	public PostDto createPost(PostDto postDto) {
		// TODO Auto-generated method stub
		
		// convert DTO to entity
		Post post = mapToEntity(postDto);
		
		Post newPost = postRepository.save(post);
		
		// convert entity to DTO
		PostDto postResponse = mapToDTO(newPost);
		
		
		return postResponse;
	}



	@Override
	public List<PostDto> getAllPost() {
		// TODO Auto-generated method stub
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
	}
	
	// convert Entity into DTO
	private PostDto mapToDTO(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setDescription(post.getDescription());
		postDto.setContent(post.getContent());
		
		return postDto;
	}
	
	// convert DTO to Entity
	private Post mapToEntity(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		return post;
	}



	@Override
	public PostDto getPostById(long id) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		return mapToDTO(post);
	}



	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		// TODO Auto-generated method stub
		
		//get post by id from database
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post updatedPost = postRepository.save(post);
		
		return mapToDTO(updatedPost);
	}



	@Override
	public String deletePost(long id) {
		// TODO Auto-generated method stub
		
		//get post by id from database
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		postRepository.delete(post);
		return "Post entity deleted successfully.";
	}

}
