package 模式.观察者;

/**
 * 
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class DigitOberver implements NumberObserver {

 
	 

	/*
	 * (non-Javadoc)
	 * 
	 * @see 模式.观察者.NumberObserver#update(模式.观察者.NumberGenenrtor)
	 */
	@Override
	public void update(NumberGenenrtor numberGenenrtor) {
		// TODO Auto-generated method stub
		System.out.println("DigitOberver:" + numberGenenrtor.getNumber());
		try {
			Thread.sleep(100);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumberGenenrtor numberGenenrtor=new RandomNumberGenenrtor();
		NumberObserver numberObserver1=new DigitOberver();
		NumberObserver numberObserver2=new DigitOberver();
		numberGenenrtor.addObservers(numberObserver1);
		numberGenenrtor.addObservers(numberObserver2);
		numberGenenrtor.createNumber();
		 

	}

}
