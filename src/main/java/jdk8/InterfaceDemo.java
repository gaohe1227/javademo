package jdk8;
/**
 * 
 * @author 高鹤
 *
 * 2016年8月3日
 *
 * 作用:接口案例
 */
  interface MyInterface {
	public static void say(){
		System.out.println("静态方法");
	}
    public default void defaultMethod(){
    	System.out.println("默认方法");
    }
}
 public class InterfaceDemo implements MyInterface{
	 public   void defaultMethod(){
	    	System.out.println("默认方法1");
	    }
	 public static void main(String[] args) {
		 MyInterface.say();
		 MyInterface myInterface=new InterfaceDemo();
		 myInterface.defaultMethod();
	}
 }