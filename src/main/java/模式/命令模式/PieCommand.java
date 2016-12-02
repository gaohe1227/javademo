package 模式.命令模式;

/**
 * 
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class PieCommand implements Command {

	private PieChef chef;// 专业做饼的厨师

	public PieCommand() {
		chef = new PieChef();
	}

	@Override
	public void execute() {
		chef.cook();
		// 调用其它需要的方法
	}

}
