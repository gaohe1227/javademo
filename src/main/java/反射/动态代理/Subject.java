package 反射.动态代理;

public interface Subject {
	default String say(){
		System.out.println("默认方法");
		return null;
		
		
	}
	public String say(String word,int age);

}
