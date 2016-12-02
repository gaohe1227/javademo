package 模式.外观模式;

/**
 * 外观模式 核心类
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class Facade {

	ServiceA sa;

	ServiceB sb;

	ServiceC sc;

	public Facade() {
		sa = new ServiceAImpl();
		sb = new ServiceBImpl();
		sc = new ServiceCImpl();
	}

	public void methodA() {
		sa.methodA();
		sb.methodB();
	}

	public void methodB() {
		sb.methodB();
		sc.methodC();
	}

	public void methodC() {
		sc.methodC();
		sa.methodA();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServiceA sa = new ServiceAImpl();
		ServiceB sb = new ServiceBImpl();
		sa.methodA();
		sb.methodB();
		System.out.println("=====================");
		Facade f = new Facade();
		f.methodA();
		f.methodB();
		f.methodC();
	}

}
