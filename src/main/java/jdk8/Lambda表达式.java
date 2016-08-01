package jdk8;
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
}
