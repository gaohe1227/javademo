package 模式.中介者模式;

/**
 * 
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public abstract class AbstractColleague {
	 protected AbstractMediator mediator;  
     
	    /**既然有中介者，那么每个具体同事必然要与中介者有联系，  
	     * 否则就没必要存在于 这个系统当中，这里的构造函数相当  
	     * 于向该系统中注册一个中介者，以取得联系  
	     */ 
	    public AbstractColleague(AbstractMediator mediator) {  
	        this.mediator = mediator;  
	    }  
	      
	    // 在抽象同事类中添加用于与中介者取得联系（即注册）的方法  
	    public void setMediator(AbstractMediator mediator) {  
	        this.mediator = mediator;  
	    }  

}

//具体同事A  
class ColleagueA extends AbstractColleague {  
    
  //每个具体同事都通过父类构造函数与中介者取得联系  
  public ColleagueA(AbstractMediator mediator) {  
      super(mediator);  
  }  
    
  //每个具体同事必然有自己分内的事，没必要与外界相关联  
  public void self() {  
      System.out.println("同事A --> 做好自己分内的事情 ...");  
  }  
    
  //每个具体同事总有需要与外界交互的操作，通过中介者来处理这些逻辑并安排工作  
  public void out() {  
      System.out.println("同事A --> 请求同事B做好分内工作 ...");  
      super.mediator.execute("ColleagueB", "self");  
  }  
}  

//具体同事B  
class ColleagueB extends AbstractColleague {  
    
  public ColleagueB(AbstractMediator mediator) {  
      super(mediator);  
  }  
    
  public void self() {  
      System.out.println("同事B --> 做好自己分内的事情 ...");  
  }  
    
  public void out() {  
      System.out.println("同事B --> 请求同事A做好分内工作  ...");  
      super.mediator.execute("ColleagueA", "self");  
  }  
} 
