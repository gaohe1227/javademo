package ���߳�;
/**
 * 
 * @author �ߺ�
 *
 * 2016��6��15��
 *
 * ����:���߳�״̬:Thread t=new Thread(new MyThread(),"�����߳�")������;t.start():��������;
 *            t.join():����;t��run()ִ��ʱ:����״̬;stop()��run����ִ����ϣ��߳�����
 */
public class MyThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
	//��ǰ�߳�����1����
		for(int i=0;i<=100;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i==50){
				 
				Thread.currentThread().interrupt();//�սᵱǰ�̵߳�����״̬
			}
			System.out.println(Thread.currentThread().getName()+"----"+i);
		}
	}
public static void main(String[] args) {
	Thread t=new Thread(new MyThread(),"�����߳�");
	t.start();
	for(int i=0;i<50;i++){
		 
		if(i==10){
			try {
				t.join();//�߳�ǿ��ִ��
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("���߳�:"+i);
	}
	
}
}
