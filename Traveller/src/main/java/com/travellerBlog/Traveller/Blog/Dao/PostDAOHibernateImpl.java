package com.travellerBlog.Traveller.Blog.Dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travellerBlog.Traveller.Blog.model.Post;

/**
 * @author VRAJESH
 *
 */
@Repository
public class PostDAOHibernateImpl implements PostDAO {

	//define field for EntityManger
	private EntityManager entityManager;

	//Setup constructor injection
	@Autowired
	public PostDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Post> findAll() {

		//  get the current hibernate session
		Session currentSession  = entityManager.unwrap(Session.class);

		//create query
		Query<Post> theQuery = currentSession.createQuery("from Post ORDER BY date DESC",Post.class);

		// execute query and get the results
		List<Post> posts = theQuery.list();

		//return the results
		return posts;
	}

	@Override
	public Post findById(long id) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// get the post
		Post thePost  = currentSession.get(Post.class,id);

		// return the post
		return thePost;
	}

	@Override
	public void create(Post post) {

		// get the current session
		Session currentSession = entityManager.unwrap(Session.class);

		//save the post
		currentSession.saveOrUpdate(post);
	}

	@Override
	public void deleteById(long id) {
		// get the current session
		Session currentSession = entityManager.unwrap(Session.class);

		// delete the post object with primary key
		Query<Post> theQuery = currentSession.createQuery("delete from Post where id=:postId");
		theQuery.setParameter("postId", id);
		theQuery.executeUpdate();	

	}

}
