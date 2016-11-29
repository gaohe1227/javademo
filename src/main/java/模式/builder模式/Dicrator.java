package 模式.builder模式;

public class Dicrator {
   private Builder builder;
	public Dicrator(Builder builder) {
		// TODO Auto-generated constructor stub
		this.builder=builder;
	}
	public Object construct(){
		builder.makeTitle("Greeting");
		builder.makeString("从早走到晚上");
		builder.makeItems(new String[]{"晚安","祝你有个好梦","再见"});
		return builder.getResult();
	}

}
