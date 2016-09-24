package 模式.工厂模式;
/**
 * 
 * @author 高鹤
 *
 * 2016年8月23日
 *
 * 作用：工厂方法模式 
 */
public interface FactoryBMW {
	BMW createBMW();
}
abstract class BMW {  
    public BMW(){  
          
    }  
}  
  class BMW3201 extends BMW {  
    public BMW3201() {  
        System.out.println("制造-->BMW3201");  
    }  
}  
  class BMW5231 extends BMW{  
    public BMW5231(){  
        System.out.println("制造-->BMW5231");  
    }  
}  
    class FactoryBMW3201 implements FactoryBMW{  
	  
	    @Override  
	    public BMW3201 createBMW() {  
	  
	        return new BMW3201();  
	    }  
	  
	}  
	  class FactoryBMW5231 implements FactoryBMW {  
	    @Override  
	    public BMW5231 createBMW() {  
	  
	        return new BMW5231();  
	    }  
	}  