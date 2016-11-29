package 模式.工厂模式;

/**
 * 
 * 简单工厂模式案例 简单工厂模式（Simple Factory Pattern）属于类的创新型模式，又叫静态工厂方法模式（Static
 * FactoryMethod Pattern）,是通过专门定义一个类来负责创建其他类的实例，被创建的实例通常都具有共同的父类。、
 * （Simple Factory Pattern）属于类的创新型模式，又叫静态工厂方法模式（Static
 *  简单工厂模式 没有抽象类，只有一个工厂类，把需要创建对象的参数传递过来，由工厂类统一创建
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月28日
 */
public class SimpleFactoryDemo {

	public SimpleFactoryDemo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// 实例化各种食物
		Food mcChicken = FoodFactory.getFood("McChicken");
		Food chips = FoodFactory.getFood("Chips");
		Food eggs = FoodFactory.getFood("Eggs");

		// 获取食物
		if (mcChicken != null) {
			mcChicken.get();
		}
		if (chips != null) {
			chips.get();
		}
		if (eggs != null) {
			eggs.get();
		}
	}

}

/**
 * ] 产品的抽象接口
 * 
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月28日
 */
interface Food {
	public void get();
}

class McChicken implements Food {
	/*
	 * 获取一份麦香鸡
	 */
	public void get() {
		System.out.println("我要一份麦香鸡");
	}
}

class Chips implements Food {
	/*
	 * 获取一份薯条
	 */
	public void get() {
		System.out.println("我要一份薯条");
	}
}

/**
 * 
 * 生产食品的工厂(核心类)
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月28日
 */
class FoodFactory {
    /**
     * 创建实例
     * @param type
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
	public static Food getFood(String type) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (type.equalsIgnoreCase("mcchicken")) {
			return McChicken.class.newInstance();

		}
		else if (type.equalsIgnoreCase("chips")) {
			return Chips.class.newInstance();
		}
		else {
			System.out.println("哎呀！找不到相应的实例化类啦！");
			return null;
		}

	}
}