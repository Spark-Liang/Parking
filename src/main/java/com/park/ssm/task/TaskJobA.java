package com.park.ssm.task;

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
 
public class TaskJobA{ 
	
	@Autowired
	private BillDao billDao;

	/*
	 * 每季度最后一天凌晨0点整生成账单
	 */
    @Transactional
    public void creatBill() {
    	Map<String, Object> paramMap = new HashMap<>();
    	Date date=new Date();
    	paramMap.put("date", date);
    	billDao.generateBill(paramMap);
    	int flag = (int) paramMap.get("flag");
    	StringBuilder errorMessage = new StringBuilder();
		if (flag == 0) {
			System.out.println("aaaaaaaaa111");
		}
		if(flag==1) {
			System.out.println("aaaaaaaaa2222");
			errorMessage.append("执行存储过程出现错误！！");
			throw new RuntimeException(errorMessage.toString());	
		}
    }  
} 