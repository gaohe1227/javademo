package 模式.责任链;

public class Manager  extends PriceHandler {

	@Override
	public void processCount(float discount) {
		// TODO Auto-generated method stub
		if(discount<=0.3){
			System.out.format("%s批准了折扣:%.2f%n", this.getClass().getName(),discount);
		}else{
			this.successor.processCount(discount);
		}
	}

}
