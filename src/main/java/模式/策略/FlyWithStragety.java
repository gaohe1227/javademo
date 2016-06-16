package 模式.策略;
/**
 * 
 * @author 高鹤
 *
 * 作用:飞行策略实现类
 *
 * 2015年11月5日
 */
public class FlyWithStragety implements FlyingStragety {

	@Override
	public void performFly() {
		// TODO Auto-generated method stub
      System.out.println("用翅膀飞行");
	}

}
