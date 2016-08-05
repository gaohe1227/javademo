package jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author 高鹤
 *
 * 2016年8月3日
 *
 * 作用:jdk8流操作的案例
 */
public class StreamDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> collected=Stream.of("a","b","c").collect(Collectors.toList());//collect操作collect(toList())
		System.out.println(collected);
		List<String> maped=Stream.of("a","b","Hello").map(string->string.toUpperCase()).collect(Collectors.toList());//map操作
		System.out.println(maped);
		List<String> filterList=Stream.of("a","1abc","abc1").filter(value->value.contains("b")).collect(Collectors.toList());//filter操作
		System.out.println(filterList);
		List<List<Integer>> together=Stream.of(Arrays.asList(1,2),Arrays.asList(3,4)).flatMap(numbers->Stream.of(numbers)).collect(Collectors.toList());//flatMap操作
		System.out.println(together);
		 Stream.generate(Math::random).limit(5).forEach(System.out::println);//生成器函数
	 
	 
	}
	
 

}
