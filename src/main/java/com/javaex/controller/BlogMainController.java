package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.AdminService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Controller
public class BlogMainController {
	
	@Autowired
	private AdminService adminService;
		
	@RequestMapping("/{id}")
	public String blogMain( @PathVariable("id") String id, 
							@RequestParam(value="cateNo", defaultValue="0") int cateNo, 
							@RequestParam(value="postNum", defaultValue="0") int postNum, 
							Model model) {
		System.out.println("[BlogMainController.blogMain()]");
		//System.out.println(id);
				
		BlogVo blogVo = adminService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		//System.out.println(blogVo);
		
		List<CategoryVo> cateList = adminService.getCateList(id);
		
		if (cateNo == 0) {
			cateNo = cateList.get(0).getCateNo();
		}
		List<PostVo> postList = adminService.getPostList(cateNo);
		//System.out.println(postList);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("postList", postList);
		map.put("cateList", cateList);
		
		model.addAttribute("map", map);
		//System.out.println(map);
		
		if(postList.size() !=0) {
			PostVo postVo1 = postList.get(postNum);
			String postContent = postVo1.getPostContent();
			postContent.replace("\n","<br>");
			postVo1.setPostContent(postContent);
			model.addAttribute("postVo1",postVo1);	
		}
		
		//System.out.println(postVo1);
		
		return "blog/blog-main";
	}
	
	
	@RequestMapping("/cmtList")
	public String cmtList(@RequestBody int postNo){
		System.out.println("[BlogMainController.cmtList()]");
		
		System.out.println(postNo);
		return "";
		
	}
}
