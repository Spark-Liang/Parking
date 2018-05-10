package com.park;

import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionDefinition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:configs/spring/*xml"})
public class BaseTest implements ApplicationContextAware{
	
	private ApplicationContext context;
	//@Test
	public void test() {
		System.out.println("hello");
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		this.context=arg0;
	}
	
	public ApplicationContext getContext() {
		return context;
	}
	
	public Object getBean(Class clazz) {
		return context.getBean(clazz);
	}
	
	public Object getBean(String beanName) {
		return context.getBean(beanName);
	}
	
	@After
	public void after() {
		context=getContext();
		DataSourceTransactionManager manager=context.getBean(DataSourceTransactionManager.class);
		
		
		
	}
	
}
