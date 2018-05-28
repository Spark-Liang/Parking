package com.park;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)  
@Transactional(transactionManager="txManager")
@Rollback(true)
@ContextConfiguration(locations= {"classpath*:configs/spring/*xml"})
@WebAppConfiguration
public class AutoRollBackTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static TestContextManager testContextManager;
	@Autowired
	protected WebApplicationContext webContext;
	@Autowired
	protected ApplicationContext applicationContext;
	
	protected MockMvc mockMvc;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		testContextManager=new TestContextManager(ParameterizedSpringTest.class);
		ConfigurationSource source=new ConfigurationSource(new ClassPathResource("com/park/log4j2Test_Env.xml").getInputStream());
		Configurator.initialize(null, source);
		Configurator.setRootLevel(Level.ALL);
	}
	
	@Before
	public void setUp() throws Exception{
		testContextManager.prepareTestInstance(this);
		mockMvc=MockMvcBuilders.webAppContextSetup(webContext).build();
	}

}
