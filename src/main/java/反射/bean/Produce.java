package 反射.bean;

public class Produce {
	private String name;
	private String price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Produce(String name, String price) {
		super();
		this.name = name;
		this.price = price;
	}
	private int cost(){
		return Integer.parseInt(price)/2;
	}
	public Produce() {
		super();
		// TODO Auto-generated constructor stub
	}

}
