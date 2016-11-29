package 模式.迭代器模式;

import java.util.Arrays;

public class MyArrayList implements MyCollection {
	 /**
     * List成员
     */
    private Object[] elementData;
    
    /**
     * List长度
     */
    private int size;

    /**
     * 构造方法
     * @param elementData
     * @param size
     */
    public MyArrayList(int size) {
        super();
        this.elementData = new Object[size];
        this.size = 0;
    }
    
    public MyArrayList(){
        this(10); //默认创建容器长度为10
    }
    
    /**
     * 添加元素
     */
    public void add(Object o){
        if(this.size >= 10){
            int newSize = size * 3;
            elementData = Arrays.copyOf(elementData, newSize);
        }
        elementData[size] = o;
        size++;
    }
    
    /**
     * 返回容器长度
     * @return
     */
    public int size(){
        return size;
    }
    
    public Object get(int size){
        return elementData[size];
    }
    
    public Iterator iterator(){
        return new ArrayListIterator();
    }
    
    /**
     * 迭代器的内部类实现
     */
    private class ArrayListIterator implements Iterator{

        private int currentIndex = 0; 
        @Override
        public Object next() {
            // TODO Auto-generated method stub
            Object o = elementData[currentIndex];
            currentIndex++;
            return o;
        }


		@Override
		public boolean harNext() {
			// TODO Auto-generated method stub
			 if(currentIndex >= size)return false;
	            else return true;
		}
    }
 

}
