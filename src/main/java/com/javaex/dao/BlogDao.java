package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(BlogVo blogVo) {
		System.out.println("[BlogDao.insert()]");
		
		int count = sqlSession.insert("blog.insertBlog", blogVo);
		System.out.println(count+"건 삽입 성공(blog)");
	}
}
