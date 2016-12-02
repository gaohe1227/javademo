package 模式.装饰器模式;
/**\
 * 
 * 装饰角色
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class SuperCar implements Car {
	private Car car;

	public SuperCar(Car car) {
		// TODO Auto-generated constructor stub
		this.car=car;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		car.move();

	}

}
  class FlyCar extends SuperCar{

    public FlyCar(Car car) {
        super(car);
    }

    public void fly() {
        System.out.println("空中行驶汽车");
    }


    @Override
    public void move() {
        super.move();
         fly();
    }

}

///////////////////////////////////以下为具体装饰角色

/**
 * 水上汽车
 */
  class WaterCar extends SuperCar{

	public WaterCar(Car car) {
        super(car);
    }

    public void swim() {
        System.out.println("水上行驶汽车");
    }
    @Override
    public void move() {
        super.move();
        swim();
    }

}