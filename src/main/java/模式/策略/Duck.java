package ģʽ.����;
/**
 * 
 * @author �ߺ�
 *
 * ����:���Ի��ࣺѼ��
 *
 * 2015��11��5��
 */
public abstract class Duck {
	private FlyingStragety flyingStragety;

	public FlyingStragety getFlyingStragety() {
		return flyingStragety;
	}

	public void setFlyingStragety(FlyingStragety flyingStragety) {
		this.flyingStragety = flyingStragety;
	}

	public void say() {
		System.out.println("�¸¸��Ľ���--------------------");
	}

	public abstract void display();
	
	public void fly(){
		this.flyingStragety.performFly();
	}
}
