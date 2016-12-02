package 模式.visitor模式;

/**
 * 
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class FirstTimeVisitor implements Visitor {

	/**
	 * 
	 */
	public FirstTimeVisitor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visit(City city) {
		// TODO Auto-generated method stub
		System.out.println("我在拜访City");
	}

	@Override
	public void visit(Museum museum) {
		// TODO Auto-generated method stub
		System.out.println("我在拜访Museum");
		
	}

	@Override
	public void visit(Park park) {
		// TODO Auto-generated method stub
		System.out.println("我在拜访Park");
	}

}
