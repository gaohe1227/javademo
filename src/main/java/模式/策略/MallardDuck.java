package 模式.策略;

public class MallardDuck extends Duck{

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("我是绿脖鸭");
	}

	public MallardDuck() {
		super();
		// TODO Auto-generated constructor stub
		super.setFlyingStragety(new FlyWithStragety());
	}

}
