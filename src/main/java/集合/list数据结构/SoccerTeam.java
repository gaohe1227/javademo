package 集合.list数据结构;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 基于LinkedList的足球队生成器
 * @author Administrator
 *
 */
public class SoccerTeam {
	public static void main(String[] args) {
		LinkedList<String> maleTeam=new LinkedList<>();
		maleTeam.add("Tom");
		maleTeam.add("John");
		maleTeam.add("Vitory");
		maleTeam.add("Aniya");
		maleTeam.add("Sam");
		System.out.println("男队:"+maleTeam);
		List<String> femaleTesm=new LinkedList<>();
		femaleTesm.add("Lucy");
		femaleTesm.add("Lucy");
		femaleTesm.add("Shariya");
		femaleTesm.add("Qierui");
		femaleTesm.add("Jane");
		femaleTesm.add("Antira");
		System.out.println("女队:"+femaleTesm);
		ListIterator<String> mIterator=maleTeam.listIterator();
		Iterator<String> feIterator=femaleTesm.iterator();
		while(feIterator.hasNext()){
			if(mIterator.hasNext()){
				mIterator.next();
			}
			mIterator.add(feIterator.next());
		}
		System.out.println("混合队:"+maleTeam+"--"+mIterator);
		
		List<String> disTeam=new LinkedList<>();
		disTeam.add("Lucy");
		disTeam.add("Jane");
		maleTeam.removeAll(disTeam);
		System.out.println("操作后"+maleTeam);
	}

}
