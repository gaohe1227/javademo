package 模式.命令模式;

/**
 * 
 * 专业做面点的厨师 
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class NoodlesChef extends Chef {

	/**
	 * 
	 */
	public NoodlesChef() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see 模式.命令模式.Chef#cook()
	 */
	@Override
	public void cook() {
		// TODO Auto-generated method stub
		System.out.println("做好了一碗美味的拉面");
	}

}
