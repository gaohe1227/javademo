package jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sound.midi.Track;

/**
 * 
 * @author 高鹤
 *
 * 2016年7月29日
 *
 * 作用:Lambda表达式案例
 */
public class Lambda表达式 {
	public static void main(String args[]){
		Lambda表达式 tester = new Lambda表达式();

	      //with type declaration
	      MathOperation addition = (int a, int b) -> a + b;//定义接口的具体实现

	      //with out type declaration
	      MathOperation subtraction = (a, b) -> a - b;

	      //with return statement along with curly braces
	      MathOperation multiplication = (int a, int b) -> { return a * b; };
	      //without return statement and without curly braces
	      MathOperation division = (int a, int b) -> a / b;

	      System.out.println("10 + 5 = " + tester.operate(10, 5, addition));	   
	      System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
	      System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
	      System.out.println("10 / 5 = " + tester.operate(10, 5, division));

	      //with parenthesis
	      GreetingService greetService1 = message -> System.out.println("Hello " + message);

	      //without parenthesis
	      GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

	      greetService1.sayMessage("Mahesh");
	      greetService2.sayMessage("Suresh");	   
	      List<Integer> list=new ArrayList<Integer>();
	      for(int i=0;i<10;i++){
	    	  list.add(new Random().nextInt(100));
	      }
	      list.forEach(o->System.out.println(o));//Lambda表达式:forEach函数实现内部迭代
	      System.out.println("---------------------------------------------------------");
	      tester.distinctPrimary("12","34","12");
	      
	      T1 t1 =(a,b)->a+b;
	      List<Integer> list1=new ArrayList<Integer>();
	      for(int i=0;i<10;i++){
	    	  list1.add(new Random().nextInt(100));
	      }
	       
	      MyNumber mynumber=(Stream<Integer> number)->number.reduce(0, (acc,element)->acc+element);
	      System.out.println("和:"+mynumber.addUp(list1.stream()));
	      OptionalDemo();
	      System.out.println("---------------------------------------------------------"); 
	      
	      
	      
	   }   

	   interface MathOperation {
	      int operation(int a, int b);
	   }  

	   interface GreetingService {
	      void sayMessage(String message);
	   }

	   private int operate(int a, int b, MathOperation mathOperation){
	      return mathOperation.operation(a, b);
	   } 
	   /**
	    * 处理集合(内部迭代)
	    * @param numbers
	    */
	   public void distinctPrimary(String... numbers) {
	        List<String> l = Arrays.asList(numbers);
	        System.out.println(l);
 
	       /* List<Integer> r = l.stream()
	                .map(e -> new Integer(e))
	                .filter(e -> Primes.isPrime(e))
	                .distinct()
	                .collect(Collectors.toList());*/
	  /*      for(int i=0;i<100;i++){
	        	l.add(String.valueOf(i));
	        }*/
	  
	       
	        List<String> r=l.stream().filter(s->{
	        	System.out.println(s);
	        return s.equals("12");		
	        }).collect(Collectors.toList());
	      
	        System.out.println("distinctPrimary result is: " + r);
	    }
	   /**
	    * Optional案例
	    */
	   private static void OptionalDemo(){
		   Optional<String> a=Optional.of("a");
		   System.out.println("a".equals(a.get()));
		   Optional emptyOptional=Optional.empty();
		   Optional alsoEmpty=Optional.ofNullable(null);
		   System.out.println(emptyOptional.isPresent()+"----"+alsoEmpty.isPresent()+"---"+alsoEmpty.orElse(null));
	   }
}
interface T1{
	int a(int a,int b); 
}
interface MyNumber{
	int addUp(Stream<Integer> numbers);
}