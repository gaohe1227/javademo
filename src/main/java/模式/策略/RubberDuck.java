package ģʽ.����;

public class RubberDuck  extends Duck{

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("���Ǽٵ�Ѽ��");
		
	}

	public RubberDuck() {
		super();
		// TODO Auto-generated constructor stub
		super.setFlyingStragety(new FlyNoway());
	}

}
