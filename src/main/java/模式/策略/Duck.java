package 模式.策略;
/**
 * 
 * @author 高鹤
 *
 * 作用:策略基类：鸭子
 *
 * 2015年11月5日
 */
public abstract class Duck {
	private FlyingStragety flyingStragety;

	public FlyingStragety getFlyingStragety() {
		return flyingStragety;
	}

	public void setFlyingStragety(FlyingStragety flyingStragety) {
		this.flyingStragety = flyingStragety;
	}

	public void say() {
		System.out.println("嘎嘎噶的叫声--------------------");
	}

	public abstract void display();
	
	public void fly(){
		this.flyingStragety.performFly();
	}
}
