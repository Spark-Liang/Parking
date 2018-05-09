package com.park.ssm.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class TestConnection {
	
	@Test
	public void testConnect() {
		ClassPathResource loader=new ClassPathResource("configs/dataSource/jdbc.properties");
		Properties properties=new Properties();
		try {
			properties.load(loader.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		String url=properties.getProperty("jdbc.url");
		System.out.println(url.equals("jdbc:mysql://CANGZDLCOA01:3306/train_db?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull"));
		String user=properties.getProperty("jdbc.username");
		System.out.println(user.equals("train_account_1"));
		String password=properties.getProperty("jdbc.password");
		System.out.println(password.equals("CoastTrain@1234"));
		connect(url, user, password);

	}
	
	
	@Test
	public void testJdbcConnect() {

		String url="jdbc:mysql://CANGZDLCOA01:3306/train_db?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
		System.out.println(url);
		String user="train_account_1";
		String password="CoastTrain@1234";
		connect(url,user,password);
	}
	
	private void connect(String url,String user,String password) {
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql="insert into test values(\'阿呆发生非典\')";
		try {
			Statement statement=connection.createStatement();
			System.out.println(statement.execute(sql));
			ResultSet set=statement.executeQuery("select * from test");
			if(set.first()) {
				while(set.next()) {
					System.out.println(set.getString("test"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
