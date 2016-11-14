package 数据库.连接池.自定义连接池;

import java.util.concurrent.atomic.AtomicLong;

import com.sun.org.apache.bcel.internal.classfile.Synthetic;

import 数据库.连接池.自定义连接池.DBpool.MyConnection;
 

public class Test {
     static AtomicLong threadSum=new AtomicLong(0);
	public static void main(String args[]) {
		try {
			int i = 0;
			DBpool dBpool = new DBpool();
			while (i < 1000) {
				new Thread(new MyConnectionRunnable(i)).start();
				i++;
			}
		}
		catch (Exception | Error e1) {
			// TODO: handle exception
			System.out.println(Thread.currentThread().getName() + "------------" + DBpool.curLink + "---" + DBpool.connectionList.size());
			e1.printStackTrace();
			System.exit(0);
		}
	}
}

class MyConnectionRunnable implements Runnable {
	int i = 0;

	boolean flag = true;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			while (flag) {
				Thread.sleep(1000);

				MyConnection connection = DBpool.getConnection();
				if (connection != null) {
					DBpool.releaseConnection(connection);
					flag = false;
					Test.threadSum.incrementAndGet();
					System.err.println(Thread.currentThread().getName() + "销毁");
					if(Test.threadSum.get()>=1000){
						System.err.println("线程结束-------------------------------------------------------------");
					}
					
				}
			}

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.currentThread().setName("线程" + i);

	}

	public MyConnectionRunnable(int i) {
		super();
		this.i = i;
	}

}
