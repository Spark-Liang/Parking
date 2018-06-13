package com.park.ssm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.ssm.service.AccountStateLogService;

@Controller
@RequestMapping("bill")
public class BillController {
	@Autowired
	private AccountStateLogService accountStateLogService; 
	
	/**在用户停卡之前获取新账单的信息
	 * 
	 * @param accountId
	 * @return
	 *  ｛price： 账单的每月单价 ，timeQuantums: 时间段的数组，时间段包含startTime和endTime属性 ｝
	 */	
	@RequestMapping(value="newBillInfo",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> getBillInfoBeforTerminate(@RequestParam("accountId")Long accountId){
		return accountStateLogService.getBillPropBeforeTerminated(accountId);
	}
}
