package 集合.Set数据结构;

/**
 * 自定义比较器1(实现Comparable)
 * @author Administrator
 *
 */
public class Player implements Comparable<Player>{
	private String name;
	private int age;

	public Player(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
     /**
      * 自定义比较方法
      */
	@Override
	public int compareTo(Player other) {
		// TODO Auto-generated method stub
		System.out.println(this.name+"---"+other.getName()+"---"+(this.age-other.getAge()));
		return this.age-other.getAge();
	}

}

