package ģʽ.������;

public class Sales extends PriceHandler{

	@Override
	public void processCount(float discount) {
		// TODO Auto-generated method stub
		if(discount<=0.05){
			System.out.format("%s��׼���ۿ�:%.2f%n", this.getClass().getName(),discount);
		}else{
			this.successor.processCount(discount);
		}
	}

}
