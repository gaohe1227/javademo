package 模式.观察者;

/**
 * 
 * @author 高鹤
 *
 *  作用:具体的观察者接口
 *
 *  2015年11月5日
 */
public class ConcreteObserver implements Observer {
	private String observerState;// 观察者状态

	@Override
	public void update(Subject subject) {
		// TODO Auto-generated method stub
		observerState = ((ConcreteSubject) subject).getSubjectstate();

	}

}
