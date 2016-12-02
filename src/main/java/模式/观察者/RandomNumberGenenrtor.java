package 模式.观察者;

import java.util.Random;

/**
 * 
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class RandomNumberGenenrtor extends NumberGenenrtor{
    private Random random=new Random();
    private int number;
	/**
	 * 
	 */
	public RandomNumberGenenrtor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return this.number;
	}

	@Override
	public void createNumber() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			number=random.nextInt(50);
			notifyAllNumberObserver();
		}
		
	}

}
