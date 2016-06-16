package 多线程;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月15日
 *
 * 作用:生产者和消费者案例
 */
public class 生产者和消费者 {
	public static void main(String[] args) {
		Info f=new Info();
		Producer p=new Producer(f);
		Customer c=new Customer(f);
		new Thread(p).start();
		new Thread(c).start();
	}

}
class Info{
	private String name="李兴华";
	private String content="JAVA讲师";
	boolean flag=false;
	public String getName() {
		return name;
	}
	/**\
	 * 设置值
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
	System.out.println("生产");
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
 * @author 高鹤
 *
 * 2016年6月15日
 *
 * 作用:生产者
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
		 * 生产信息
		 */
		for(int i=0;i<50;i++){
			 
			if(flag){
				this.info.set("李兴华","JAVA讲师"); 
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
 * @author 高鹤
 *
 * 2016年6月15日
 *
 * 作用:消费者
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
				 //System.out.println(this.info.getName()+"------>"+this.info.getContent());//取出信息
				 this.info.get();//取出信息
		}
		
	}
	
}