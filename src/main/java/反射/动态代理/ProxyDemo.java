 package 反射.动态代理;

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
 * @author 高鹤
 *
 * 作用:自定义用来获取动态代理对象的类
 *
 * 2015年11月4日
 */
public class ProxyDemo {
	/**
	 * 获取代理类
	 * 
	 * @param inter
	 *            :接口
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

		str += "package 模式.代理;" + rt 
		      +"import java.lang.reflect.Method;"+rt
		      +"import 模式.代理.MyInvocationHandler;"+rt
				+ "/**" 
				+ rt + "*" + rt + "* @author 高鹤"
				+ rt + "*" + rt + "* 作用:聚合方式实现静态代理" + rt + "*" + rt
				+ "* 2015年11月4日" + rt + "*/ " + rt

				+ "public class $Proxy0 implements " + inter.getName() + "{"+ rt  
				
                   +"  private MyInvocationHandler h;" + rt
			 

				 + "   public $Proxy0(MyInvocationHandler h) {" + rt
			     + "	  this.h = h;" + rt 
			     + "	}" + rt
			   
			     
           +methodStr+rt
				+ "}" ;
		/**
		 * 生成代理类的java文件
		 */
		String fileName = System.getProperty("user.dir")
				+ "/bin/模式/代理/$Proxy0.java";
		System.out.println(fileName);
		File file = new File(fileName);
		FileUtils.writeStringToFile(file, str);// 生成带有指定数据的文件
		/**
		 * 编译
		 */
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();// 获取当前系统提供的java编译器
		StandardJavaFileManager fileManager=javaCompiler.getStandardFileManager(null, null, null);//生成文件管理者
		Iterable units= fileManager.getJavaFileObjects(fileName);//获取文件
		CompilationTask task=  javaCompiler.getTask(null, fileManager, null, null, null, units);//生成编译任务
        task.call();//进行编译\
        fileManager.close();
          ClassLoader cl=ClassLoader.getSystemClassLoader();//获取类加载器
        Class c=  cl.loadClass("模式.代理.$Proxy0");//将类加载到内存
        System.out.println(c.getName());
        Constructor constructor=c.getConstructor(MyInvocationHandler.class);
       return constructor.newInstance(h);
  

	}
}