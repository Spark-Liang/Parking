package com.park;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)  
@TransactionConfiguration(transactionManager="txManager", defaultRollback=true)   
@ContextConfiguration(locations= {"classpath*:configs/spring/*xml"})
public class AutoRollBackTest extends AbstractTransactionalJUnit4SpringContextTests {

	
}
