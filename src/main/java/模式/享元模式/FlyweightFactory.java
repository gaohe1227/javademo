package 模式.享元模式;

import java.util.HashMap;
import java.util.Map;

/**
 * 核心类
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class FlyweightFactory {

	/**
	 * 
	 */
	public FlyweightFactory() {
		// TODO Auto-generated constructor stub
	}
	private Map<Integer, Flyweight> labels = new HashMap<Integer, Flyweight>();

    public Flyweight factory(String intrinsicState) {

        int hashCode = intrinsicState.hashCode();

        Flyweight fly = labels.get(hashCode);

        if (fly == null) {
            fly = new ConcreteFlyweight(intrinsicState);
            labels.put(hashCode, fly);
        }

        return fly;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FlyweightFactory factory = new FlyweightFactory();

        Flyweight fly1 = factory.factory("Hello");
        fly1.operation("ExtrinsicState-1");

        Flyweight fly2 = factory.factory("DesignPattern");
        fly2.operation("ExtrinsicState-2");

        Flyweight fly3 = factory.factory("Flyweight");
        fly3.operation("ExtrinsicState-3");

        Flyweight fly4 = factory.factory("Hello");
        fly4.operation("ExtrinsicState-4");

        System.out.println("fly1 == fly2 ? " + (fly1 == fly2));
        System.out.println("fly1 == fly3 ? " + (fly1 == fly3));
        System.out.println("fly1 == fly4 ? " + (fly1 == fly4));

 
	}

}
