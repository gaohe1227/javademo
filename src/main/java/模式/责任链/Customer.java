package ģʽ.������;

public class Customer {
	private PriceHandler priceHandler;
	public PriceHandler getPriceHandler() {
		return priceHandler;
	}
	public void setPriceHandler(PriceHandler priceHandler) {
		this.priceHandler = priceHandler;
	}
	public void requestDiscount(float discount){
		this.priceHandler.processCount(discount);
	}

}
