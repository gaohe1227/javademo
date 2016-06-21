package model;

import 注解.Agevalidator;

public class Person extends Father{
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
      @Agevalidator(min=18,max=100)
	public void setAge(Integer age) {
		this.age = age;
	}
	public void say(String name, Integer age) {
		this.name = name;
		this.age = age;
		System.out.println("����:"+name+";����:"+age);
	}
	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	private void say(){
		System.out.println("---------------------------------------�ط�");
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
}
