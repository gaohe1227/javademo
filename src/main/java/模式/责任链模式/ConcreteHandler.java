package 模式.责任链模式;

/**
 * 
 * 具体的处理者
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class ConcreteHandler extends Handler {

	public ConcreteHandler() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 处理请求
	 */
	@Override
	public void handleRequest() {
		// TODO Auto-generated method stub
		/**
		 * 判断是否有后继的责任对象 如果有，就转发请求给后继的责任对象 如果没有，则处理请求
		 */
		if (getSuccessor() != null) {
			System.out.println("放过请求");
			getSuccessor().handleRequest();
		}
		else {
			System.out.println("处理请求");
		}
	}

	public static void main(String[] args) {
		// 组装责任链
		Handler handler1 = new ConcreteHandler();
		Handler handler2 = new ConcreteHandler();
		handler1.setSuccessor(handler2);
		// 提交请求
		handler1.handleRequest();
	}

}
