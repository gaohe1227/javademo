package ģʽ.������ģʽ;
/**
 * 
 * @author �ߺ�
 *
 * ����:����ת����������
 *
 * 2015��11��3��
 */
public class TwoPlugAdater implements ThreePlugif {
	private GBTwoPlug gbTwoPlug;

	public TwoPlugAdater(GBTwoPlug gbTwoPlug) {
		super();
		this.gbTwoPlug = gbTwoPlug;
	}

	@Override
	public void powerWithThree() {
		// TODO Auto-generated method stub
		System.out.println("ͨ��ת��");
		gbTwoPlug.powerWithTwo();

	}

}
