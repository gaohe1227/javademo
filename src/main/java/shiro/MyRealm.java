package shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;

/**
 * 
 * @author 高鹤
 *
 * 2016年6月22日
 *
 * 作用:自定义realm
 */
public class MyRealm implements Realm{
    /**
     * 设置realm名称
     */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "MyRealm1";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		// TODO Auto-generated method stub
		return token instanceof UsernamePasswordToken;//设置仅支持UsernamePasswordToken类型的token
	}
   /**
    * 验证数据
    */
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
	  String username=(String)token.getPrincipal();//获取用户名
	  if(!username.equals("java1234")){
		  throw new UnknownAccountException();
	  }
	  String password=	new String((char[])token.getCredentials());//密码
	  if(!password.equals("12345")){
		  throw new IncorrectCredentialsException();
	  }
		return new SimpleAuthenticationInfo(username, password, getName());//身份验证成功,返回一个AuthenticationInfo的实现
	}
	

}
