package com.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

 
import com.redis.User;
import com.redis.dao.IUserDao;
 
/** 
 * 测试
 * @author http://blog.csdn.net/java2000_wl 
 * @version <b>1.0</b> 
 */  
 
/*@ContextConfiguration(locations = { "classpath:spring/spring-redis.xml" })  
@RunWith(SpringJUnit4ClassRunner.class) */
public class RedisTest  {
	 
	private IUserDao userDao;
	 @Before
	 public void testBefore(){
		  ApplicationContext ac =  new ClassPathXmlApplicationContext("classpath*:spring/spring-redis.xml");
	      System.out.println(ac);
		  userDao=(IUserDao) ac.getBean("userDao");
	 }
 
	/**
	 * 新增
	 * <br>------------------------------<br>
	 */
	@Test
	public void testAddUser() {
		try{
		User user = new User();
		user.setId("user"+UUID.randomUUID());		
		user.setName("java2000_wl");
		System.out.println("----------------------------------------------------------------------------"); 
		boolean result = userDao.add(user);
		Assert.assertTrue(result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量新增 普通方式
	 * <br>------------------------------<br>
	 */
	@Test
	public void testAddUsers1() {
		List<User> list = new ArrayList<User>();
		for (int i = 10; i < 50000; i++) {
			User user = new User();
			user.setId("user" + i);
			user.setName("java2000_wl" + i);
			list.add(user);
		}
		long begin = System.currentTimeMillis();
		for (User user : list) {
			userDao.add(user);
		}
		System.out.println(System.currentTimeMillis() -  begin);
	}
	
	/**
	 * 批量新增 pipeline方式
	 * <br>------------------------------<br>
	 */
	@Test
	public void testAddUsers2() {
		List<User> list = new ArrayList<User>();
		for (int i = 10; i < 1500000; i++) {
			User user = new User();
			user.setId("user" + i);
			user.setName("java2000_wl" + i);
			list.add(user);
		}
		long begin = System.currentTimeMillis();
		boolean result = userDao.add(list);
		System.out.println(System.currentTimeMillis() - begin);
		Assert.assertTrue(result);
	}
	
	/**
	 * 修改
	 * <br>------------------------------<br>
	 */
	@Test
	public void testUpdate() {
		User user = new User();
		user.setId("user1");
		user.setName("new_password");
		boolean result = userDao.update(user);
		Assert.assertTrue(result);
	}
	
	/**
	 * 通过key删除单个
	 * <br>------------------------------<br>
	 */
	@Test
	public void testDelete() {
		String key = "user1";
		userDao.delete(key);
	}
	
	/**
	 * 批量删除
	 * <br>------------------------------<br>
	 */
	@Test
	public void testDeletes() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add("user" + i);
		}
		userDao.delete(list);
	}
	
	/**
	 * 获取
	 * <br>------------------------------<br>
	 */
	@Test
	public void testGetUser() {
		String id = "user1";
		User user = userDao.get(id);
		System.out.println(user.getName());
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getName(), "java2000_wl");
	}
 
}