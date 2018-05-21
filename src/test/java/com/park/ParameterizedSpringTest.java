package com.park;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SuppressWarnings("deprecation")
@RunWith(Parameterized.class)
/*@ContextConfiguration(classes= {WebAppConfiguration.class},loader=org.springframework.test.context.web.WebDelegatingSmartContextLoader.class,
		locations= {"ParameterTest-config.xml"})*/
@ContextConfiguration(locations= {"classpath*:configs/spring/*xml"})
@TransactionConfiguration(transactionManager="txManager", defaultRollback=true)   
@WebAppConfiguration
public class ParameterizedSpringTest implements ApplicationContextAware {
	private static TestContextManager testContextManager;
	
	@Autowired
	protected WebApplicationContext webContext;
	protected ApplicationContext applicationContext;
	
	protected MockMvc mockMvc;
	
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		testContextManager=new TestContextManager(ParameterizedSpringTest.class);
		
	}
	
	@Before
	public void setUp() throws Exception{
		testContextManager.prepareTestInstance(this);
		mockMvc=MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	public WebApplicationContext getWebContext() {
		return webContext;
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext=applicationContext;
	}


	
}
