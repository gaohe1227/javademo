package 模式.命令模式;

/**
 * 
 *实现具体NoodlesCommand
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class NoodlesCommand implements Command {

	private NoodlesChef chef;//专业做面的厨师
    
    public NoodlesCommand(){
        chef = new NoodlesChef();
    }
 

	/* (non-Javadoc)
	 * @see 模式.命令模式.Command#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		chef.cook();
	}

}
