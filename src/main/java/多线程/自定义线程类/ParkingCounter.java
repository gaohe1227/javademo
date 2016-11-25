package 多线程.自定义线程类;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 *  自定义原子对象(对停车场的车辆进出进行计数)
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月24日
 */
public class ParkingCounter extends AtomicInteger {
	private int maxNumber;//声明一个私有的、int类型的属性maxNumber，用来存储停车场允许停放汽车的最大数量

	public ParkingCounter(int maxNumber) {//实现这个类的构造器，并初始化它的属性
		set(0);
		this.maxNumber = maxNumber;
	}
	/**
	 * 车进入停车场这个方法用于增加车的计数器,如果小于它设置的最大数,构成一个循环,并使用get()方法获取内部计数器的值环
	 * @return
	 */
public boolean carIn(){
	for(;;){
		int value=get();
		if(value==maxNumber){//如果计数器的值等于最maxNumber属性值，这个计数器不能再增加（停车场已满，其他车不能再进入）。这个方法返回false值。
			System.out.println("停车场已满，其他车不能再进入");
			return false;
		}else{
			int newValue=value+1;
			boolean changed=compareAndSet(value,newValue);
			if(changed){
				System.out.println("一辆车进入了");
				return true;
			}

		}
	}
	
}
/**
 * 车离开停车场(减少车的计数器)
 * @return
 */
public boolean carOut(){
	for(;;){
		int value=get();
			if(value==0){
				System.out.println("停车场已空");
				return false;
			}else{
				int newValue=value-1;
				boolean changed=compareAndSet(value,newValue);//比较值是否改变
				if(changed){
					System.out.println("一辆车离开了开");
					return true;
				}
				 
			}
		}
	 
}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParkingCounter counter=new ParkingCounter(5);

		Sensor1 sensor1=new Sensor1(counter);
		Sensor2 sensor2=new Sensor2(counter);
		Thread thread1=new Thread(sensor1);
		Thread thread2=new Thread(sensor2);
		thread1.start();
		thread2.start();
		try {
			thread1.join();
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			thread2.join();
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("Main: Number of cars: %d\n",counter.get());
		System.out.printf("Main: End of the program.\n");


	}

}
class Sensor1 implements Runnable{
	private ParkingCounter counter;
	public Sensor1(ParkingCounter counter) {
		super();
		this.counter = counter;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carOut();
		counter.carOut();
		counter.carOut();
		counter.carIn();
		counter.carIn();
		counter.carIn();
	}
	
}
class Sensor2 implements Runnable{
	private ParkingCounter counter;
	public Sensor2(ParkingCounter counter) {
		super();
		this.counter = counter;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		counter.carIn();
		counter.carOut();
		counter.carOut();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();
	}
}