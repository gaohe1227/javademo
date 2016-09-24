package jdk8;

import java.util.function.Function;

import com.alibaba.druid.support.http.util.IPAddress;
/**
 * 
 * @author 高鹤
 *
 * 2016年8月23日
 *
 * 作用：function接口
 */
public class FunctionDemo {
	static void modifyTheValue(int valueToBeOperated,Function<Integer, Integer> function) {

		int newValue = function.apply(valueToBeOperated);

		System.out.println(newValue); 
	}

	public static void main(String[] args) {

		int incr = 20;
		int myNumber = 10;
		modifyTheValue(myNumber, val -> val + incr);
		myNumber = 15;
		modifyTheValue(myNumber, val -> val * 10);
		modifyTheValue(myNumber, val -> val - 100);
		modifyTheValue(myNumber, val -> "somestring".length() + val - 100);
		
		 
	}

}
