package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/joinForm")
	public String joinForm() {
		System.out.println("[UserController.joinForm()]");
		return "user/joinForm";
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.join()]");
		//System.out.println(userVo);
		userService.join(userVo);
		return "user/joinSuccess";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		System.out.println("[UserController.loginForm()]");
		
		return"user/loginForm";
	}
	
	@RequestMapping("/login")
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login()]");
		//System.out.println(userVo);
		
		UserVo authUser = userService.login(userVo);
		//System.out.println(authUser);
		
		if (authUser != null) { // 로그인 성공
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		} else { // 로그인 실패
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("[UserController.logout()]");

		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
}
