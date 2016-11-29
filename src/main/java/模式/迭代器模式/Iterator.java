package 模式.迭代器模式;

public interface Iterator {
     /**
      * 返回下一个对象
      * @return
      */
	 Object next();
	 /**
	  * 是否还有下一个
	  * @return
	  */
	 boolean harNext();

}
