package ģʽ.������;

public class CEO extends PriceHandler {

	@Override
	public void processCount(float discount) {
		// TODO Auto-generated method stub
		if(discount<=0.55){
			System.out.format("%s��׼���ۿ�:%.2f%n", this.getClass().getName(),discount);
		}else{
			System.out.format("%s�ܾ��ۿ�:%.2f%n", this.getClass().getName(),discount);
		}
	}

}
