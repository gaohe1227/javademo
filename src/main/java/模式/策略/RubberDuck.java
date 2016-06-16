package 模式.策略;

public class RubberDuck  extends Duck{

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("我是假的鸭子");
		
	}

	public RubberDuck() {
		super();
		// TODO Auto-generated constructor stub
		super.setFlyingStragety(new FlyNoway());
	}

}
