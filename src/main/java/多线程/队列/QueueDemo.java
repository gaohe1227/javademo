package 多线程.队列;

/**
 * 网上抄的一个队列实现(队列：先进先出)
 * 
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年11月29日
 */
public class QueueDemo {
	private int maxSize; // 队列长度，由构造函数初始化

	private long[] queArray; // 队列

	private int front; // 队头

	private int rear; // 队尾

	private int nItems; // 元素的个数

	public QueueDemo(int s) // 构造函数
	{
		maxSize = s;
		queArray = new long[maxSize];
		front = 0;
		rear = -1;
		nItems = 0;
	}

	/**
	 * 进队
	 * @param j
	 */
	public void insert(long j) // 进队列
	{
		if (rear == maxSize - 1) // 处理循环
			rear = -1;
		queArray[++rear] = j; // 队尾指针加1,把值j加入队尾
		nItems++;
	}

	public long remove() // 取得队列的队头元素。
	{
		long temp = queArray[front++]; // 取值和修改队头指针
		if (front == maxSize) // 处理循环
			front = 0;
		nItems--;
		return temp;
	}

	public long peekFront() // 取得队列的队头元素。该运算与 remove()不同，后者要修改队头元素指针。
	{
		return queArray[front];
	}

	public boolean isEmpty() // 判队列是否为空。若为空返回一个真值，否则返回一个假值。
	{
		return (nItems == 0);
	}

	public boolean isFull() // 判队列是否已满。若已满返回一个真值，否则返回一个假值。
	{
		return (nItems == maxSize);
	}

	// --------------------------------------------------------------
	public int size() // 返回队列的长度
	{
		return nItems;
	}

	// ----------------------
	/**
	 * 
	 */
	public QueueDemo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QueueDemo theQueue = new QueueDemo(5); // 队列有5个元素

		theQueue.insert(10); // 添加4个元素
		theQueue.insert(20);
		theQueue.insert(30);
		theQueue.insert(40);

		theQueue.remove(); // 移除3个元素
		theQueue.remove(); // (10, 20, 30)
		theQueue.remove();

		theQueue.insert(50); // 添加4个元素
		theQueue.insert(60);
		theQueue.insert(70);
		theQueue.insert(80);

		while (!theQueue.isEmpty()) // 遍历队列并移除所有元素
		{
			long n = theQueue.remove(); // (40, 50, 60, 70, 80)
			System.out.print(n);
			System.out.print(" ");
		}
		System.out.println("");
	}

}
