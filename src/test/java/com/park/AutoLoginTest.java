package com.park;

import org.junit.Before;
import org.springframework.mock.web.MockHttpSession;

import com.park.ssm.entity.InnerUser;

public class AutoLoginTest extends AutoRollBackTest{
	@SuppressWarnings("serial")
	protected InnerUser innerUser=new InnerUser() {
		{
			setNickname("12345678910");
			setPassword("123456");
			setTypeflag(0);
		}
	};
	protected MockHttpSession session=new MockHttpSession();
	
	
	public AutoLoginTest() {
		session.setAttribute("innerUser", innerUser);
	}
	
}
