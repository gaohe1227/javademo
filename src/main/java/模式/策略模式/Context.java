package 模式.策略模式;
/**
 * 环境角色
 * 
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月29日
 */
public class Context {

	public Context() {
		// TODO Auto-generated constructor stub
	}

	Strategy stra;

	public Context(Strategy stra) {
		this.stra = stra;
	}

	public void doMethod() {
		this.stra.method();
	}
	public static void main(String[] args) {
		Context context1=new Context(new StrategyImplA());
		context1.doMethod();
		Context context2=new Context(new StrategyImplB());
	}
}
