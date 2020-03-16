package com.travellerBlog.Traveller.Blog.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.travellerBlog.Traveller.Blog.model.Post;
import com.travellerBlog.Traveller.Blog.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {

	private PostService postService;

	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "D:/traveller/uploads/";

	// Construction injection 
	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}


	@GetMapping("/blogs")
	public String blogsPage(Model model) {
		List<Post> posts = postService.findAll();
		if (posts == null)
			return "redirect:/";
		model.addAttribute("posts", posts);
		return "post/Blogs";
	}

	@GetMapping("/newPost")
	public String newEntry(Model model) {
		model.addAttribute("newPost", new Post());
		return "post/create-post";
	}

	@PostMapping("/submit")
	public String submitData(@ModelAttribute Post thePost, ModelMap model, HttpSession session,
			HttpServletRequest request) {

		ArrayList<String> fileNames = null;
		if (thePost.getImageFile().length > 0) {
			fileNames = new ArrayList<String>();
			String fileName = null;
			String filePath = null;
			for (MultipartFile file : thePost.getImageFile()) {
				if (file.isEmpty())
					model.put("message", "Please select a file to upload");

				try {
					fileName = file.getOriginalFilename();
					filePath = UPLOADED_FOLDER + file.getOriginalFilename();

					file.transferTo(new File(filePath));
					fileNames.add(file.getOriginalFilename());
					thePost.setImagePath(fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			model.put("name", thePost.getName());
			model.put("description", thePost.getDescription());
			model.put("date", thePost.getDate());
		}

		System.out.println(thePost.toString());
		postService.create(thePost);

		return "redirect:/post/blogs";
	}

	@GetMapping("/editPost/{postId}")
	public String editPost(@PathVariable("postId") long postId, Model model) {
		Post post = postService.findById(postId);
		model.addAttribute("newPost", post);
		return "post/create-post";
	}

	@GetMapping("/deletePost/{postId}")
	public String deletePost(@PathVariable("postId") long postId) {
		postService.deleteById(postId);
		return "redirect:/post/blogs";
	}

	// This will get image path and send it to image tag in Blogs.html
	@RequestMapping(value = "/image/{imagePath}")
	@ResponseBody
	public byte[] getImage(@PathVariable("imagePath") String filePath) throws IOException {
		File file = new File(UPLOADED_FOLDER + filePath);
		return Files.readAllBytes(file.toPath());
	}
}
