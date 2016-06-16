package 模式.单例模式;
/**\
 * 
 * @author 高鹤
 *
 * 作用:饿汉模式示例
 *
 * 2015年11月3日
 */
public class Student {
	private static Student stu=new Student();
	private Student(){
		
	};
	public static Student getInstance(){
		return stu;
		
	}
 
}
