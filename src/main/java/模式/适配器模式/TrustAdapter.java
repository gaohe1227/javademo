package 模式.适配器模式;
/**
 * 
 * 委托适配器(直接关联被适配器类,并实现目标接口)
 * Java语言中的委托是指把某个方法的实际处理交给其他对象的方法执行
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月28日
 */
public class TrustAdapter  implements Target{
	private Adaptee adaptee;

	public Adaptee getAdaptee() {
		return adaptee;
	}

	public void setAdaptee(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public String toString() {
		return "TrustAdapter [adaptee=" + adaptee + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 使用普通功能类
				Target concreteTarget = new ConcreteTarget();
				concreteTarget.request();
				
				// 使用特殊功能类，即适配类，
				// 需要先创建一个被适配类的对象作为参数
				Target adapter = new TrustAdapter(new Adaptee());
				adapter.request();
	}

	public TrustAdapter(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public void request() {
		// TODO Auto-generated method stub
		this.adaptee.specificRequest();
	}

}
