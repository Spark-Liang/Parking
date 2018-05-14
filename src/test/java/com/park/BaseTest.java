package com.park;

import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	

	public Object getBean(Class<?> clazz) {
		return context.getBean(clazz);
	}
	
	public Object getBean(String beanName) {
		return context.getBean(beanName);
	}
	

}
