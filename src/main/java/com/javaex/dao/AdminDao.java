package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

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
	
	public void insertPost(PostVo postVo) {
		System.out.println("[AdminDao.insertPost()]");
		
		int count = sqlSession.insert("admin.insertPost", postVo);
		System.out.println(count+"건 삽입(post)");
	}
	
	public void insertCate(CategoryVo cateVo) {
		System.out.println("[AdminDao.insertCate()]");
		
		int count = sqlSession.insert("admin.insertCate", cateVo);
		System.out.println(count+"건 삽입(category)");
	}
	
	public CategoryVo selectCate(int cateNo) {
		System.out.println("[AdminDao.insertCate()]");
		
		CategoryVo cVo = sqlSession.selectOne("admin.selectCate", cateNo);
		return cVo;
	}
	
	public void deleteCate(int no) {
		System.out.println("[AdminDao.deleteCate()]");
		
		int count = sqlSession.delete("admin.deleteCate", no);
		System.out.println(count +"건 삭제 성공(category)");
	}
	
	public List<PostVo> selectPostList(int cateNo){
		System.out.println("[AdminDao.selectPostList()]");
		
		List<PostVo> postList = sqlSession.selectList("admin.selectPostList", cateNo);
		return postList;
	}
	

}
