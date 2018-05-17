package com.park;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(Parameterized.class)
/*@ContextConfiguration(classes= {WebAppConfiguration.class},loader=org.springframework.test.context.web.WebDelegatingSmartContextLoader.class,
		locations= {"ParameterTest-config.xml"})*/
@ContextConfiguration(locations= {"classpath*:configs/spring/*xml"})
@TransactionConfiguration(transactionManager="txManager", defaultRollback=true)   
@WebAppConfiguration
public class ParameterizedSpringTest {
	
	@Autowired
	private WebApplicationContext webContext;
	
	private TestContextManager testContextManager;
	private MockMvc mockMvc;
	
	
	@Before
	public void setUp() throws Exception {
		testContextManager=new TestContextManager(this.getClass());
		testContextManager.prepareTestInstance(this);
		mockMvc=MockMvcBuilders.webAppContextSetup(webContext).build();
	}


	public WebApplicationContext getWebContext() {
		return webContext;
	}


	public TestContextManager getTestContextManager() {
		return testContextManager;
	}


	public MockMvc getMockMvc() {
		return mockMvc;
	}
	
}
