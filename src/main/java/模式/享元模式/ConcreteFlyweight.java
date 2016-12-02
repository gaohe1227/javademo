package 模式.享元模式;

/**
 * 
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class ConcreteFlyweight implements Flyweight {

	/**
	 * 
	 */
	public ConcreteFlyweight() {
		// TODO Auto-generated constructor stub
	}

	private String intrinsicState = null;

    /**
     * 构造函数 内蕴状态作为参数传入
     */
    public ConcreteFlyweight(String _intrinsicState) {
        this.intrinsicState = _intrinsicState;
    }

    /**
     * 外蕴状态作为参数传入方法中 改变方法的行为 但是并不改变对象的内蕴状态
     */
    @Override
    public void operation(String extrinsicState) {
        System.out.println("内蕴状态：" + intrinsicState);
        System.out.println("外蕴状态：" + extrinsicState);
    }

}
