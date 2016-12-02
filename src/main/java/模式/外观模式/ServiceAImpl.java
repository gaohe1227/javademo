package 模式.外观模式;

/**
 * 
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class ServiceAImpl implements ServiceA {

	/**
	 * 
	 */
	public ServiceAImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see 模式.外观模式.ServiceA#methodA()
	 */
	@Override
	public void methodA() {
		// TODO Auto-generated method stub
		System.out.println( "methodA--> is runing" );  
	}

}
