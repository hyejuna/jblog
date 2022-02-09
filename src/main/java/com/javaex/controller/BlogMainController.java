package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogMainController {
	public String blogMain() {
		System.out.println("[BlogMainController]");
		return "blog/blog-main";
	}
}
