package �㷨.����;
/**
 * 
 * @author �ߺ�
 *
 * 2016��2��23��
 *
 * ����:Comparable����
 */
public class User implements Comparable<User>{
	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub
		return this.getAge()-o.getAge();
	}

}
