package 模式.观察者.内置的观察者模式;

import java.util.Observable;
import java.util.Observer;
/**
 * 
 * 主题
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public class WatchedCounter extends Observable {

	public WatchedCounter() {
		// TODO Auto-generated constructor stub
	}
	 public void countdown(int number)
	    {
	        for (; number >= 0; --number)
	        {
	            // 设置改变变量
	            setChanged();

	            // 通知所有观察者，将number作为参数信息传递给观察者
	            notifyObservers(number);

	        }

	    }
	 public static void main(String[] args)
	    {
	        WatchedCounter watchedCounter = new WatchedCounter();
	        Watcher1 watcher1 = new Watcher1();
	        Watcher2 watcher2 = new Watcher2();

	        //添加观察者
	        watchedCounter.addObserver(watcher1);
	        watchedCounter.addObserver(watcher2);

	        //开始倒数计数
	        watchedCounter.countdown(10);
	    }
}
class Watcher1 implements Observer
{
    @Override
    public void update(Observable arg0, Object arg1)
    {
        System.out.println("Watcher1's number: " + arg1);

    }
}

class Watcher2 implements Observer
{
    @Override
    public void update(Observable arg0, Object arg1)
    {

        if (((Integer) arg1).intValue() <= 5)
        {
            System.out.println("Watcher2's number: " + arg1);
        }
    }
}

