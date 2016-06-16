package 反射.动态代理;

public class RealSubject implements Subject{

	@Override
	public String say(String word, int age) {
		// TODO Auto-generated method stub
		return "语言:"+word+";年龄:"+age;
	}


}
