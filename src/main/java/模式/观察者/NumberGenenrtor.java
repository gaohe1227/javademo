package 模式.观察者;

import java.util.Iterator;
import java.util.Vector;

/**
 * 产生数值的抽象类
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public abstract class NumberGenenrtor {
	private Vector observers=new Vector();//存储Observer
	public void addObservers(NumberObserver numberObserver){//新增NumberObserver
		observers.add(numberObserver);
		notifyAllNumberObserver();
	}
	public void deleteObservers(NumberObserver numberObserver){//删除NumberObserver
		observers.remove(numberObserver);
		notifyAllNumberObserver();
	}
	public void notifyAllNumberObserver(){//通知NumberObserver
		Iterator iterator=observers.iterator();
		while (iterator.hasNext()) {
			NumberObserver numberObserver = (NumberObserver) iterator.next();
			numberObserver.update(this);
		}
		
	}
	public abstract int getNumber();//取得数值
	public abstract void  createNumber();//产生数值
	
}
