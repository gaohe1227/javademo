package 模式.适配器模式;

/**
 * 
 * 类的适配器模式(采取继承实现)
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月28日
 */
public class ClassAdapter extends Adaptee implements Target {
	@Override
	public void request() {
		// TODO Auto-generated method stub
		super.specificRequest();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 使用普通功能类
		Target concreteTarget = new ConcreteTarget();
		concreteTarget.request();

		// 使用特殊功能类，即适配类
		Target adapter = new ClassAdapter();
		adapter.request();
	}

}

/**
 * 
 * 已存在的、具有特殊功能、但不符合我们既有的标准接口的类
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月28日
 */
class Adaptee {
	public void specificRequest() {
		System.out.println("被适配器具有的特殊功能");
	}
}

/*
 * 目标接口
 */
interface Target {
	public void request();
}

// 具体目标类，只提供普通功能
class ConcreteTarget implements Target {
	public void request() {
		System.out.println("普通类 具有 普通功能...");
	}
}

 