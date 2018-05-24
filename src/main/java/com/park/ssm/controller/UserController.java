package com.park.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.park.ssm.entity.Account;
import com.park.ssm.entity.User;
import com.park.ssm.service.AccountService;

@Controller
@RequestMapping("user")
public class UserController {

	
	@Autowired
	private AccountService accountService;

	/**
	 * 根据客户ID获取客户的所有帐户信息
	 * @userId 用户ID
	 */
	@RequestMapping(value = "getAllAccount", method = { RequestMethod.POST })
	@ResponseBody
	public String getAllAccount(@PathVariable("userId") long userId) {
		Map result=new HashMap();
		String Message="";
		try {
			User user=accountService.findUserByuserId(userId);//判断客户帐户是否存在
			if(user!=null){
				List<Account> list=accountService.findAccountrById(userId);
				result.put("list", list);
			}
			else {
				Message="不存在此用户";
			}
			result.put("message", Message);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("message"," 服务器出现错误");
		}
		return JSON.toJSONString(result);
	}
	

	/**
	 * 根据客户ID创建特定停车场的停车卡
	 * @LotId 停车场Id
	 */
	@RequestMapping(value = "addNewCard", method = { RequestMethod.POST })
	@ResponseBody
	public String addNewCard(HttpSession session,@PathVariable("LotId") Integer LotId ,@PathVariable("userId") long userId) {
		Map result=new HashMap();
		Account account=new Account();
		String Message="";
		boolean falg=true;
		try {
			User user=accountService.findUserByuserId(userId);//判断客户帐户是否存在
		     if(user!=null){   	 
		    	 account.setUserId(user.getUserId());
		    	 account.setParkingLotId(LotId);
		    	 int status=accountService.addNewCard(account);
		    	 if(status>0) {   
		    		Message="创建成功";
		    	 }
		    	 else {
		    		 falg=false;
		    		 Message="创建失败，请重新尝试";
		    	 }	
		     }
		     else {
		    	    falg=false;
					Message="不存在此用户,请重新输入！";
				}
		     result.put("falg",falg);
			 result.put("message",Message);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("message"," 服务器出现错误");
		}
		return JSON.toJSONString(result);
	}
}
