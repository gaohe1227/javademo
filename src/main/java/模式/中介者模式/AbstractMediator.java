package 模式.中介者模式;

import java.util.Hashtable;

/**
 *中介者抽象类 
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public abstract class AbstractMediator {
	 //中介者肯定需要保持有若干同事的联系方式  
    protected Hashtable<String, AbstractColleague> colleagues = new Hashtable<String, AbstractColleague>();  
      
    //中介者可以动态地与某个同事建立联系  
    public void addColleague(String name, AbstractColleague c) {  
        this.colleagues.put(name, c);  
    }     
      
    //中介者也可以动态地撤销与某个同事的联系  
    public void deleteColleague(String name) {  
        this.colleagues.remove(name);  
    }  
      
    //中介者必须具备在同事之间处理逻辑、分配任务、促进交流的操作  
    public abstract void execute(String name, String method); 
}
//具体中介者  
class Mediator extends AbstractMediator{  
      
    //中介者最重要的功能，来回奔波与各个同事之间  
    public void execute(String name, String method) {  
          
        if("self".equals(method)){  //各自做好分内事  
            if("ColleagueA".equals(name)) {  
                ColleagueA colleague = (ColleagueA)super.colleagues.get("ColleagueA");  
                colleague.self();  
            }else {  
                ColleagueB colleague = (ColleagueB)super.colleagues.get("ColleagueB");  
                colleague.self();  
            }  
        }else { //与其他同事合作  
            if("ColleagueA".equals(name)) {  
                ColleagueA colleague = (ColleagueA)super.colleagues.get("ColleagueA");  
                colleague.out();  
            }else {  
                ColleagueB colleague = (ColleagueB)super.colleagues.get("ColleagueB");  
                colleague.out();  
            }  
        }  
    }  
} 