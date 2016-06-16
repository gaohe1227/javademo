package 模式.责任链;
/**
 * 
 * @author 高鹤
 *
 * 作用:生产责任链的工厂类
 *
 * 2015年11月5日
 */
public class PriceHandlerFacory {

	/**\
	* 创建PriceHandler的工厂方法
	* @return
	*/
	public static PriceHandler createPriceHandler() {
		// TODO Auto-generated method stub
		PriceHandler sale=new Sales();
		PriceHandler manager=new Manager();
		PriceHandler director=new Director();
		PriceHandler vip=new VIP();
		PriceHandler ceo=new CEO();
		sale.setSuccessor(manager);
		manager.setSuccessor(director);
		director.setSuccessor(vip);
		vip.setSuccessor(ceo);
		return sale;
	}

}
