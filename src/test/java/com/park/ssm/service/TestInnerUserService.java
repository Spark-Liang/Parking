package com.park.ssm.service;

import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.park.MainTest;
import com.park.ssm.entity.InnerUser;



public class TestInnerUserService extends MainTest{
	@Autowired  
   private InnerUserService innerUserService;  
  
   @Test
   //@Rollback(false)
   public void  testInsertInnerUser() {
	   InnerUser innerUser=new InnerUser();
	   innerUser.setNickname("Meixi");
	   innerUser.setPassword("123678");
	   innerUser.setTypeflag(2);
	   innerUser.setName("Mei");
	   innerUser.setSex(1);
	   innerUser.setPhone(121789);
	   innerUser.setAddress("Toronto");
	   Assert.assertTrue(innerUserService.insertInnerUser(innerUser)>0);
   }
   
   @Test
   @Rollback(false)
   public void testChangeInnerUserByNickname() {
	   InnerUser innerUser=new InnerUser();
	   innerUser.setNickname("Mesi");
	   innerUser.setPassword("9876+54321");
	   innerUser.setTypeflag(1);
	   innerUser.getNickname();
	   innerUser.setSex(1);
	   innerUser.setPhone(123456789);
	   innerUser.setAddress("Basaluona");
	   System.out.println("----------------------------------"+innerUser.getAddress());
	   Assert.assertTrue(innerUserService.changeInnerUserByNickname(innerUser)>0);
   }
   
   @Test
   public void testFindInnerUserByNickname() {
	   InnerUser innerUser=innerUserService.findInnerUserByNickname("Meisi");
	   Assert.assertEquals("Meii", innerUser.getNickname());
   }
   
   @Test
   public void testFindInnerUser() {
	   InnerUser innerUser=innerUserService.findInnerUser("Meixi", "123678");
	   Assert.assertEquals(98267654, innerUser.getPhone());
   }
   
   @Test
  // @Rollback(false)
   public void testdropInnerUserByNickname() {
	   Assert.assertTrue(innerUserService.dropInnerUserByNickname("Meixi")>0);
   }
}
