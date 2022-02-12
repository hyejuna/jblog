package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	/* 블로그 기본정보 */
	//기본정보 화면 불러오기
	@RequestMapping("/basic")
	public String basic(@PathVariable("id") String id, Model model ) {
		System.out.println("[AdminController.basic()]");
		
		BlogVo blogVo = adminService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/blog-admin-basic";
	}
	//기본정보 수정
	@RequestMapping("/basicModify")
	public String basicModify(@RequestParam("file") MultipartFile file, @RequestParam("blogTitle") String title, @PathVariable("id") String id, Model model) {
		System.out.println("[AdminController.basicUpdate()]");
		//System.out.println(file.getOriginalFilename());
		//System.out.println(title);
		adminService.blogModify(file, title, id);
		
		return "redirect:basic";
	}
	
	/* post 글쓰기 */
	//글쓰기 폼 불러오기
	@RequestMapping("/writeForm")
	public String writeForm(@PathVariable("id") String id, Model model) {
		System.out.println("[AdminController.wirteForm()]");
		
		BlogVo blogVo = adminService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		
		List<CategoryVo> cateList = adminService.getCateList(id);
		model.addAttribute("cateList", cateList);
		
		return "blog/admin/blog-admin-write";
	}
	//글쓰기 저장
	@RequestMapping("/write")
	public String write(@ModelAttribute PostVo postVo) {
		System.out.println("[AdminController.wirte()]");
		//System.out.println(postVo);
		
		String content = postVo.getPostContent();
		content.replace("\r\n", "<br>");
		content.replace("\n", "<br>");
		postVo.setPostContent(content);
		
		adminService.write(postVo);
		return "redirect:writeForm";
	}
	
	/* 카테고리 */
	//카테고리 화면 불러오기
	@RequestMapping("/category")
	public String category(@PathVariable("id") String id, Model model) {
		System.out.println("[AdminController.category()]");
		
		BlogVo blogVo = adminService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/admin/blog-admin-cate";
	}
	
	//데이터만 가져오기
	@ResponseBody
	@RequestMapping("/cateList")
	public List<CategoryVo> cateList(@PathVariable("id") String id){
		System.out.println("[AdminController.cateList()]");
		
		List<CategoryVo> cateList = adminService.getCateList(id);
		//System.out.println(cateList);
		return cateList;
	}
	
	//카테고리 추가
	@ResponseBody
	@RequestMapping("/addCate")
	public CategoryVo addCate(@RequestBody CategoryVo cateVo, @PathVariable("id") String id) {
		System.out.println("[AdminController.addCate()]");
		cateVo.setId(id);
		System.out.println(cateVo);
		
		CategoryVo cVo = adminService.addCate(cateVo);
		System.out.println(cVo);
		return cVo;		
	}
	
	@ResponseBody
	@RequestMapping("/removeCate")
	public String removeCate(@RequestParam("no") int no ) {
		System.out.println("[AdminController.removeCate()]");
		//System.out.println(no);
		
		adminService.removeCate(no);
		return "success";
	}


}
