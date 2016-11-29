package 模式.工厂模式;

/**
 * 
 * 工厂方法模式
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月28日
 */
public class FactoryMdthodDemo {

	public FactoryMdthodDemo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 找到汽车主管，告诉他今天我想开奔驰看利物浦对阵维拉的比赛
		CarManager carManager = new BenzManager();
		// 负责宝马汽车的司机接到管家的电话，给宝马加满油,宝马来喽
		Car driveCar = carManager.driveCar();
		// 飞奔利物浦
		driveCar.drive();
	}

}

interface Car {
	public void drive();
}

class Audi implements Car {

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		System.out.println("我是奥迪车");
	}

}

class Benchi implements Car {
	@Override
	public void drive() {
		// TODO Auto-generated method stub
		System.out.println("我是奔驰车");
	}
}

interface CarManager {

	public Car driveCar();

}

class AudiManager implements CarManager {
	public Car driveCar() {

		return new Audi();

	}
}

class BenzManager implements CarManager {

	public Car driveCar() {

		return new Benchi();

	}
}