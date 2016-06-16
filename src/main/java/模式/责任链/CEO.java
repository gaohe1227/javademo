package 模式.责任链;

public class CEO extends PriceHandler {

	@Override
	public void processCount(float discount) {
		// TODO Auto-generated method stub
		if(discount<=0.55){
			System.out.format("%s批准了折扣:%.2f%n", this.getClass().getName(),discount);
		}else{
			System.out.format("%s拒绝折扣:%.2f%n", this.getClass().getName(),discount);
		}
	}

}
