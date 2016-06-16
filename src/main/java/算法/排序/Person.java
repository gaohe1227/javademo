package �㷨.����;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author �ߺ�
 *
 *         2016��2��23��
 *
 *         ����:Comparator����
 */
public class Person {
	String name;

	Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

public static void main(String[] args) {
	  List<Person> pList=new ArrayList<>(); 
	  Person p1=new Person("keke", 30);
	  pList.add(p1);
	  Person p2=new Person("xifei", 21);
	  pList.add(p2);
	  Person p3=new Person("�ػ���", 12);
	  pList.add(p3);
	  Person p4=new Person("���", 23);
	  pList.add(p4);
	  for (Person person : pList) {
		System.out.print(person);
	}
	 System.out.println(); 
	  Collections.sort(pList,new Comparator<Person>() {

		@Override
		public int compare(Person o1, Person o2) {
			// TODO Auto-generated method stub
			return o1.getAge()-o2.getAge();
		}
	});
	  for (Person person : pList) {
			System.out.print(person);
		}
		  
}
}
