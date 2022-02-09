package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class BlogMainService {

	@Autowired
	private UserDao userDao;
	
	public UserVo getBlog(String id) {
		System.out.println("[BlogMainService.getBlog()]");
		
		UserVo blogUser = userDao.selectBlogUser(id);
		return blogUser;
	}
}
