package ģʽ.�۲���;

/**
 * 
 * @author �ߺ�
 *
 *  ����:����Ĺ۲��߽ӿ�
 *
 *  2015��11��5��
 */
public class ConcreteObserver implements Observer {
	private String observerState;// �۲���״̬

	@Override
	public void update(Subject subject) {
		// TODO Auto-generated method stub
		observerState = ((ConcreteSubject) subject).getSubjectstate();

	}

}
