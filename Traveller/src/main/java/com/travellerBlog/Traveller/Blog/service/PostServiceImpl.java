package com.travellerBlog.Traveller.Blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travellerBlog.Traveller.Blog.Dao.PostDAO;
import com.travellerBlog.Traveller.Blog.model.Post;

/**
 * @author VRAJESH
 *
 */

@Service
@Primary
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDAO postDAO;

	@Override
	@Transactional
	public List<Post> findAll() {
		return this.postDAO.findAll();
	}

	@Override
	@Transactional
	public Post findById(long id) {
		return this.postDAO.findById(id);
	}

	@Override
	@Transactional
	public void create(Post post) {
		postDAO.create(post);
	}


	@Override
	@Transactional
	public void deleteById(long id) {
		postDAO.deleteById(id);
	}

}
