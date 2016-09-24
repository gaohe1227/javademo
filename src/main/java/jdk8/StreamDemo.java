package jdk8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
		List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());// collect操作collect(toList())
		System.out.println(collected);
		List<String> maped = Stream.of("a", "b", "Hello").map(string -> string.toUpperCase()).collect(Collectors.toList());// map操作
		System.out.println(maped);
		List<String> filterList = Stream.of("a", "1abc", "abc1").filter(value -> value.contains("b")).collect(Collectors.toList());// filter操作
		System.out.println(filterList);
		List<List<Integer>> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4)).flatMap(numbers -> Stream.of(numbers)).collect(Collectors.toList());// flatMap操作
		System.out.println(together);
		Stream.generate(Math::random).limit(5).forEach(System.out::println);// 生成器函数
		System.err.println("数值构造");
		IntStream.of(new int[] { 1, 23, 45, 12 }).forEach(System.out::print);
		IntStream.range(1, 3).forEach(System.out::println);
		IntStream.rangeClosed(1, 3).forEach(System.out::println);
		IntStream.rangeClosed(1, 3).forEach(i -> System.out.println(i));
		System.err.println("输出平方数");
		List<Integer> nums = Arrays.asList(1, 2, 3, 4);
		List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());// map操作:转换元素规则，生成新的Stream
		squareNums.stream().forEach(System.out::print);// forEach 方法接收一个 Lambda表达式，然后在 Stream 的每一个元素上执行该表达式;forEach不能修改自己包含的本地变量值，也不能用 break/return之类的关键字提前结束循环
		System.err.println("一对多");
		Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));

		Stream<Integer> outputStream = inputStream.flatMap((childrenList) -> childrenList.stream());// flatMap操作
		outputStream.map(n -> n).forEach(System.out::println);
		System.err.println("留下偶数");
		Integer[] sixNums = { 1, 2, 3, 4, 5, 6 };
		Integer[] evens = Stream.of(sixNums).filter(n -> n % 2 == 0).toArray(Integer[]::new);// filter操作:filter对原始Stream进行某项测试，通过测试的元素被留下来生成一个新Stream
		System.out.println(evens);
		System.err.println("optional案例:");
		optionalDemo();
		System.err.println("reduce案例:");
		reduceDemo();
		System.err.println("limit、skip");
		testLimitAndSkip();
		System.err.println("min/max/distinct");
		min_max_distinct();
		System.err.println("match案例");
		matchDemo();
		System.err.println("自己生成流");
		ownDemo();
		ownDemo1();

	}
	
	/**
	 * reduce案例
	 */
	static void reduceDemo(){
		// 字符串连接，concat = "ABCD"
		String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
		System.err.println(concat);
		// 求最小值，minValue = -3.0
		double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
		System.err.println(minValue);
		// 求和，sumValue = 10, 有起始值
		int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
		System.err.println(sumValue);
		// 求和，sumValue = 10, 无起始值
		sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
		System.err.println(sumValue);
		// 过滤，字符串连接，concat = "ace"
		concat = Stream.of("a", "B", "c", "D", "e", "F").filter(x -> x.compareTo("Z") > 0).reduce("", String::concat);
		System.err.println(concat);
	
	}
	
     /**
      * optional案例
      */
	static void optionalDemo() {
		String strA = " abcd ", strB = null;
		print(strA);
		print("");
		print(strB);
		System.out.println(getLength(strA)); 
		System.out.println(getLength("")); 
		System.out.println(getLength(strB)); 
	}

	public static void print(String text) {
		Optional.ofNullable(text).ifPresent(System.out::println);
		// Pre-Java 8
		/*
		 * if (text != null) { System.out.println(text); }
		 */
	}

	public static int getLength(String text) {
		// Java 8
		return Optional.ofNullable(text).map(String::length).orElse(-1);
		// Pre-Java 8
		// return if (text != null) ? text.length() : -1;
	};

	/**
	 * limit、skip案例
	 */
	public static void testLimitAndSkip() {
		List<Person> persons = new ArrayList();
		for (int i = 1; i <= 10000; i++) {
			Person person = new Person(i, "name" + i);
			persons.add(person);
		}
		List<String> personList = persons.stream().map(person -> person.getName()).limit(10).skip(3).collect(Collectors.toList());
		System.out.println(personList);

	}
	/**
	 * min/max/distinct
	 */
	public static void min_max_distinct() {
		BufferedReader br =null;
		try {
			  br = new BufferedReader(new FileReader("e:\\error.log"));
		/*	  IntStream in=  br.lines().mapToInt(String::length);
			int longest = in.max().getAsInt(); 
			br.close();
			System.out.println("max:找出最长一行的长度"+longest);*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> words = br.lines().flatMap(line -> Stream.of(line.split(" "))).filter(word -> word.length() > 0). map(String::toLowerCase).
				 distinct().sorted().collect(Collectors.toList());
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("distinct:找出全文的单词，转小写，并排序"+words);
			
	}
	/**
	 * match案例
	 */
	public static void matchDemo(){
		List<Person> persons = new ArrayList();
		persons.add(new Person(1, "name" + 1, 10));
		persons.add(new Person(2, "name" + 2, 21));
		persons.add(new Person(3, "name" + 3, 34));
		persons.add(new Person(4, "name" + 4, 6));
		persons.add(new Person(5, "name" + 5, 55));
		boolean isAllAdult = persons.stream().
		 allMatch(p -> p.getAge() > 18);
		System.out.println("All are adult? " + isAllAdult);
		boolean isThereAnyChild = persons.stream().
		 anyMatch(p -> p.getAge() < 12);
		System.out.println("Any child? " + isThereAnyChild);
	}
	/**
	 * 自己生成流
	 */
	public static void ownDemo(){
		Random seed = new Random();
		Supplier<Integer> random = seed::nextInt;
		Stream.generate(random).limit(10).forEach(i->System.out.print(i+"--"));//生成流
		//Another way
		IntStream.generate(() -> (int) (System.nanoTime() % 100)).
		limit(10).forEach(System.out::println);
	}
	public static void ownDemo1(){
		Stream.generate(new PersonSupplier()).
		limit(10).
		forEach(p -> System.out.println(p.getName() + ", " + p.getAge()));
	}
	

}
 class PersonSupplier implements Supplier<Person> {
	 private int index = 0;
	 private Random random = new Random();
	 @Override
	 public Person get() {
	 return new Person(index++, "StormTestUser" + index, random.nextInt(100));
	 }
	}
class Person {
	public int no;
	private String name;
    private int age;
	public int getAge() {
		return age;
	}

	public Person(int no, String name, int age) {
		super();
		this.no = no;
		this.name = name;
		this.age = age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(int no, String name) {
		this.no = no;
		this.name = name;
	}

	public String getName() {
		System.out.println(name);
		return name;
	}
}
