package com.travellerBlog.Traveller.Blog.Dao;

import java.util.List;

import com.travellerBlog.Traveller.Blog.model.Post;

/**
 * @author VRAJESH
 *
 */
// Interface for Post Data Access Objects
public interface PostDAO {
	
	public List<Post> findAll();

	public Post findById(long id);

	public void create(Post post);

	public void deleteById(long id);

}
