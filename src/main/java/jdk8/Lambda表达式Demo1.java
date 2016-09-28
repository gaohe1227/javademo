package jdk8;

 
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.omg.PortableInterceptor.INACTIVE;

public class Lambda表达式Demo1 {
	public static void main(String[] args) {
		// Runnable runnable=()->System.out.println(Thread.currentThread().getName()+"----->"+new Date());
		Runnable runnable = () -> {
			for (int i = 0; i < 100; i++) {
				System.out.println(Thread.currentThread().getName() + "----->"+ i);
			}

		};

		new Thread(runnable, "test1").start();
		new Thread(runnable, "test2").start();
		new ArrayList<Person>().stream().filter(i -> i.getName().equals("hg")).collect(Collectors.toList());
		 List<String> list=Stream.of("a","b1sdd","c,Ac","d").map(i->i.toUpperCase()).collect(Collectors.toList());
		 for (int j = 0; j < list.size(); j++) {
			System.err.println(list.get(j));
		}
		 int count = Stream.of(1, 2, 3) .reduce(0, (acc, element) -> acc + element);
		 ArrayList<Person1> personList= new  ArrayList<Person1>();
		 for (int j = 0; j < list.size(); j++) {
			 personList.add	(new Person1(j, "测试"+j,new Random().nextInt(100)));
			}
		 System.out.println("size:---"+personList.size()); 
		Optional<Integer> age= personList.stream().map(i->i.getAge()).max(Comparator.comparing(Person1::getMinAge));
		System.out.println(age.get());
	}
}
class Person1  {
	public int no;
	private String name;
    private int age;
	public int getAge() {
		return age;
	}
	public static int  getMinAge(int age1){
		return age1;
	}
	public Person1(int no, String name, int age) {
		super();
		this.no = no;
		this.name = name;
		this.age = age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person1(int no, String name) {
		this.no = no;
		this.name = name;
	}

	public String getName() {
		System.out.println(name);
		return name;
	}

	 
}
class StringExercises {

    // Question 7
    public static int countLowercaseLetters(String string) {
        return (int) string.chars()
                           .filter(Character::isLowerCase)
                           .count();
    }

    // Question 8
    public static Optional<String> mostLowercaseString(List<String> strings) {
        return strings.stream()
                      .max(Comparator.comparing(StringExercises::countLowercaseLetters));
    }

}
