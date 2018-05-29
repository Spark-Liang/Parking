package com.park;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import com.park.ssm.util.Encryption;

@RunWith(Parameterized.class)
public class PasswordGenerator {

	
	
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	@Parameters
	public static Collection<Object[]> data(){
		return new LinkedList() {
			{
				add(new Object[] {"13745678910","123456"});
				add(new Object[] {"13745678911","123456"});
				add(new Object[] {"13745678912","123456"});
				add(new Object[] {"13745678913","123456"});
				add(new Object[] {"13745678914","123456"});
				add(new Object[] {"13745678915","123456"});
				add(new Object[] {"13745678916","123456"});
				add(new Object[] {"13745678917","123456"});
				add(new Object[] {"13745678918","123456"});
				add(new Object[] {"13745678919","123456"});
				add(new Object[] {"13745678920","123456"});
				add(new Object[] {"13719326102","123456"});
				add(new Object[] {"13775119722","123456"});
			}
		};
	}
	private String salt;
	private String userId;
	private String password;
	private Encryption encryption;
	private static List<String> valuesArr=new LinkedList<>();
	
	
	public  PasswordGenerator(Object userId,Object password) {
		// TODO Auto-generated constructor stub
		encryption=new Encryption();
		salt=encryption.createSalt();
		this.userId=(String) userId;
		this.password=(String) password;
	}
	
	@Test
	public void tmp() {
		String realPassword=encryption.SHA512(password+salt);
		valuesArr.add("(\'"+userId+"\',\'"+realPassword+"\',\'"+salt+"\')\n");

	}

}
