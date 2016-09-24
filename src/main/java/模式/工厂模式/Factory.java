package 模式.工厂模式;
/**
 * 
 * @author 高鹤
 *
 * 2016年8月23日
 *
 * 作用：简单工厂模式
 */
public class Factory {
	 public BWM BWM(int type) {  
	        switch (type) {  
	          
	        case 320:  
	            return new  BMW320();  
	  
	        case 523:  
	            return new BMW523();  
	  
	        default:  
	            break;  
	        }  
	        return null;  
	    }  
}

abstract class BWM{
	public BWM(){
		
	}
	
}
class BMW320 extends BWM {  
    public BMW320() {  
        System.out.println("制造-->BMW320");  
    }  
}  
class BMW523  extends BWM {  
    public BMW523() {  
        System.out.println("制造-->BMW523");  
    }  
}  