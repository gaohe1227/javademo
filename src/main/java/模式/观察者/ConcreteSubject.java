package 模式.观察者;

/**
 * 
 * @author 高鹤
 *
 * 作用:具体目标对象的类
 *
 * 2015年11月5日
 */
public class ConcreteSubject extends Subject {
	private String subjectstate;// 目标对象的状态

	public String getSubjectstate() {
		return subjectstate;
	}

	public void setSubjectstate(String subjectstate) {
		this.subjectstate = subjectstate;
		this.notifytach();
	}

}