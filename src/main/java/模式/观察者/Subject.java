package 模式.观察者;

import java.util.ArrayList;

/**
 * 
 * @author 高鹤
 *
 * 作用:目标对象的类，它知道观察它的观察者，并提供注册(添加)和删除的接口
 *
 * 2015年11月5日
 */
public class Subject {
	private ArrayList<Observer> observers = new ArrayList<Observer>();// 保存注册的观察者对象

	public void attach(Observer observer) {// 吧观察者对象添加到观察者集合中
		this.observers.add(observer);
	}
	public void detach(Observer observer){//删除指定观察者
		this.observers.remove(observer);
	}
	//通知所有的观察者
	protected void notifytach(){
		for (Observer observer : observers) {
			observer.update(this);
		}
	}
}
