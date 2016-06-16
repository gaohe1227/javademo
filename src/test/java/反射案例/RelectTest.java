package ���䰸��;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.junit.Test;
import org.omg.Messaging.SyncScopeHelper;

import model.Person;
import ����.ReflectUtil;
import ����.��̬����.MyInvocationHandler;
import ����.��̬����.RealSubject;
import ����.��̬����.Subject;
import ע��.Agevalidator;

/**
 * 
 * @author �ߺ�
 *
 *         2016��6��13��
 *
 *         ����:�������
 */
public class RelectTest {
	@Test
	public void testDynamicProxy() {
		System.out.println("-------------");
		MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
		Subject subject = (Subject) myInvocationHandler.bind(new RealSubject());
		String info = subject.say("���˻�", 30);
		System.out.println(info);
	}

	@Test
	public void testClass() {
		try {
			Class clazz = null;
			clazz = Person.class;
			System.out.println(clazz.getName() + clazz.newInstance());

			Method[] methods = clazz.getMethods();// ��ȡ����
			for (Method method : methods) {
				System.out.println(method.getModifiers() + "-------------" + method.getName() + "---"
						+ method.getParameterTypes());
			}
			Method m = clazz.getMethod("setName", String.class);
			;// ��ȡָ������
			Object obj = clazz.newInstance();
			System.out.println("ִ�з���");
			Object o = m.invoke(obj, "����");
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
			System.out.println(clazz.getName() + "------------" + clazz.newInstance());// ���Class��������ƺ�Class��������������һ���¶���
			System.out.println(Class.forName("model.Person").getClassLoader());// ����������
			InputStream in = RelectTest.class.getClassLoader().getResourceAsStream("testclassLoader.properties");// ���������ȡ������
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
			reflectUtil.invoke(obj, "say", "����", 12);
			reflectUtil.invoke("model.Person", "say", "����1", 121);
			/* System.out.println(reflectUtil.invoke(obj,"say")); */

			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��������
	 */
	@Test
	public void testField() {
		try {
			Class clazz = Class.forName("model.Person");
			Field[] fields = clazz.getDeclaredFields();// ��ȡ�����б�
			for (Field field : fields) {
				System.out.println(field.getName());
			}
			Field f = clazz.getDeclaredField("name");
			System.out.println(f.getName());
			Person p = new Person("����", 12);
			Field field = clazz.getDeclaredField("name");// ����ָ������
			field.setAccessible(true);
			System.out.println(field.get(p));// �������ֵ
			field.set(p, "����");
			System.out.println(field.get(p));// �������ֵ
		} catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException
				| IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ���Թ�����
	 */
	@Test
	public void testConstructor(){
		try {
			Class clazz = Class.forName("model.Person");
			Constructor<Person>[] constructors=	(Constructor<Person>[])clazz.getConstructors();//��ȡ�������б�
			for (Constructor<Person> constructor : constructors) {
				System.out.println(constructor);
			}
			Constructor<Person> constructor=clazz.getConstructor(String.class,Integer.class);//��ȡָ���������͵Ĺ�����
			System.out.println(constructor);
			Person p=	constructor.newInstance("���",12);//����������ʵ��
			System.out.println(p.getName());
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * ����ע��
	 */
	@Test
	public void testAnnocation(){
		try {
			Class clazz = Class.forName("model.Person");
			Person p=(Person) clazz.newInstance();
			Method m=clazz.getMethod("setAge", Integer.class);
			Annotation a=	m.getAnnotation(Agevalidator.class);//��ȡע��ʵ��
		 
			int val=120;
			if(a!=null){
				System.out.println(a.getClass().getName());
				if(a instanceof Agevalidator){
					Agevalidator agevalidator=(Agevalidator)a;
					System.out.println(agevalidator.max()+"---"); 
					if(val>agevalidator.max()||val<agevalidator.min()){
						throw new RuntimeException("���ֹ�����С");
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
