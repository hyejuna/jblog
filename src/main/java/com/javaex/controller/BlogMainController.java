package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.AdminService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogMainController {
	
	@Autowired
	private AdminService adminService;
		
	@RequestMapping("/{id}")
	public String blogMain(@PathVariable("id") String id, Model model) {
		System.out.println("[BlogMainController.blogMain()]");
		//System.out.println(id);
				
		BlogVo blogVo = adminService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/blog-main";
	}
}
