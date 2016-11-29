package 模式.迭代器模式;

public class Node {

	/**
	 * 链表数据本身
	 */
	private Object data;

	/**
	 * 指向链表下一个元素
	 */
	private Node next;

	public Node(Object data, Node next) {
		super();
		this.data = data;
		this.next = next;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

}
