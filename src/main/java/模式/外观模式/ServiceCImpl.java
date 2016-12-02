package 模式.外观模式;

public class ServiceCImpl implements ServiceC {

	public ServiceCImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see 模式.外观模式.ServiceA#methodA()
	 */
	@Override
	public void methodC() {
		System.out.println("methodC--> is runing");
	}

}
