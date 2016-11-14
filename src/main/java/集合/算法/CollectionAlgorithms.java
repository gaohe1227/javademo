package 集合.算法;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionAlgorithms {
     public static void main(String[] args) {
		List<Integer> list=new ArrayList();
		for(int i=0;i<100;i++){
			list.add((int)(Math.random()*100));
		}
		Collections.sort(list);//为集合排序
		/*for (Integer integer : list) {
			System.out.print(integer+" ");
		}*/
		System.out.println(list);
	}
}
