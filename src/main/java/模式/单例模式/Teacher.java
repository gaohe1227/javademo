package ģʽ.����ģʽ;
/**
 * 
 * @author �ߺ�
 *
 * ����:����ģʽʾ��
 *
 * 2015��11��3��
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
