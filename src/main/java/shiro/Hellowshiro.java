package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月22日
 *
 * 作用:shiro测试
 */
public class Hellowshiro {
	public static void main(String[] args) {
	/*	Subject currentUser=ShiroUtil.login("classpath:shiro_role.ini", "java1234", "123456");//角色管理
		System.out.println(currentUser.hasRole("role1")+"-------------"+currentUser.isAuthenticated());
		currentUser.checkRole("role1"); */
		//testPermitted();
		testJdbcRealm();
	}
	/**
	 * 验证用户
	 */
	public static void testUser(){
		// 读取配置文件，初始化SecurityManager工厂
				Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
				// 获取securityManager实例
				SecurityManager securityManager=factory.getInstance();
				// 把securityManager实例绑定到SecurityUtils
				SecurityUtils.setSecurityManager(securityManager);
				// 得到当前执行的用户
				Subject currentUser=SecurityUtils.getSubject();
				// 创建token令牌，用户名/密码
			    UsernamePasswordToken token=new UsernamePasswordToken("eduadmin", "eduadmin");
			    try{
					// 身份认证
					currentUser.login(token);	
					System.err.println("身份认证成功！");
				}catch(AuthenticationException e){
					e.printStackTrace();
					System.err.println("身份认证失败！");
				}
				// 退出
				currentUser.logout();
	}
     /**
      * 测试权限
      */
	public static void testPermitted() {
		Subject currentUser=ShiroUtil.login("classpath:shiro_permission.ini", "java1234", "123456");
		// Subject currentUser=ShiroUtil.login("classpath:shiro_permission.ini", "jack", "123");
		System.err.println(currentUser.isPermitted("user:select")?"有user:select这个权限":"没有user:select这个权限");
		System.err.println(currentUser.isPermitted("user:update")?"有user:update这个权限":"没有user:update这个权限");
		boolean results[]=currentUser.isPermitted("user:select","user:update","user:delete");
		System.err.println(results[0]?"有user:select这个权限":"没有user:select这个权限");
		System.err.println(results[1]?"有user:update这个权限":"没有user:update这个权限");
		System.err.println(results[2]?"有user:delete这个权限":"没有user:delete这个权限");
		System.err.println(currentUser.isPermittedAll("user:select","user:update")?"有user:select,update这两个权限":"user:select,update这两个权限不全有");
		
		currentUser.logout();
	}

 
	public static void testCheckPermitted() {
		Subject currentUser=ShiroUtil.login("classpath:shiro_permission.ini", "java1234", "123456");
		// Subject currentUser=ShiroUtil.login("classpath:shiro_permission.ini", "jack", "123");
		currentUser.checkPermission("user:select");
		currentUser.checkPermissions("user:select","user:update","user:delete");
		currentUser.logout();
	}
	
	/**
	 * 测试Realm：Realm:域(也可以说是数据源)
	 */
	public static void testJdbcRealm(){
		// 读取配置文件，初始化SecurityManager工厂
				Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:jdbc_realm.ini");
				// 获取securityManager实例
				SecurityManager securityManager=factory.getInstance();
				// 把securityManager实例绑定到SecurityUtils
				SecurityUtils.setSecurityManager(securityManager);
				// 得到当前执行的用户
				Subject currentUser=SecurityUtils.getSubject();
				// 创建token令牌，用户名/密码
				UsernamePasswordToken token=new UsernamePasswordToken("zhang", "123");
				System.err.println("-------------------------------------------------------");
				
				
				try{
					// 身份认证
					currentUser.login(token);	
					System.err.println("身份认证成功！");
				}catch(AuthenticationException e){
					e.printStackTrace();
					System.err.println("身份认证失败！");
				}
				// 退出
				currentUser.logout();
	}
}
