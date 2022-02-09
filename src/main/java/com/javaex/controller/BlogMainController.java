package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BlogMainService;
import com.javaex.vo.UserVo;

@Controller
public class BlogMainController {
	
	@Autowired
	private BlogMainService blogMainService;
		
	@RequestMapping("/{id}")
	public String blogMain(@PathVariable("id") String id, Model model) {
		System.out.println("[BlogMainController.blogMain()]");
		//System.out.println(id);
				
		UserVo blogUser = blogMainService.getBlog(id);
		model.addAttribute("blogUser", blogUser);
		System.out.println(blogUser);
		return "blog/blog-main";
	}
}
