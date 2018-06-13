package com.park.ssm.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.park.ssm.dao.AccountDao;

public class TaskJobB {

	
	@Autowired
    private AccountDao accountDao;	
    
    /*
     * 当到每个季度的下一个月的第一天凌晨0点整 ，自动停止未支付帐单的停车卡与回收相应车位
     */
    @Transactional
    public void  autoStopCard() {
    	
    	Map<String, Object> paramMap = new HashMap<>();
    	Date date=new Date();
    	paramMap.put("date", date);
    	accountDao.updateAllAccountState(paramMap);
    	int flag = (int) paramMap.get("flag");
    	StringBuilder errorMessage = new StringBuilder();
		if (flag == 0) {
			System.out.println("bbbbbbbbbb111");
		}
		if(flag==1) {
			System.out.println("bbbbbbbbbb2222");
			errorMessage.append("执行存储过程出现错误！！");
			throw new RuntimeException(errorMessage.toString());	
		}
    }
}
