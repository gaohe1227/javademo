package 模式.命令模式;

/**
 * 
 * 定义专业做饼的厨师
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class PieChef extends Chef {

	/**
	 * 
	 */
	public PieChef() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see 模式.命令模式.Chef#cook()
	 */
	@Override
	public void cook() {
		// TODO Auto-generated method stub
		System.out.println("做好了一块香喷喷的大饼");
	}

}
