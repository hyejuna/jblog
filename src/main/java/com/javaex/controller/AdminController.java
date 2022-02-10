package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AdminService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Controller
@RequestMapping("/{id}/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/basic")
	public String basic(@PathVariable("id") String id, Model model ) {
		System.out.println("[AdminController.basic()]");
		
		BlogVo blogVo = adminService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/blog-admin-basic";
	}
	
	@RequestMapping("/basicModify")
	public String basicModify(@RequestParam("file") MultipartFile file, @RequestParam("blogTitle") String title, @PathVariable("id") String id, Model model) {
		System.out.println("[AdminController.basicUpdate()]");
		//System.out.println(file.getOriginalFilename());
		//System.out.println(title);
		adminService.blogModify(file, title, id);
		
		return "redirect:/{id}/admin/basic";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm(@PathVariable("id") String id, Model model) {
		System.out.println("[AdminController.wirteForm()]");
		
		BlogVo blogVo = adminService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		
		List<CategoryVo> cateList = adminService.getCateList(id);
		model.addAttribute("cateList", cateList);
		
		return "blog/admin/blog-admin-write";
	}

	@RequestMapping("/write")
	public String write(@ModelAttribute PostVo postVo) {
		System.out.println("[AdminController.wirte()]");
		System.out.println(postVo);
		
		return "";
	}
}
