package �㷨.����;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * 
 * @author �ߺ�
 *
 * 2016��6��16��
 *
 * ����:��������
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
	Collections.sort(strings);//����
	Collections.sort(strings,Collections.reverseOrder());//����
	for (String string : strings) {
		System.out.print(string+"   ");
	}
	System.out.println();
	/**
	 * ��ת����
	 * 
	 */
	Collections.reverse(strings);
	for (String string : strings) {
		System.out.print(string+"   ");
	}
}
}
