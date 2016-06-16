package ģʽ.������;
/**
 * 
 * @author �ߺ�
 *
 * ����:�����������Ĺ�����
 *
 * 2015��11��5��
 */
public class PriceHandlerFacory {

	/**\
	* ����PriceHandler�Ĺ�������
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
