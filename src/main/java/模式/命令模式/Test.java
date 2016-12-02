package 模式.命令模式;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Command Pattern餐馆开张。。");
		System.out.println("第一位客户X先生");
		System.out.println("X先生：你好，我需要一碗面，我饿极了");
		NoodlesCommand nCmd = new NoodlesCommand();
		System.out.println("柜台服务员：好的，我已经记下了，马上就好");
		System.out.println("柜台服务员：厨房~~,接单");
		nCmd.execute();
		System.out.println("X先生：真快啊！");

		System.out.println();

		System.out.println("第二位客户XX先生");
		System.out.println("XX先生：你好，我需要一块饼，20分钟后来取");
		PieCommand pCmd = new PieCommand();
		System.out.println("柜台服务员：好的，我已经记下了");
		System.out.println("15分钟后");
		System.out.println("柜台服务员：厨房~~,接单");
		pCmd.execute();
		System.out.println("XX先生：真准时啊！");
	}

}
