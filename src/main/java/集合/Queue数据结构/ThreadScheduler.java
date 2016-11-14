package 集合.Queue数据结构;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ThreadScheduler {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			list.add((int) (Math.random() * 10));
		}
		PriorityQueue<Integer> threaQueue = new PriorityQueue<>();
		threaQueue.addAll(list);
		System.out.println("----------等待线程");
		for (Integer integer : threaQueue) {
			System.out.print(integer + ",");
		}
		System.out.println("----------等待线程");
		while (!threaQueue.isEmpty()) {
			System.out.print(threaQueue.remove() + ",");
		}
	}
}
