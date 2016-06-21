package 反射.动态代理;

public interface Subject {
	default String say(){
		System.out.println("测试");
		return null;
		
		
	}
	public String say(String word,int age);

}
