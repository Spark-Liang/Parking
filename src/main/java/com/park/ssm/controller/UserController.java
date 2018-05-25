package com.park.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.park.ssm.annotation.Permission;
import com.park.ssm.entity.Account;
import com.park.ssm.entity.User;
import com.park.ssm.service.AccountService;

@Controller
@RequestMapping("user")
public class UserController {

	
	@Autowired
	private AccountService accountService;

	/**
	 * 操作员
	 * 根据客户ID获取客户的所有帐户信息
	 * userId  客户ID 
	 * Message 返回的结果信息
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
	
//
//	/**
//	 * 操作员
//	 * 根据客户ID获取客户的特定停车场的停车卡信息（即帐号信息 ）
//	 * userId  客户ID 
//	 * Message 返回的结果信息
//	 */
//	@RequestMapping(value = "getAccount", method = { RequestMethod.POST })
//	@ResponseBody
//	public String getAccount(@PathVariable("userId") long userId,@PathVariable("LotId") Integer LotId ) {
//		Map result=new HashMap();
//		Account account=new Account();
//		String Message="";
//		try {
//			User user=accountService.findUserByuserId(userId);//判断客户帐户是否存在
//			if(user!=null){
//				List<Account> list=accountService.findAccountrByIdAnd(userId);
//				result.put("list", list);
//			}
//			else {
//				Message="不存在此用户";
//			}
//			result.put("message", Message);
//		}catch(Exception e) {
//			e.printStackTrace();
//			result.put("message"," 服务器出现错误");
//		}
//		return JSON.toJSONString(result);
//	}
//	
//	
	
	
	/**
	 * 操作员
	 * 根据客户ID创建特定停车场的停车卡
	 * LotId   停车场ID
	 * userId  客户ID 
	 * cardId  停车卡卡号
	 * Message 返回的结果信息
	 * falg    返回的操作是否成功的标志{0:异常状况，服务器出现错误，1：停车卡创建成功，2:停车卡创建失败，3：不存在此用户，请重新输入}
	 */
	@RequestMapping(value = "addNewCard", method = { RequestMethod.POST })
	@ResponseBody
	@Permission(value= {Permission.Type.ADMIN,Permission.Type.OPERATOR})
	public String addNewCard(@PathVariable("LotId") Integer LotId ,@PathVariable("userId") long userId,@PathVariable("cardId") long cardId ) {
		Map result=new HashMap();
		Account account=new Account();
		String Message="";
		int falg=0;
		int status=0;
		try {
			User user=accountService.findUserByuserId(userId);//判断客户帐户是否存在
		     if(user!=null){   	 
		    	     status=accountService.isNotExistCard(cardId);
		    	     if(status>0){
			    		 falg=2;
			    		 Message="创建失败，该停车卡已存在！";
		    	     }
		    		 account.setUserId(user.getUserId());
			    	 account.setParkingLotId(LotId);
			    	 account.setCardId(cardId);
			    	 status=0;
			    	 status=accountService.addNewCard(account);//添加新卡
			    	 if(status>0) {
			    		falg=1;
			    		Message="创建成功";
			    	    result.put("cardId",cardId);
			    	 }
			    	 else {
			    		 
			    		 falg=2;
			    		 Message="创建失败，请重新尝试!";
			    	 }	
		     }
		     else {
		    	    falg=3;
					Message="不存在此用户,请重新输入！";
				  }
		     result.put("falg",falg);
			 result.put("message",Message);
		}catch(Exception e) {
			e.printStackTrace();
			falg=0;
			result.put("message"," 系统出错，请联系技术部门！");
		}
		return JSON.toJSONString(result);
	}
	
	

	/**
	 * 操作员
	 * 根据客户ID，更换新的停车卡(创建新的卡号，代替旧的卡)
	 * LotId 停车场Id
	 * userId 客户ID 
	 */
	@RequestMapping(value = "changeNewCard", method = { RequestMethod.POST })
	@ResponseBody
	public String changeNewCard(@PathVariable("LotId") Integer LotId ,@PathVariable("userId") long userId){
		Map result=new HashMap();
		String Message="";
		int status=0;
		Account account =new Account();
		try {
			account.setUserId(userId);
			account.setParkingLotId(LotId);
			Random random=new Random();
	    	long r=random.nextInt(99999);
			account.setCardId(r);
	        status=accountService.modifyAccount(account);//更换新的停车卡
	        if(status>0) {
	        	 Message="更换成功！";
	         }
	         else {
	        	  Message="更换失败，请重新尝试！";
	         }
			result.put("message", Message);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("message"," 服务器出现错误");
		}
		return JSON.toJSONString(result);
	}
}
