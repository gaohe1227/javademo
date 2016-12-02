package 模式.装饰器模式;

/**
 * 
 * 客户端
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class Client {

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  DemoCar car=new DemoCar();
	        car.move();

	        System.out.println("------增加新功能,飞行------");
	        FlyCar flyCar=new FlyCar(car);
	        flyCar.move();

	        System.out.println("------新增加功能，水上行驶-----");
	        WaterCar waterCar=new WaterCar(car);
	        waterCar.move();

	        System.out.println("-----新增加两个功能，飞行与水上行驶-----");
	        WaterCar waterCar2=new WaterCar(flyCar);
	        waterCar2.move();
	}

}
