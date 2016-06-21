 package 算法.排序;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * 
 * @author 高鹤
 *
 * 2016年6月16日
 *
 * 作用:数组排序
 */
public class ArraysUtil {
public static void main(String[] args) {
	String[] names={"a","f","1","65","6","g","b","m","k"};
	Arrays.sort(names);
	for (String string : names) {
		System.out.print(string+"  ");
	}
	System.out.println();
	List<String> strings=Arrays.asList(names);
	Collections.sort(strings);//升序
	Collections.sort(strings,Collections.reverseOrder());//降序
	for (String string : strings) {
		System.out.print(string+"   ");
	}
	System.out.println();
	/**
	 * 反转排序
	 * 
	 */
	Collections.reverse(strings);
	for (String string : strings) {
		System.out.print(string+"   ");
	}
}
}