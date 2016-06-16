package ���߳�;
/**
 * 
 * @author �ߺ�
 *
 * 2016��6��15��
 *
 * ����:�����ߺ������߰���
 */
public class �����ߺ������� {
	public static void main(String[] args) {
		Info f=new Info();
		Producer p=new Producer(f);
		Customer c=new Customer(f);
		new Thread(p).start();
		new Thread(c).start();
	}

}
class Info{
	private String name="���˻�";
	private String content="JAVA��ʦ";
	boolean flag=false;
	public String getName() {
		return name;
	}
	/**\
	 * ����ֵ
	 * @param name
	 * @param content
	 */
	public synchronized void set(String name,String content){
	 if(!flag){
			
			try {
				super.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
	 }
	 this.name = name;
	this.content = content;
	System.out.println("����");
	flag=false;
	super.notify();	
	 
	 
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public synchronized void get() {
 
		 if(flag){ 
				try {
					super.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		 }
			System.out.println(this.name+"--->"+this.content);
		 flag=true;
		 super.notify();	
				

		
	}
	 
}
/**
 * 
 * @author �ߺ�
 *
 * 2016��6��15��
 *
 * ����:������
 */
class Producer implements Runnable{
 private Info info;
	public Producer(Info info) {
	super();
	this.info = info;
}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean flag=false;
		/**
		 * ������Ϣ
		 */
		for(int i=0;i<50;i++){
			 
			if(flag){
				this.info.set("���˻�","JAVA��ʦ"); 
				flag=false; 
			}else{
				this.info.set("mldn","www.mldnjava.cn"); 
				flag=true;
			}
		}
		
	}
	
}
/**]
 * 
 * @author �ߺ�
 *
 * 2016��6��15��
 *
 * ����:������
 */
class Customer implements Runnable{
	 private Info info;
		public Customer(Info info) {
		super();
		this.info = info;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<50;i++){ 
			 
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 //System.out.println(this.info.getName()+"------>"+this.info.getContent());//ȡ����Ϣ
				 this.info.get();//ȡ����Ϣ
		}
		
	}
	
}