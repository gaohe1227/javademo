package 模式.visitor模式;
/**
 * 
 * 访问者接口
 *
 * @author <a href="mailto:904724283@qq.com">gaohe</a>
 * @version 2016年12月2日
 */
public interface Visitor {
	 public void visit(City city);  
	    public void visit(Museum museum);  
	    public void visit(Park park);  
	}  
 
