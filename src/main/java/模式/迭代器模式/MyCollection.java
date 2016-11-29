package 模式.迭代器模式;

public interface MyCollection {
	/*
	 * 添加元素
	 * 
	 * @param o
	 */
	public void add(Object o);

	/**
	 * 返回大小
	 * @return
	 */
	public int size();

	/**
	 * 迭代方法
	 * @return
	 */
	public Iterator iterator();

}
