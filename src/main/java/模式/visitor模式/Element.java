package 模式.visitor模式;

import java.util.ArrayList;

/**
 * 
 * 接受拜访者的接口
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public interface Element {

	public void accept(Visitor visitor);
}

class City implements Element {
	ArrayList<Element> places = new ArrayList<Element>();

	public City() {
		places.add(new Museum());
		places.add(new Park());
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		System.out.println("City is 接受 visitor.");
		visitor.visit(this);

		for (Element e : places) {
			e.accept(visitor);
		}
	}

}

class Museum implements Element {
	@Override
	public void accept(Visitor visitor) {
		System.out.println("Museum is 接受 visitor.");
		visitor.visit(this);
	}
}

class Park implements Element {
	@Override
	public void accept(Visitor visitor) {
		System.out.println("Park is 接受 visitor.");
		visitor.visit(this);
	}

}