package com.park;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@TransactionConfiguration
@ContextConfiguration(locations = { "classpath:configs/spring/applicationContext.xml" })
public class MainTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Override
	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		super.setDataSource(dataSource);
	}

}
