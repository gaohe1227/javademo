package ģʽ.����;

public class MallardDuck extends Duck{

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("�����̲�Ѽ");
	}

	public MallardDuck() {
		super();
		// TODO Auto-generated constructor stub
		super.setFlyingStragety(new FlyWithStragety());
	}

}
