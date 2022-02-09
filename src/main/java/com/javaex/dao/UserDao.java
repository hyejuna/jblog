package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo userVo) {
		System.out.println("[UserDao.insert()]");
		//System.out.println(userVo);
		int count = sqlSession.insert("user.insert", userVo);
		System.out.println(count + "건 삽입 성공(user)");
	}
	
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao.selectUser()]");
		
		UserVo authUser = sqlSession.selectOne("user.selectUser",userVo);
		return authUser;
	
	}

}
