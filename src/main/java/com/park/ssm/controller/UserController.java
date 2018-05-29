package com.park.ssm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.park.ssm.annotation.Permission;
import com.park.ssm.entity.Account;
import com.park.ssm.entity.Bill;
import com.park.ssm.entity.User;
import com.park.ssm.entity.type.AccountState;
import com.park.ssm.service.AccountService;
import com.park.ssm.service.UserService;
import com.park.ssm.util.Encryption;

/**
 * User控制器
 * 实现user的相关功能
 * @author ASNPHX4
 *
 */
@Controller
@RequestMapping("user")
public class UserController {

	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 操作员
	 * 根据客户ID获取客户的所有帐户信息
	 * userId  客户ID 
	 * message 返回的结果信息
	 */
	@RequestMapping(value = "getAllAccount", method = { RequestMethod.POST })
	@ResponseBody
	public String getAllAccount(@RequestParam("userId") long userId) {
		Map result=new HashMap();
		String message="";
		try {
			User user=accountService.findUserByuserId(userId);//判断客户帐户是否存在
			if(user!=null){
				List<Account> list=accountService.findAccountrById(userId);
				result.put("list", list);
			}
			else {
				message="不存在此用户";
			}
			result.put("message", message);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("message"," 服务器出现错误");
		}
		return JSON.toJSONString(result);
	}
	

	/**
	 * 操作员
	 * 根据客户ID获取客户的特定停车场的停车卡信息（即帐号信息 ）
	 * userId  客户ID 
	 * message 返回的结果信息
	 */
	@RequestMapping(value = "getCardMessage", method = { RequestMethod.POST })
	@ResponseBody
	public String getCardMessage(@RequestParam("cardId") long cardId) {
		Map result=new HashMap();
		Account account=new Account();
		String message="";
		try {
			    account=accountService.getCardMessage(cardId);
				result.put("account", account);//前端判断account是否为空即可
				if(account==null) {
					message="不存在此卡，请重新输入！";
				}
			    result.put("message", message);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("message"," 服务器出现错误");
		}
		return JSON.toJSONString(result);
	}
	
	
	
	
	/**
	 * 操作员
	 * 根据客户ID创建特定停车场的停车卡
	 * LotId   停车场ID
	 * userId  客户ID 
	 * cardId  停车卡卡号
	 * message 返回的结果信息
	 * falg    返回的操作是否成功的标志
	 */
	@RequestMapping(value = "addNewCard", method = { RequestMethod.POST })
	@ResponseBody
	@Permission(value= {Permission.Type.ADMIN,Permission.Type.OPERATOR})
	public String addNewCard(@RequestParam("LotId") int LotId ,@RequestParam("userId") long userId,@RequestParam("cardId") long cardId ) {
		Map result=new HashMap();
		Account account=new Account();
		String message="";
		int falg=0;
		int status=0;
		try {
			String times = new SimpleDateFormat("MMdd").format(new Date());
//			String times = "0401";
			String exetime = "";
			String a[]= {"0101","0401","0701","1001"};
			for(int i=0;i<3;i++) {
				exetime=a[i];
				if(times.equals(exetime)){//判断是否为出帐单的日子，出账单日无法办卡
					 message="系统正在生成账单，无法办理开卡业务！";
					 falg=2;
					 break;
				}
			}
			if(falg!=2){
			User user=accountService.findUserByuserId(userId);//判断客户帐户是否存在
		     if(user!=null){  
		    	     Account cardMessage=new Account();
		    	     cardMessage=accountService.getCardMessage(cardId);
		    	     if(cardMessage!=null){
			    		 message="开卡失败，该停车卡已与帐户绑定，请重新选择！";
		    	     }
		    	     else {
		    	    	 int bill=accountService.isNotPayBill(userId);//判断是否存在未支付的账单
		    	    	 if(bill>0) {
		    	    		 message="开卡失败，该账户存在未缴费的账单，请先支付帐单！";
		    	    	 }
		    	    	 else {
		    	    		 account.setUserId(user.getUserId());
			    	    	 Integer lotId=new Integer(LotId);
					    	 account.setParkingLotId(lotId);
					    	 account.setCardId(cardId);
					    	 account.setState(AccountState.getValueByInd(0));
					    	 status=accountService.addNewCard(account);//添加新卡
					    	 if(status>0) {
					    		falg=1;
					    		message="开卡成功";
					    	    result.put("cardId",cardId);
					    	 }
					    	 else {
					    		 message="系统出错，请联系技术部门！";
					    	 }	 
		    	    	 }
		    	     }
		     }
		     else {
		    	    message="请输入已存在的用户帐号！";
				  }
		     }
		     result.put("falg",falg);
			 result.put("message",message);
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
	 * LotId    停车场Id
	 * userId   客户ID 
	 * message  返回的结果信息
	 */
	@RequestMapping(value = "changeCard", method = { RequestMethod.POST })
	@ResponseBody
	public String changeCard(@RequestParam("OldCardId")long OldCardId,@RequestParam("NewCardId")long NewCardId){
		Map result=new HashMap();
		String message="";
		int status=0;
		try {
			Account account=accountService.getCardMessage(OldCardId);
			if(account!=null) {
				Account NewCardAccount=accountService.getCardMessage(OldCardId);
				if(NewCardAccount!=null) {
					 message="创建失败，该停车卡已与帐户绑定，请重新输入！";
				}
				else {
					account.setCardId(NewCardId);
			        status=accountService.modifyAccount(account);//更换新的停车卡
			        if(status>0) {
			        	message="更换成功！";
			         }
			         else {
			        	 message=" 系统出错，请联系技术部门！";
			         }
				}
			}
			else {
				message="该停车卡不存在，请重新输入！";
			}
			result.put("message", message);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("message"," 系统出错，请联系技术部门！");
		}
		return JSON.toJSONString(result);
	}
	
	/**
	 * User登陆控制器
	 * @param userId 用户登陆的id，11位手机号码
	 * @param password 登陆密码6-16位，不包含空格
	 * @param session  登陆成功后写入session
	 * @return Json格式的user对象
	 */
	@RequestMapping(value = "userLogin", method = { RequestMethod.POST })
	@ResponseBody
	public String login(String userId, String password,HttpSession session) {
		User user = new User();
		Encryption en=new Encryption();
		if (null != userId.trim() && !"".equals(userId.trim())) {
			if (null != password && !"".equals(password)) {
				try {
					Long luserId=Long.parseLong(userId);
					String salt=userService.findSaltByUserId(luserId);
					String passwordAndSalt=en.SHA512(password.trim()+salt);
					user = userService.findUser(luserId, passwordAndSalt);
				} catch (Exception e) {
					return JSON.toJSONString(null);
				}
			}
		}
		if (null != user) {
			session.setAttribute("user", user);
			System.out.println(user.toString());
			return JSON.toJSONString(user);
		} else {
			return JSON.toJSONString(null);
		}
	}
	
	/**
	 * 跳转到User登陆界面
	 * @return
	 */
	@RequestMapping(value="toUserLogin")
	@Permission(value= {},haveControl=false)
	public String toUserLogin() {
		return "userLogin";
	}
	
	/**
	 * User退出登陆功能
	 * 消除session
	 * @param session
	 * @return 跳转到User登陆界面
	 */
	@RequestMapping(value="userLogout")
	@Permission(value= {},haveControl=false)
	public String userLogout(HttpSession session) {
		session.removeAttribute("user");
		session.invalidate();
		return "userLogin";
	}
}
