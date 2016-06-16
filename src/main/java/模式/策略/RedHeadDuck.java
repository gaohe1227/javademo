package 模式.策略;
/**\
 * 
 * @author 高鹤
 *
 * 作用:实现类：红头鸭
 *
 * 2015年11月5日
 */
public class RedHeadDuck extends Duck{

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("我是红头鸭");
	}

	public RedHeadDuck() {
		super();
		// TODO Auto-generated constructor stub
		super.setFlyingStragety(new FlyWithStragety());
	}

}
