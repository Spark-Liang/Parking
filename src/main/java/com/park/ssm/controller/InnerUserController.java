package com.park.ssm.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.park.ssm.entity.InnerUser;
import com.park.ssm.service.InnerUserService;

@Controller("innerUserController")
@RequestMapping("inneruser")
public class InnerUserController {
	@Autowired
	private InnerUserService innerUserService;
	
	@RequestMapping("page")
	public String loginPage() {
		System.out.println("here");
		//return new ModelAndView("login.html");
		return "login";
	}
	
	/**
	 * 登陆控制器
	 * @param session
	 * @param nickname
	 * @param password
	 * @param typeflag
	 * @return
	 */
	@RequestMapping(value="login",method= {RequestMethod.POST}) 
	@ResponseBody
	public String login(HttpSession session,String nickname,String password,String typeflag) {
		InnerUser innerUser=new InnerUser();
		int intTypeflag=Integer.parseInt(typeflag.trim());
		innerUser.setNickname(nickname.trim());
		innerUser.setPassword(password.trim());
		innerUser.setTypeflag(intTypeflag);
		
		if(null!=nickname.trim()&&""!=nickname.trim()&&1==checkNickname(nickname)) {
			if(null!=password&&""!=password) {
				try {
				innerUser=innerUserService.findInnerUser(nickname.trim(), password.trim(), intTypeflag);
				}catch(Exception e) {
					return null;
				}
			}
		}
		if(null!=innerUser) {
			session.setAttribute("innerUser", innerUser);
			return JSON.toJSONString(innerUser);
		}else {
			return JSON.toJSONString(null);
		}	
	}
	
	/**
	 * 检查nickname控制器
	 * @param nickname
	 * @return
	 */
	@RequestMapping("checkNickname")
	public int checkNickname(String nickname) {
		try {
			if(null!=innerUserService.findInnerUserByNickname(nickname.trim())) {
				return 1;
			}else {
				return 0;
			}
		}catch(Exception e) {
			return -1;
		}
		
	}
	
	public int addInnerUser() {
		return 0;
		
	}
	
}
