package com.park.ssm.service;

import java.util.ArrayList;
import java.util.List;

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
	   innerUser.setNickname("14785236930");
	   innerUser.setPassword("123678");
	   innerUser.setTypeflag(2);
	   innerUser.setName("Mei");
	   Assert.assertTrue(innerUserService.insertInnerUser(innerUser)>0);
   }
   
   @Test
   @Rollback(false)
   public void testChangeInnerUserByNickname() {
	   InnerUser innerUser=new InnerUser();
	   innerUser.setNickname("10987654321");
	   innerUser.setPassword("9876/-74321");
	   innerUser.setTypeflag(1);
	   innerUser.setName("Messi");
	  // System.out.println("----------------------------------"+innerUser.getAddress());
	   Assert.assertTrue(innerUserService.changeInnerUserByNickname(innerUser)>0);
   }
   
   @Test
   public void testFindInnerUserByNickname() {
	   InnerUser innerUser=innerUserService.findInnerUserByNickname("Meisi");
	   Assert.assertEquals("Meii", innerUser.getNickname());
   }
   
   @Test
   public void testFindInnerUser() {
	   InnerUser innerUser=innerUserService.findInnerUser("Meixi", "123678",2);
	   Assert.assertEquals(2, innerUser.getTypeflag());
   }
   
   @Test
  // @Rollback(false)
   public void testdropInnerUserByNickname() {
	   Assert.assertTrue(innerUserService.dropInnerUserByNickname("10987654321")>0);
   }
   
   @Test
   public void testFindInnerUserByTypeflag() {
	   List<InnerUser> list=new ArrayList<InnerUser>();
	   list=innerUserService.findInnerUserByTypeflag();
	   Assert.assertEquals("14785236930", list.get(0).getNickname());
   }
   
   
}
