package 模式.适配器模式;
/**
 * 
 * @author 高鹤
 *
 * 作用:二相转三相适配器
 *
 * 2015年11月3日
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
		System.out.println("通过转化");
		gbTwoPlug.powerWithTwo();

	}

}
