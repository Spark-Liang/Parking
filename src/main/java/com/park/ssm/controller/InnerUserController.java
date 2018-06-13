package com.park.ssm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.alibaba.fastjson.JSONObject;
import com.park.ssm.annotation.Permission;
import com.park.ssm.entity.InnerUser;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.ParkingRecord;
import com.park.ssm.service.InnerUserService;
import com.park.ssm.service.ParkingLotService;
import com.park.ssm.util.Encryption;

/**
 * InnerUser控制器 实现员工的相关功能
 * 
 * @author ASNPHX4
 *
 */
@Controller
@RequestMapping("inneruser")
public class InnerUserController {
	@Autowired
	private InnerUserService innerUserService;

	@Autowired
	private ParkingLotService parkingLotService;

	@RequestMapping("page")
	public String loginPage() {
		return "login";
	}

	/**
	 * 判断是否为季度第一天
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private boolean setPriceOrNot() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		int currentDate = c.get(Calendar.DATE);
		// System.out.println("----------currentMonth="+currentMonth+"-------------currentDate="+currentDate);;
		if ((currentMonth == 3 && currentDate == 31) || (currentMonth == 12 && currentDate == 31)) {
			return false;
		} else if ((currentMonth == 6 && currentDate == 30) || (currentMonth == 9 && currentDate == 30)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 得出年份
	 * @param desYear
	 * @return
	 */
	private String splitYear(String desYear) {
		String year = null;
		char chr[] = desYear.toCharArray();
		for (int i = 0; i < chr.length; i++) {
			if (chr[i] == '-') {
				year = desYear.substring(0, i);
				System.out.println("--------------------"+year);
				break;
			}
		}
		return year;
	}
	
	/**
	 * 得出月份
	 * @param desMonth
	 * @return
	 */
	private String splitMonth(String desMonth) {
		String month = null;
		char chr[] = desMonth.toCharArray();
		for (int i = 0; i < chr.length; i++) {
			if (chr[i] == '-') {
				month = desMonth.substring(i + 1, chr.length);
				System.out.println("--------------------"+month);
				break;
			}
		}
		return month;
	}
	
	/**
	 * 判断是否为闰年
	 * @param year
	 * @return
	 */
	private boolean isLeanYear(String year) {
		int intYear = Integer.parseInt(year);
		if (intYear % 4 == 0 && intYear % 100 != 0 || intYear % 400 == 0) {
			return true;
		} else {
			return false;
		}
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
	 * 
	 * @return
	 */
	@RequestMapping(value = "login", method = { RequestMethod.POST })
	@ResponseBody
	public String login(HttpSession session, String nickname, String password) {
		InnerUser innerUser = new InnerUser();
		// int intTypeflag = Integer.parseInt(typeflag.trim());
		Encryption en = new Encryption();
		// innerUser.setNickname(nickname.trim());
		// innerUser.setPassword(password.trim());
		// innerUser.setTypeflag(intTypeflag);
		// System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+salt);
		if (null != nickname.trim() && !"".equals(nickname.trim())) {
			if (null != password && !"".equals(password)) {
				try {
					String salt = innerUserService.findSaltByNickname(nickname.trim());
					String passwordAndSalt = en.SHA512(password.trim() + salt);
					innerUser = innerUserService.findInnerUser(nickname.trim(), passwordAndSalt);
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
	@Permission(value = { Permission.Type.ADMIN })
	public String addInnerUser(InnerUser innerUser, HttpSession session) {
		InnerUser admin = (InnerUser) session.getAttribute("innerUser");
		int result = 0;
		int typeflag = admin.getTypeflag();
		Map<String, Object> map = new HashMap<>();
		if (typeflag == 0) {// admin的typefalg为0，只有admin才能添加
			try {
				String password = innerUser.getPassword();
				Encryption en = new Encryption();
				String salt = en.createSalt();
				innerUser.setSalt(salt);
				password += salt;
				password = en.SHA512(password);
				innerUser.setPassword(password);
				result = innerUserService.insertInnerUser(innerUser);
				if (result > 0) {
					map.put("msg", 1);
					map.put("innerUser", innerUser);
					System.out.println("------------------------------" + innerUser);
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
	@Permission(value = { Permission.Type.ADMIN })
	public String changeInnerUser(InnerUser innerUser, HttpSession session) {
		InnerUser admin = (InnerUser) session.getAttribute("innerUser");
		int result = 0;
		int typeflag = admin.getTypeflag();
		Map<String, Object> map = new HashMap<>();
		if (typeflag == 0) {// admin的typefalg为0，只有admin才能修改
			try {
				String password = innerUser.getPassword();
				Encryption en = new Encryption();
				String salt = en.createSalt();
				innerUser.setSalt(salt);
				password += salt;
				password = en.SHA512(password);
				innerUser.setPassword(password);
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
	@Permission(value = { Permission.Type.ADMIN })
	public String deleteInnerUser(@RequestParam("nickname") String nickname, HttpSession session) {
		int result = 0;
		InnerUser admin = (InnerUser) session.getAttribute("innerUser");
		int typeflag = admin.getTypeflag();
		Map<String, Object> map = new HashMap<>();
		if (typeflag == 0) {// admin的typefalg为0，只有admin才能删除
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
	@Permission(value = { Permission.Type.ADMIN })
	public String selectInnerUser(HttpSession session) {
		InnerUser admin = (InnerUser) session.getAttribute("innerUser");
		List<InnerUser> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		int typeflag = admin.getTypeflag();
		if (typeflag == 0) {// admin的typefalg为0，只展示inneerUser给admin
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
	 * manager修改停车场价格
	 * 
	 * @param parkingLot
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "changeParkingLotPrice", method = RequestMethod.POST)
	@ResponseBody
	@Permission(value = { Permission.Type.MANAGER })
	public String changeParkingLotPrice(ParkingLot parkingLot, HttpSession session) {
		InnerUser manager = (InnerUser) session.getAttribute("innerUser");
		int typeflag = manager.getTypeflag();
		Map<String, Object> map = new HashMap<>();
		if (setPriceOrNot() == false) {
			map.put("msg", "出账日不能修改价格");
		}
		if (typeflag == 1 && setPriceOrNot() == true) {
			try {
				parkingLotService.updateParkingLot(parkingLot);
				map.put("msg", 1);
			} catch (Exception e) {
				map.put("msg", "程序内部错误");
			}
		}
		String strChangeParkingLotPrice = new JSONObject(map).toString();
		return strChangeParkingLotPrice;
	}

	/**
	 * 查看使用情况统计
	 * 
	 * @param lotId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "sumUsage", method = RequestMethod.GET)
	@Permission(value = { Permission.Type.MANAGER })
	public Map<String, Object> sumUsage(@RequestParam("lotId") Integer lotId, @RequestParam("time") String time) {
		List<ParkingRecord> list = new ArrayList<>();
		String time1=time;
		String time2=time;
		String year=splitYear(time1);
		String month=splitMonth(time2);
		String[] str= {"-31","-30","-28","-29"};
		String strStartDate="-01";
		String endTime=null;
		String startTime=time+strStartDate;
		if(month.equals("2")||month.equals("02")) {
			if(isLeanYear(year)==true) {
				endTime=time+str[3];
			}else {
				endTime=time+str[2]; 
			}
		}
		if(month.equals("01")||month.equals("1")) {
			endTime=time+str[0];
		}
		if(month.equals("03")||month.equals("3")) {
			endTime=time+str[0];
		}
		if(month.equals("05")||month.equals("5")) {
			endTime=time+str[0];
		}
		if(month.equals("07")||month.equals("7")) {
			endTime=time+str[0];
		}
		if(month.equals("08")||month.equals("8")) {
			endTime=time+str[0];
		}
		if(month.equals("10")||month.equals("12")) {
			endTime=time+str[0];
		}
		if(month.equals("04")||month.equals("4")) {
			endTime=time+str[1];
		}
		if(month.equals("06")||month.equals("6")) {
			endTime=time+str[1];
		}
		if(month.equals("09")||month.equals("9")) {
			endTime=time+str[1];
		}
		if(month.equals("11")) {
			endTime=time+str[1];
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> result = new HashMap<>();
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = sdf.parse(startTime);
			endDate = sdf.parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("error", "字符串转换失败");
		}
		list = parkingLotService.sumUsage(lotId, startDate, endDate);
		if (!list.isEmpty()) {
			result.put("list", list);
		} else {
			result.put("msg", 0);
		}
		return result;
	}
}