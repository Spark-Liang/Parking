package com.park.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.park.ssm.dao.AccountDao;
import com.park.ssm.dao.BillDao;
import com.park.ssm.entity.Bill;

@Component("taskJob")  
public class TaskJob { 
	
	@Autowired
	private BillDao billDao;
	
	@Autowired
    private AccountDao accountDao;	
	/*
	 * 每季度最后一天凌晨0点整生成账单
	 */
    @Scheduled(cron="0/30 * * * * ?")//每隔三十秒触发一次
  //@Scheduled(cron="0 0 0 L 3,6,9,12 ? *")
    @Transactional
    public void creatBill() {
    	System.out.println("A任务进行中");
    }  
    
    /*
     * 当到每个季度的下一个月的第一天凌晨0点整 ，自动停止未支付帐单的停车卡与回收相应车位
     */
    @Scheduled(cron="0/30 * * * * ?")//每隔三十秒触发一次
  //@Scheduled(cron="0 0 0 L 1,4,7,10 ? *")
    @Transactional
    public void  autoStopCard() {
    	System.out.println("B任务进行中");
    }
}  