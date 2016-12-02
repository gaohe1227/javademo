package 模式.装饰器模式;

/**
 * 问题：举一个简单的汽车例子，创造每一种功能的汽车都需要继承车的父类进行实现如下图，那么当我们需要既能路上行驶有能水上行驶的车又得继续继承父类拓展新的类。
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public interface Car {
	default void say(){
		System.out.println("我是汽车");
	}
void move();
}
