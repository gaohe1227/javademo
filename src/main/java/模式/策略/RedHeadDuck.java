package ģʽ.����;
/**\
 * 
 * @author �ߺ�
 *
 * ����:ʵ���ࣺ��ͷѼ
 *
 * 2015��11��5��
 */
public class RedHeadDuck extends Duck{

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("���Ǻ�ͷѼ");
	}

	public RedHeadDuck() {
		super();
		// TODO Auto-generated constructor stub
		super.setFlyingStragety(new FlyWithStragety());
	}

}
