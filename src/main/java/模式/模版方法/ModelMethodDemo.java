package 模式.模版方法;

/**
 * 
 * 模版方法: 定义一个操作中的算法的骨架，而将步骤延迟到子类中。模板方法使得子类可以不改变一个算法的结构即可重定义算法的某些特定步骤 . 模式中的角色:
 * (1) 抽象类（AbstractClass）：实现了模板方法，定义了算法的骨架。 (2)
 * 具体类（ConcreteClass)：实现抽象类中的抽象方法，已完成完整的算法。
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月28日
 */
public class ModelMethodDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractDisplay d1 = new CharDisplay('h');
		AbstractDisplay d2 = new StringDisplay("Hello World");
		d1.display();
		d2.display();
	}

}

abstract class AbstractDisplay {
	public abstract void open();

	public abstract void print();

	public abstract void close();

	public final void display() {
		open();
		System.out.println(this.getClass().getName());
		for (int i = 0; i < 10; i++) {
			print();
		}
		close();
	}
}

class CharDisplay extends AbstractDisplay {
	private char ch;

	public CharDisplay(char ch) {
		super();
		this.ch = ch;
	}

	public char getCh() {
		return ch;
	}

	public void setCh(char ch) {
		this.ch = ch;
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		System.out.print("<<");
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.print(ch);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.print(">>");
		System.out.println();
	}

}

class StringDisplay extends AbstractDisplay {
	private String string;

	private int width;

	public StringDisplay(String string) {
		super();
		this.string = string;
		this.width = string.getBytes().length;
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		printLine();
	}

	private void printLine() {
		// TODO Auto-generated method stub
		System.out.print("+");
		for (int i = 0; i < width; i++) {
			System.out.print("-");
		}
		System.out.println("+");
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("|" + string + "|");
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		printLine();
	}

}