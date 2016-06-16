package ����.��̬����;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;
/**
 * 
 * @author �ߺ�
 *
 * ����:�Զ���������ȡ��̬����������
 *
 * 2015��11��4��
 */
public class ProxyDemo {
	/**
	 * ��ȡ������
	 * 
	 * @param inter
	 *            :�ӿ�
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static Object newProxyInstance(Class inter,MyInvocationHandler h) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String rt = "\r\n";
		String str = "";
		String methodStr = "";
		if (null != inter.getMethods() && inter.getMethods().length > 0) {
			for (Method method : inter.getMethods()) {
				methodStr += "	@Override"+ rt
						  +"	public void "+ method.getName()+ "() {"	+ rt
						  +"    // TODO Auto-generated method stub"+ rt
						  +"     try{"+rt
						  +"    Method md="+inter.getName()+".class.getMethod(\""+method.getName()+"\");"+rt
						  +"      h.invoke(this,md);"+rt 
						  +"   }catch(Exception e){"+rt 
						  +"   e.printStackTrace();"+rt 
						  + " }" + rt
				+ " }" + rt;
			   }

		}

		str += "package ģʽ.����;" + rt 
		      +"import java.lang.reflect.Method;"+rt
		      +"import ģʽ.����.MyInvocationHandler;"+rt
				+ "/**" 
				+ rt + "*" + rt + "* @author �ߺ�"
				+ rt + "*" + rt + "* ����:�ۺϷ�ʽʵ�־�̬����" + rt + "*" + rt
				+ "* 2015��11��4��" + rt + "*/ " + rt

				+ "public class $Proxy0 implements " + inter.getName() + "{"+ rt  
				
                   +"  private MyInvocationHandler h;" + rt
			 

				 + "   public $Proxy0(MyInvocationHandler h) {" + rt
			     + "	  this.h = h;" + rt 
			     + "	}" + rt
			   
			     
           +methodStr+rt
				+ "}" ;
		/**
		 * ���ɴ������java�ļ�
		 */
		String fileName = System.getProperty("user.dir")
				+ "/bin/ģʽ/����/$Proxy0.java";
		System.out.println(fileName);
		File file = new File(fileName);
		FileUtils.writeStringToFile(file, str);// ���ɴ���ָ�����ݵ��ļ�
		/**
		 * ����
		 */
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();// ��ȡ��ǰϵͳ�ṩ��java������
		StandardJavaFileManager fileManager=javaCompiler.getStandardFileManager(null, null, null);//�����ļ�������
		Iterable units= fileManager.getJavaFileObjects(fileName);//��ȡ�ļ�
		CompilationTask task=  javaCompiler.getTask(null, fileManager, null, null, null, units);//���ɱ�������
        task.call();//���б���\
        fileManager.close();
          ClassLoader cl=ClassLoader.getSystemClassLoader();//��ȡ�������
        Class c=  cl.loadClass("ģʽ.����.$Proxy0");//������ص��ڴ�
        System.out.println(c.getName());
        Constructor constructor=c.getConstructor(MyInvocationHandler.class);
       return constructor.newInstance(h);
  

	}
}
