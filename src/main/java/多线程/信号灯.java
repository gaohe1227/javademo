package ���߳�;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 * 
 * @author �ߺ�
 *
 * 2016��6��16��
 *
 * ����:�źŵư���
 */
public class �źŵ� {

	public �źŵ�() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
      
		ExecutorService service = Executors.newCachedThreadPool();
		final  Semaphore sp = new Semaphore(3);
		for(int i=0;i<10;i++){
			Runnable runnable = new Runnable(){
					public void run(){
					try {
						sp.acquire();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.err.println("�߳�" + Thread.currentThread().getName() + 
							"���룬��ǰ����" + (3-sp.availablePermits()) + "������");
					try {
						Thread.sleep((long)(Math.random()*10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("�߳�" + Thread.currentThread().getName() + 
							"�����뿪");					
					sp.release();
					//���������ʱ��ִ�в�׼ȷ����Ϊ��û�к�����Ĵ���ϳ�ԭ�ӵ�Ԫ
					System.out.println("�߳�" + Thread.currentThread().getName() + 
							"���뿪����ǰ����" + (3-sp.availablePermits()) + "������");					
				}
			};
			service.execute(runnable);			
		}
	}
}
