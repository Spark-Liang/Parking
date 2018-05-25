package com.park;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.core.io.ClassPathResource;

import com.park.ssm.util.Encryption;

@RunWith(Parameterized.class)
public class PasswordGenerator {
	private static BufferedWriter writer;
	
	@BeforeClass
	public static void setUp() throws IOException {
		ClassPathResource resource=new ClassPathResource("com/park/password.txt");
		File file=resource.getFile();
		writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
	}
	
	
	
	@Parameters
	public static Collection<Object[]> data(){
		return new LinkedList() {
			{
				add(new Object[] {"12345678910","123456"});
				add(new Object[] {"12345678911","123456"});
				add(new Object[] {"12345678912","123456"});
				add(new Object[] {"12345678913","123456"});
				add(new Object[] {"12345678914","123456"});
				add(new Object[] {"12345678915","123456"});
				add(new Object[] {"12345678916","123456"});
				add(new Object[] {"12345678917","123456"});
				add(new Object[] {"12345678918","123456"});
				add(new Object[] {"12345678919","123456"});
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
	
	@AfterClass
	public static void finish() throws IOException {
		String sql="insert into User(userId,password,salt) values\n";
		String values=String.join(",", valuesArr);
		System.out.println(sql+values);
		//writer.write(sql+values);
		//writer.flush();
	}

}
