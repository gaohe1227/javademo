package 多线程.队列;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 阻塞队列LinkedBlockingDeque案例
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月23日
 */
public class Client implements Runnable {
   private LinkedBlockingQueue<String> requestList;
   
	public Client(LinkedBlockingQueue<String> requestList) {
	super();
	this.requestList = requestList;
}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<3;i++){
			for(int j=0;j<5;j++){
				StringBuilder request=new StringBuilder();
				request.append(i+":"+j);
				try {
					requestList.put(request.toString());
				}
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.printf("Client: %s at %s.\n",request,new Date());
				try {
					TimeUnit.SECONDS.sleep(2);//沉睡
				}
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.printf("Client: End.\n");
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedBlockingQueue<String> list=new LinkedBlockingQueue<String>();//创建阻塞队列对象象
		Client client=new Client(list);
		new Thread(client).start();
		for(int i=0;i<3;i++){
			for(int j=0;j<5;j++){
				try {
					String request=list.take();//从队列中取值
					System.out.printf("Main: Request: %s at %s. Size:%d\n",request,new Date(),list.size());


					TimeUnit.MILLISECONDS.sleep(300);//沉睡300毫秒
				}
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

}
