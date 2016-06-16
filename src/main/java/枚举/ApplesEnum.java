package 枚举;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author 高鹤
 *
 * 2016年6月14日
 *
 * 作用:枚举案例
 */
enum Apple {
	 BELMAC(11), AUROLE(10), CORTLAND(15),EMPRICE(8);
	private int price;

	public int getPrice() {
		return price;
	}

	private Apple(int price) {
		this.price = price;
	}

}

public class ApplesEnum {
	public static void main(String[] args) {
		for (Apple a : Apple.values()) {
			System.out.println(a.getPrice() + "-----------" + a + "---" + a.name() + "--" + a.ordinal());//name:枚举常量的名称;ordinal:枚举常量的序号
		}
		
		Set<Apple> cset=new TreeSet<Apple>();
		cset.add(Apple.CORTLAND);
		cset.add(Apple.AUROLE);
		cset.add(Apple.EMPRICE);
		cset.add(Apple.CORTLAND);
		cset.add(Apple.AUROLE);
		
		cset.add(Apple.CORTLAND);
		cset.add(Apple.BELMAC);
		Iterator<Apple> aIterator=cset.iterator();
		while(aIterator.hasNext()){
			System.out.println(aIterator.next());//输出结果证明按此时按照ordinal排列
		}
		
		
		
	}
}
