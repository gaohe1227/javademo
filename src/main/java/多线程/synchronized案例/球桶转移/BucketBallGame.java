package 多线程.synchronized案例.球桶转移;

public class BucketBallGame {
	private int buckt[]={1000,1000};
	private static boolean RIGHT_TO_LEFT; 
    private void doTransfer(){
    	for(int i=0;i<100;i++){
    		new  Thread(new TransferThread(!RIGHT_TO_LEFT)).start();
        	new  Thread(new TransferThread(RIGHT_TO_LEFT)).start();
    	}
    }
    
    public static void main(String[] args) {
		new BucketBallGame().doTransfer();
	}
    public synchronized void transfer(boolean direction ,int number){
    	if(direction==RIGHT_TO_LEFT){
    		buckt[0]-=number;
    		buckt[1]+=number;
    	}else{
    		buckt[0]+=number;
    		buckt[1]-=number;
    	}
    	System.out.println("total="+(buckt[0]+buckt[1]));
    	
    }
	class TransferThread implements Runnable{
	    private boolean direction;
		public TransferThread(boolean direction) {
			super();
			this.direction = direction;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0;i<100;i++){
				transfer(direction, (int)(Math.random()*2000));
				try{
					Thread.sleep((int)(Math.random()*100));
					
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
			
		}
		
	}
}
