 package 反射案例;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.junit.Test;
import model.Person;
import 反射.ReflectUtil;
import 反射.动态代理.MyInvocationHandler;
import 反射.动态代理.RealSubject;
import 反射.动态代理.Subject;
import 注解.Agevalidator;

/**
 * 
 * @author 高鹤
 *
 *         2016年6月13日
 *
 *         作用:反射测试
 */
public class RelectTest {
	@Test
	public void testDynamicProxy() {
		System.out.println("-------------");
		MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
		Subject subject = (Subject) myInvocationHandler.bind(new RealSubject());
		String info = subject.say("李兴华", 30);
		System.out.println(info);
	}

	@Test
	public void testClass() {
		try {
			Class clazz = null;
			clazz = Person.class;
			System.out.println(clazz.getName() + clazz.newInstance());

			Method[] methods = clazz.getMethods();// 获取方法
			for (Method method : methods) {
				System.out.println(method.getModifiers() + "-------------" + method.getName() + "---"
						+ method.getParameterTypes());
			}
			Method m = clazz.getMethod("setName", String.class);
			;// 获取指定方法
			Object obj = clazz.newInstance();
			System.out.println("执行方法");
			Object o = m.invoke(obj, "名称");
			/* System.out.println(((Person)obj).getName()); */

			clazz = Class.forName("model.Person");
			try {
				System.out.println(clazz.getName() + "---" + clazz.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Person person = new Person();
			clazz = person.getClass();
			System.out.println(clazz.getName() + "------------" + clazz.newInstance());// 输出Class对象的名称和Class对象所代表的类的一个新对象
			System.out.println(Class.forName("model.Person").getClassLoader());// 输出类加载器
			InputStream in = RelectTest.class.getClassLoader().getResourceAsStream("testclassLoader.properties");// 类加载器获取输入流
			Properties p = new Properties();
			p.load(in);
			System.out.println(p.getProperty("test1"));
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReflectUtilInvoke() {
		ReflectUtil reflectUtil = new ReflectUtil();
		try {

			Class clazz = Class.forName("model.Person");

			Object obj = clazz.newInstance();
			reflectUtil.invoke(obj, "say", "测试", 12);
			reflectUtil.invoke("model.Person", "say", "测试1", 121);
			/* System.out.println(reflectUtil.invoke(obj,"say")); */

			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试属性
	 */
	@Test
	public void testField() {
		try {
			Class clazz = Class.forName("model.Person");
			Field[] fields = clazz.getDeclaredFields();// 获取属性列表
			for (Field field : fields) {
				System.out.println(field.getName());
			}
			Field f = clazz.getDeclaredField("name");
			System.out.println(f.getName());
			Person p = new Person("测试", 12);
			Field field = clazz.getDeclaredField("name");// 虎丘指定属性
			field.setAccessible(true);
			System.out.println(field.get(p));// 输出属性值
			field.set(p, "银魂");
			System.out.println(field.get(p));// 输出属性值
		} catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException
				| IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 测试构造器
	 */
	@Test
	public void testConstructor(){
		try {
			Class clazz = Class.forName("model.Person");
			Constructor<Person>[] constructors=	(Constructor<Person>[])clazz.getConstructors();//获取构造器列表
			for (Constructor<Person> constructor : constructors) {
				System.out.println(constructor);
			}
			Constructor<Person> constructor=clazz.getConstructor(String.class,Integer.class);//获取指定参数类型的构造器
			System.out.println(constructor);
			Person p=	constructor.newInstance("棋魂",12);//构造器创建实例
			System.out.println(p.getName());
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 测试注解
	 */
	@Test
	public void testAnnocation(){
		try {
			Class clazz = Class.forName("model.Person");
			Person p=(Person) clazz.newInstance();
			Method m=clazz.getMethod("setAge", Integer.class);
			Annotation a=	m.getAnnotation(Agevalidator.class);//获取注解实例
		 
			int val=120;
			if(a!=null){
				System.out.println(a.getClass().getName());
				if(a instanceof Agevalidator){
					Agevalidator agevalidator=(Agevalidator)a;
					System.out.println(agevalidator.max()+"---"); 
					if(val>agevalidator.max()||val<agevalidator.min()){
						throw new RuntimeException("数字过大或过小");
					}
				}
			}
			
			
			
			m.invoke(p, val);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}