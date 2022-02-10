package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Repository
public class AdminDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo selectBlog(String id) {
		System.out.println("[AdminDao.selectBlog()]");
		
		BlogVo blogVo = sqlSession.selectOne("admin.selectBlog", id);
		
		return blogVo;
	}
	
	public void blogUpdate(BlogVo blogVo) {
		System.out.println("[AdminDao.blogUpdate()]");
		
		int count = sqlSession.update("admin.blogUpdate", blogVo);
		System.out.println(count+"건 수정 성공(blog)");
	}
	
	public List<CategoryVo> selectCateList(String id) {
		System.out.println("[AdminDao.selectCateList()]");
		
		List<CategoryVo> cateList = sqlSession.selectList("admin.selectCateList", id);
		return cateList;
	
	}
	

}
