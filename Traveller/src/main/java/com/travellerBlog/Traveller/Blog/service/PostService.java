package com.travellerBlog.Traveller.Blog.service;

import java.util.List;
/**
 * @author VRAJESH
 *
 */

import com.travellerBlog.Traveller.Blog.model.Post;

public interface PostService {

	public List<Post> findAll();

	public Post findById(long id);

	public void create(Post post);

	public void deleteById(long id);
}
