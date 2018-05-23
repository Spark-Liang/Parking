package com.park.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.park.ssm.entity.InnerUser;
import com.park.ssm.service.InnerUserService;
import com.park.ssm.util.Encryption;

@Controller
@RequestMapping("inneruser")
public class InnerUserController {
	@Autowired
	private InnerUserService innerUserService;

	@RequestMapping("page")
	public String loginPage() {
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
		Encryption en=new Encryption();
		//innerUser.setNickname(nickname.trim());
		//innerUser.setPassword(password.trim());
		//innerUser.setTypeflag(intTypeflag);
		//System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+salt);
		if (null != nickname.trim() && !"".equals(nickname.trim())) {
			if (null != password && "" != password) {
				try {
					String salt=innerUserService.findSaltByNickname(nickname);
					String passwordAndSalt=en.SHA512(password.trim()+salt);
					innerUser = innerUserService.findInnerUser(nickname.trim(), passwordAndSalt, intTypeflag);
				} catch (Exception e) {
					return JSON.toJSONString(null);
				}
			}
		}
		if (null != innerUser) {
			session.setAttribute("innerUser", innerUser);
			System.out.println(innerUser.toString());
			return JSON.toJSONString(innerUser);
		} else {
			return JSON.toJSONString(null);
		}
	}

	/**
	 * 注销登陆，清除session,重新跳转到登陆界面
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		session.removeAttribute("innerUser");
		session.invalidate();
		return "login";
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
		Map<String, Object> map = new HashMap<>();
		try {
			innerUser = innerUserService.findInnerUserByNickname(nickname.trim());
			if (null != innerUser) {
				map.put("msg", 1);
				// return JSON.toJSONString(innerUser);
			} else {
				map.put("msg", 0);
			}
		} catch (Exception e) {
			map.put("msg", -1);
		}
		String strCheckNickname = new JSONObject(map).toString();
		return strCheckNickname;
	}

	/**
	 * 添加InnerUser
	 * 
	 * @param innerUser
	 * @return
	 */
	@RequestMapping(value = "addInnerUser", method = { RequestMethod.POST })
	@ResponseBody
	public String addInnerUser(InnerUser innerUser, HttpSession session) {
		InnerUser admin = (InnerUser) session.getAttribute("innerUser");
		int result = 0;
		int id = admin.getTypeflag();
		Map<String, Object> map = new HashMap<>();
		if (id == 0) {// admin的typefalg为0，只有admin才能添加
			try {
				String password=innerUser.getPassword();
				Encryption en=new Encryption();
				String salt=en.createSalt();
				password+=salt;
				password=en.SHA512(password);
				innerUser.setPassword(password);
				innerUser.setSalt(salt);
				result = innerUserService.insertInnerUser(innerUser);
				System.out.println("++++++++++++++++++++++++++"+innerUser.getSalt());
				if (result > 0) {
					map.put("msg", 1);
				} else {
					map.put("msg", 0);
				}
			} catch (Exception e) {
				map.put("msg", -1);
			}
		} else {
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
	@RequestMapping(value = "changeInnerUser", method = { RequestMethod.POST })
	@ResponseBody
	public String changeInnerUser(InnerUser innerUser, HttpSession session) {
		InnerUser admin = (InnerUser) session.getAttribute("innerUser");
		int result = 0;
		int id = admin.getTypeflag();
		//String oldNickname=innerUser.getNickname();
		Map<String, Object> map = new HashMap<>();
		if (id == 0) {// admin的typefalg为0，只有admin才能修改
			try {
				result = innerUserService.changeInnerUserByNickname(innerUser);
				if (result > 0) {
					map.put("msg", 1);
				} else {
					map.put("msg", 0);
				}
			} catch (Exception e) {
				map.put("msg", -1);
			}
		} else {
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
	@RequestMapping(value = "deleteInnerUser", method = RequestMethod.GET)
	@ResponseBody
	public String deleteInnerUser(@RequestParam("nickname") String nickname, HttpSession session) {
		int result = 0;
		InnerUser admin = (InnerUser) session.getAttribute("innerUser");
		int id = admin.getTypeflag();
		Map<String, Object> map = new HashMap<>();
		if (id == 0) {// admin的typefalg为0，只有admin才能删除
			try {
				result = innerUserService.dropInnerUserByNickname(nickname);
				if (result > 0) {
					map.put("msg", 1);
				} else {
					map.put("msg", 0);
				}
			} catch (Exception e) {
				map.put("msg", -1);
			}
		} else {
			map.put("msg", -1);
		}
		String strDeleteInnerUser = new JSONObject(map).toString();
		return strDeleteInnerUser;
	}

	/**
	 * 根据typeflag显示InnerUser，用于登陆成功时显示给admin
	 * 
	 * @param typeflag
	 * @return
	 */
	@RequestMapping(value = "selectInnerUser", method = RequestMethod.GET)
	@ResponseBody
	public String selectInnerUser(HttpSession session) {
		InnerUser admin = (InnerUser) session.getAttribute("innerUser");
		List<InnerUser> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		int id = admin.getTypeflag();
		if (id == 0) {// admin的typefalg为0，只展示inneerUser给admin
			try {
				list = innerUserService.findInnerUserByTypeflag();
				if (!list.isEmpty()) {
					map.put("msg", list);
				} else {
					map.put("msg", null);
				}
			} catch (Exception e) {
				map.put("msg", "error");
			}
		} else {
			map.put("msg", "error");
		}
		String strInnerUser = new JSONObject(map).toString();
		return strInnerUser;
	}

	/**
	 * 前端下拉列表查询InnerUser
	 * 
	 * @param nickname
	 * @param sex
	 * @param phone
	 * @return
	 * 
	 * @RequestMapping(value = "selectInnerUserByFuzzy", method = RequestMethod.GET)
	 * @ResponseBody public String selectInnerUserByFuzzy(@PathVariable("nickname")
	 *               String nickname, @PathVariable("sex") String
	 *               sex, @PathVariable("phone") String phone) { int intSex = -1;//
	 *               由于数据库设置了0表示男，1表示女，故初始值只能设置为0和1以外的整数 int intPhone = 0;
	 *               List<InnerUser> list = new ArrayList<>(); Map<String, Object>
	 *               map = new HashMap<>(); // 非空才转化成整型 if (null != sex &&
	 *               !"".equals(sex)) { try { intSex = Integer.parseInt(sex); }
	 *               catch (Exception e) { map.put("msg", "error"); } } if (null !=
	 *               phone && !"".equals(phone)) { try { intPhone =
	 *               Integer.parseInt(phone); } catch (Exception e) { map.put("msg",
	 *               "error"); } }
	 * 
	 *               try { list = innerUserService.findInnerUserByFuzzy(nickname,
	 *               intSex, intPhone); if (!list.isEmpty()) { map.put("msg", list);
	 *               } else { map.put("msg", null); } } catch (Exception e) {
	 *               map.put("msg", "error"); } String strFuzzySearch = new
	 *               JSONObject(map).toString(); return strFuzzySearch; }
	 */
}