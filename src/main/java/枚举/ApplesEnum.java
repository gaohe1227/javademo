package ö��;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author �ߺ�
 *
 * 2016��6��14��
 *
 * ����:ö�ٰ���
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
			System.out.println(a.getPrice() + "-----------" + a + "---" + a.name() + "--" + a.ordinal());//name:ö�ٳ���������;ordinal:ö�ٳ��������
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
			System.out.println(aIterator.next());//������֤������ʱ����ordinal����
		}
		
		
		
	}
}
