package ģʽ.����ģʽ;
/**\
 * 
 * @author �ߺ�
 *
 * ����:����ģʽʾ��
 *
 * 2015��11��3��
 */
public class Student {
	private static Student stu=new Student();
	private Student(){
		
	};
	public static Student getInstance(){
		return stu;
		
	}
 
}
