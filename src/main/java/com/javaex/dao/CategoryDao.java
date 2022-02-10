package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(CategoryVo cateVo) {
		System.out.println("[CategoryDao.insert()]");
		
		int count = sqlSession.insert("blog.insertCategory", cateVo);
		System.out.println(count +"건 삽입 성공(category)");
		
	}
}
