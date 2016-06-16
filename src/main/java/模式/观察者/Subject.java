package ģʽ.�۲���;

import java.util.ArrayList;

/**
 * 
 * @author �ߺ�
 *
 * ����:Ŀ�������࣬��֪���۲����Ĺ۲��ߣ����ṩע��(���)��ɾ���Ľӿ�
 *
 * 2015��11��5��
 */
public class Subject {
	private ArrayList<Observer> observers = new ArrayList<Observer>();// ����ע��Ĺ۲��߶���

	public void attach(Observer observer) {// �ɹ۲��߶�����ӵ��۲��߼�����
		this.observers.add(observer);
	}
	public void detach(Observer observer){//ɾ��ָ���۲���
		this.observers.remove(observer);
	}
	//֪ͨ���еĹ۲���
	protected void notifytach(){
		for (Observer observer : observers) {
			observer.update(this);
		}
	}
}
