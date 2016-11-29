package jdk8;

import java.util.function.Function;

/**
 * 
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月25日
 */
public class FunctionDemo1 {
    static void modifyValue(int valueToBeOperated,Function<Integer,String> functions){
    	String newValue=functions.apply(valueToBeOperated);
    	System.out.println(newValue);
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int inrc=15;int myNumber=20;
		modifyValue(2, val->val+"---"+inrc);
		Function<Integer,Integer> f1=val->val+myNumber;
		System.out.println(f1.apply(22));
		

	}

}
