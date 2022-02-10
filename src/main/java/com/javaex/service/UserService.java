package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao cateDao;
	
	
	public void join(UserVo userVo) {
		System.out.println("[UserService.join()]");
		
		userDao.insert(userVo);
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogVo.setBlogTitle(userVo.getUserName()+"의 블로그 입니다");
		blogVo.setLogoFile("spring-logo.jpg");
		blogDao.insert(blogVo);
		
		CategoryVo cateVo = new CategoryVo();
		cateVo.setId(userVo.getId());
		cateVo.setCateName("미분류");
		cateVo.setDescription("기본으로 만들어지는 카테고리 입니다.");
		cateDao.insert(cateVo);	
		
	}
	
	public UserVo login(UserVo userVo) {
		System.out.println("[UserService.login()]");
		
		UserVo authUser = userDao.selectUser(userVo);
		return authUser;
	}
}
