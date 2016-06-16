package 模式.单例模式;
/**
 * 
 * @author 高鹤
 *
 * 作用:懒汉模式示例
 *
 * 2015年11月3日
 */
public class Teacher {
	private Teacher(){
		
	}
	private static Teacher teacher;
	
	public static Teacher getInstance(){
		if(null==teacher){
			return teacher=new Teacher();
		}
		return teacher;
		
	}

}
