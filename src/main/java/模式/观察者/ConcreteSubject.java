package ģʽ.�۲���;

/**
 * 
 * @author �ߺ�
 *
 * ����:����Ŀ��������
 *
 * 2015��11��5��
 */
public class ConcreteSubject extends Subject {
	private String subjectstate;// Ŀ������״̬

	public String getSubjectstate() {
		return subjectstate;
	}

	public void setSubjectstate(String subjectstate) {
		this.subjectstate = subjectstate;
		this.notifytach();
	}

}
