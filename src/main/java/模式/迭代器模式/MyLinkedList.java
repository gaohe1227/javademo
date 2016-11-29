package 模式.迭代器模式;

public class MyLinkedList  implements MyCollection{

    /**
     * 头结点
     */
    private Node head = null;
    
    /**
     * 当前大小
     */
    private int size = 0;
    
    private Node tail = null;
    
    /**
     * 添加元素
     * @param o
     */
    public void add(Object o){
        Node node = new Node(o,null);
        if(head == null){ //如果头结点为空
            head = node;
            tail = node;
            node.setNext(null);
        }else{
            tail.setNext(node);
            tail = node;
        }
        size++;
    }
    
    /**
     * 获取长度
     * @return
     */
    public int size(){
        return size;
    }
    public Iterator iterator(){
        return new LinkedListIterator();
    }
    
    /**
     * 迭代器的内部类实现
     */
    private class LinkedListIterator implements Iterator{

        private Node currentNode = head;
        
   

        @Override
        public Object next() {
            Node node = currentNode;
            currentNode = currentNode.getNext();
            return node;
        }


		@Override
		public boolean harNext() {
			// TODO Auto-generated method stub
			if(currentNode== null){
                return false;
            }
            else return true;
		}
    }

}
