package jdk8;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 注意:Predicate的test方法进行逻辑判断,并返回一个布尔值;Consumer的accept方法接收并改变某个对象的内部值值
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月25日
 */
public class PreidcateConsumerDemo {
	/**
	 * 
	 * @param student
	 * @param predicate
	 * @param consumer
	 * @return
	 */
     public static Student updateStudentFree(Student student, Predicate<Student> predicate, Consumer<Student> consumer){
    	 if(predicate.test(student)){
    		 consumer.accept(student);
    	 }
		return student;
    	 
     }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         Student student=new Student("Ashok","Kumar", 9.5);
         student=updateStudentFree(student, student1->student1.grade>10, student1->student1.feeDiscount=30.0);
         student.printFee();
	}

}
class Student{

    String firstName;

    String lastName;

    Double grade;

    Double feeDiscount = 0.0;

    Double baseFee = 20000.0;

    public Student(String firstName, String lastName, Double grade) {

        this.firstName = firstName;

        this.lastName = lastName;

        this.grade = grade;
    }

    public void printFee(){

        Double newFee = baseFee - ((baseFee * feeDiscount) / 100);

        System.out.println("The fee after discount: " + newFee);

    }

}