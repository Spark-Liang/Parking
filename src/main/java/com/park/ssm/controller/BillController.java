package com.park.ssm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.ssm.entity.Bill;
import com.park.ssm.service.AccountStateLogService;
import com.park.ssm.service.BillService;

/**与账单支付有关的操作
 * 
 * @author ASNPHXJ
 *
 */
@Controller
@RequestMapping("bill")
public class BillController {
	@Autowired
	private AccountStateLogService accountStateLogService; 
	@Autowired
	private BillService billService;
	
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
	


	/**
	 * 给原本账号为正常状态的支付当前账单
	 * @param bill
	 * @return
	 */
	@RequestMapping(value="normalpaybill",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> normalPayBill(Bill bill) {
		Map<String,Object> map=new HashMap<>();
		int result=0;
		result=billService.normalPayBill(bill);
		map.put("msg", result);
		return map;
	}
	
	/**
	 * 用于记录账号所占用的停车位
	 */
	@Autowired
	private HandlePositionBooking handlePositionBooking;
	
	/**
	 * 给账号预先占用停车位
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value="bookposition",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> bookPositionForResume(@RequestParam("accountId")Long accountId){
		Map<String, Object>result=new HashMap<>();
		Long positionId=billService.bookPositionBeforePayBill(accountId);
		handlePositionBooking.bookPosition(accountId, positionId);
		result.put("res", true);
		return result;
	}
	/**
	 * 检查该账号当前时刻是否还占有停车位
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value="checkbookposition",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> checkBookPositionForResume(@RequestParam("accountId")Long accountId){
		Map<String, Object>result=new HashMap<>();
		Long positionId=handlePositionBooking.getBookPosition(accountId);
		if(positionId != null) {
			result.put("res", true);
			handlePositionBooking.resumeBooking(accountId, positionId);
		}else {
			result.put("res", false);
		}
		return result;
	}
	
	/**该账号取消支付，则取消对应占用的停车位
	 * 
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value="unbookposition",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> unBookPosition(@RequestParam("accountId")Long accountId){
		Map<String, Object>result=new HashMap<>();
		handlePositionBooking.cleanBookPositionRecord(accountId);
		result.put("res", true);
		return result;
	}
	
	/**该账号支付成功，完成数据库中相应的记录操作作
	 * 
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value="finishresume",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> finishResume(@RequestParam("accountId")Long accountId){
		Map<String, Object>result=new HashMap<>();
		handlePositionBooking.cleanBookPositionRecord(accountId);
		result.put("res", true);
		return result;
	}
	
}


/**
 * 用于记录停卡用户支付前对停车位的预先占位
 * @author ASNPHXJ
 *
 */
@Component
class HandlePositionBooking{
	private static ConcurrentHashMap<Long, Long>positionBookingMap=new ConcurrentHashMap<>();
	private static ConcurrentHashMap<Long, Timer>timerMap=new ConcurrentHashMap<>();
	
	@Autowired
	private static BillService billService;
	
	@SuppressWarnings("deprecation")
	private static long DelayTime=new Date(0, 0, 0, 0, 15, 0).getTime();
	private class CleanBookingTask extends TimerTask{
		private Long accountId;
		
		public CleanBookingTask(Long accountId) {
			super();
			this.accountId = accountId;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			HandlePositionBooking.this.cleanBookPositionRecord(accountId);
		}
	}
	/**
	 * 记录对应账号预先占用的停车位id
	 * @param accountId
	 * @param positionId
	 */
	public void bookPosition(Long accountId,Long positionId) {
		positionBookingMap.put(accountId, positionId);
		CleanBookingTask task=new CleanBookingTask(accountId);
		Timer timer=new Timer();
		timer.scheduleAtFixedRate(task, DelayTime, 0);
		timerMap.put(accountId, timer);
	}
	/**
	 * 获取当前账号是否存在占用的停车位
	 * @param accountId
	 * @return
	 */
	public Long getBookPosition(Long accountId) {
		return positionBookingMap.get(accountId);
	}
	
	/**延长当前账号的停车位的占用的时间
	 * 
	 * @param accountId
	 * @param positionId
	 */
	public void resumeBooking(Long accountId,Long positionId) {
		Timer timer=timerMap.get(accountId);
		if(timer != null) {
			timer.cancel();
			timerMap.remove(accountId);
		}
		bookPosition(accountId, positionId);
	}
	
	/**
	 * 清除对应账号的停车位占用记录
	 * @param accountId
	 */
	public void cleanBookPositionRecord(Long accountId) {
		Long positionId=positionBookingMap.get(accountId);
		if(positionId != null) {
			billService.unBookPosition(positionId);
			Timer timer=timerMap.get(accountId);
			if(timer != null) {
				timerMap.remove(accountId);
				timer.cancel();
			}
			positionBookingMap.remove(accountId);
		}
	}
}
