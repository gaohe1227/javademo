package 模式.享元模式;
 /**
  * 
  * @author 高鹤
  *
  * 2015年12月3日
  * 
  * 作用:享元模式案例
  * 享元模式：使用共享物件以减少内存的使用或者将资讯分给尽可能多的相似物件
  *
  */
public class Demo {
	public static void main(String[] args) {
		Integer i1=13;//自动装箱
		System.out.println(i1+24);//拆箱
		Integer i2=13;
		Integer i3=134;
		Integer i4=134;
		Integer i5=Integer.valueOf(3);
		Integer i6=Integer.valueOf(3);
		Integer i7=Integer.valueOf(378);
		Integer i8=Integer.valueOf(378);
		System.out.println(i1==i2);
		System.out.println(i3==i4);
		System.out.println(i5==i6);
		System.out.println(i7==i8);
		System.out.println(i1==i5);
		System.out.println(i3==i8);
	}

}
