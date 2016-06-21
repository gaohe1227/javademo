 package 模式.观察者;
/**
 * 
 * @author 高鹤
 *
 * 作用:这是一个观察者的接口,定义一个更新的接口给那些在目标发生改变的时候被通知的对象
 *
 * 2015年11月5日
 */
public interface Observer {
   /**
    * 
    * @param subject:传入的目标对象，有助于获取目标对象的状态
    */
	void update(Subject subject);

}