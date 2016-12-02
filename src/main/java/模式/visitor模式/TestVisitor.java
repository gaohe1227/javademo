package 模式.visitor模式;

public class TestVisitor {

	public TestVisitor() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		FirstTimeVisitor visitor = new FirstTimeVisitor();
		City city = new City();
		city.accept(visitor);
	}
}
