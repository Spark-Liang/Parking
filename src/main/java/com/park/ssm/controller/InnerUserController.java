package com.park.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.park.ssm.entity.InnerUser;
import com.park.ssm.service.InnerUserService;

@Controller("innerUserController")
@RequestMapping("inneruser")
public class InnerUserController {
	@Autowired
	private InnerUserService innerUserService;

	@RequestMapping("page")
	public String loginPage() {
		// System.out.println("here");
		// return new ModelAndView("login.html");
		return "login";
	}

	/**
	 * 登陆控制器
	 * 
	 * @param session
	 *            用于登陆成功后写入会话
	 * @param nickname
	 *            登陆的nickname，nickname唯一
	 * @param password
	 *            密码
	 * @param typeflag
	 *            权限控制
	 * @return
	 */
	@RequestMapping(value = "login", method = { RequestMethod.POST })
	@ResponseBody
	public String login(HttpSession session, String nickname, String password, String typeflag) {
		InnerUser innerUser = new InnerUser();
		int intTypeflag = Integer.parseInt(typeflag.trim());
		innerUser.setNickname(nickname.trim());
		innerUser.setPassword(password.trim());
		innerUser.setTypeflag(intTypeflag);
		if (null != nickname.trim() && "" != nickname.trim()) {
			if (null != password && "" != password) {
				try {
					innerUser = innerUserService.findInnerUser(nickname.trim(), password.trim(), intTypeflag);
				} catch (Exception e) {
					return JSON.toJSONString(null);
				}
			}
		}
		if (null != innerUser) {
			session.setAttribute("innerUser", innerUser);
			return JSON.toJSONString(innerUser);
		} else {
			return JSON.toJSONString(null);
		}
	}
	
	/**
	 * 注销登陆，清除session,重新跳转到登陆界面
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "logout")
	public String logout(HttpSession session){
		session.removeAttribute("innerUser");
		session.invalidate();
		return "redirect:/login";
	}

	/**
	 * nickname查重
	 * 
	 * @param nickname
	 * @return
	 */
	@RequestMapping("checkNickname")
	@ResponseBody
	public String checkNickname(@PathVariable("nickname") String nickname) {
		InnerUser innerUser = new InnerUser();
		try {
			innerUser = innerUserService.findInnerUserByNickname(nickname.trim());
			if (null != innerUser) {
				return JSON.toJSONString(innerUser);
			} else {
				return JSON.toJSONString(null);
			}
		} catch (Exception e) {
			return JSON.toJSONString(null);
		}

	}

	/**
	 * 添加InnerUser
	 * 
	 * @param innerUser
	 * @return
	 */
	@RequestMapping(value = "addInnerUser", method = { RequestMethod.POST })
	@ResponseBody
	public String addInnerUser(InnerUser innerUser) {
		int result = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			result = innerUserService.insertInnerUser(innerUser);
			if (result > 0) {
				map.put("msg", 1);
			}else {
				map.put("msg", 0);
			}
		} catch (Exception e) {
			map.put("msg", -1);
		}
		String strAddInnerUser = new JSONObject(map).toString();
		return strAddInnerUser;
	}

	/**
	 * 修改InnerUser相关信息
	 * 
	 * @param innerUser
	 * @return
	 */
	@RequestMapping(value = "changeInnerUser", method = { RequestMethod.PUT })
	@ResponseBody
	public String changeInnerUser(InnerUser innerUser) {
		int result = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			result = innerUserService.changeInnerUserByNickname(innerUser);
			if (result > 0) {
				map.put("msg", 1);
			}else {
				map.put("msg", 0);
			}
		} catch (Exception e) {
			map.put("msg", -1);
		}
		String strChangeInnerUser = new JSONObject(map).toString();
		return strChangeInnerUser;
	}

	/**
	 * 删除innerUser,根据nickname执行
	 * 
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value="deleteInnerUser",method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteInnerUser(@RequestParam("nickname") String nickname) {
		int result = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			result = innerUserService.dropInnerUserByNickname(nickname);
			if (result > 0) {
				map.put("msg", 1);
			}else {
				map.put("msg", 0);
			}
		} catch (Exception e) {
			map.put("msg", -1);
		}
		String strDeleteInnerUser = new JSONObject(map).toString();
		return strDeleteInnerUser;
	}
	
	/**
	 * 根据typeflag显示InnerUser，用于登陆成功时显示给admin
	 * @param typeflag
	 * @return
	 */
	@RequestMapping(value="selectInnerUser",method=RequestMethod.GET)
	@ResponseBody
	public String selectInnerUserByTypeflag(@PathVariable("typeflag")String typeflag) {
		int intTypeflag=Integer.parseInt(typeflag);
		List<InnerUser> list=new ArrayList<>();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			list=innerUserService.findInnerUserByTypeflag(intTypeflag);
			if(list.size()>0) {
				map.put("msg", list);
			}else {
				map.put("msg", null);
			}
		}catch(Exception e) {
			map.put("msg", "error");
		}
		String strInnerUser=new JSONObject(map).toString();
		return strInnerUser;		
	}
}