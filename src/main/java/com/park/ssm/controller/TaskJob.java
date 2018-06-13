package com.park.ssm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.taglibs.standard.lang.jstl.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.park.ssm.dao.AccountDao;
import com.park.ssm.dao.BillDao;
import com.park.ssm.entity.Bill;
 
public class TaskJob{ 
	
	@Autowired
	private BillDao billDao;
	
	@Autowired
    private AccountDao accountDao;	
	/*
	 * 每季度最后一天凌晨0点整生成账单
	 */
//  @Scheduled(cron="0/30 * * * * ?")//每隔三十秒触发一次
//  @Scheduled(cron="0 15 10 L * ?")
    @Transactional
    public void creatBill() {
//    	System.out.println("bbbbbbbbbb");
//    	Map<String, Object> paramMap = new HashMap<>();
//    	Date date=new Date();
//    	paramMap.put("date", date);
////    	billDao.generateBill(paramMap);
//    	int flag = (int) paramMap.get("flag");
//    	StringBuilder errorMessage = new StringBuilder();
//		if (flag == 0) {
//			System.out.print("BBBBBBB");
//		}
//		if(flag==1) {
//			System.out.println("bbbbbbbbbb");
//			errorMessage.append("执行存储过程出现错误！！");
//			throw new RuntimeException(errorMessage.toString());	
//		}
    }  
    
    /*
     * 当到每个季度的下一个月的第一天凌晨0点整 ，自动停止未支付帐单的停车卡与回收相应车位
     */
//  @Scheduled(cron="0/5 * * * * ?")//每隔三十秒触发一次
//  @Scheduled(cron="0 0 0 L 1,4,7,10 ? *")
    @Transactional
    public void  autoStopCard() {
//    	System.out.println("aaaaaa");
//    	Map<String, Object> paramMap = new HashMap<>();
//    	Date date=new Date();
//    	paramMap.put("date", date);
////    	accountDao.updateAllAccountState(paramMap);
//    	int flag = (int) paramMap.get("flag");
//    	StringBuilder errorMessage = new StringBuilder();
//		if (flag == 0) {
//			System.out.println("AAAAAA");
//		}
//		if(flag==1) {
//			System.out.println("aaaaaa");
//			errorMessage.append("执行存储过程出现错误！！");
//			throw new RuntimeException(errorMessage.toString());	
//		}
    }
} 