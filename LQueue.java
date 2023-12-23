public class LQueue implements Queue<Double> {

    private LinkedNode front = null;  //维护一个队首指针
    private LinkedNode rear = null;   //维护一个队尾指针

    private static class LinkedNode<Double>{
        Double element = null;                        //以泛型类型定义栈的element
        LinkedNode<Double> next = null;              //以LinkedNode类型定义指针next
        public LinkedNode(Double element ,LinkedNode<Double> next){
            this.element = element;
            this.next = next;
        }

        public LinkedNode getNext(){
            return this.next;              //获取当前结点指针域的值
        }

        public Double getElement(){
            return this.element;         //获取当前结点的元素值
        }

        public void setElement(Double element) {
            this.element = element;
        }

        public void setNext(LinkedNode<Double> next) {
            this.next = next;
        }
    }
    public LQueue(){
        setup();
    }

    public void setup(){
        front = rear = null;
    }

    public boolean isFull(){
        return false;
    }

    public boolean isEmpty(){
        return front == null;
    }

    public void enqueue(Double newElement){
        if(isEmpty()){
            rear = new LinkedNode<>(newElement,null);
            front = rear;  //front挂到尾上
        }else {
            rear.setNext(new LinkedNode(newElement, null));  //rear永远在尾指针的位置
            rear = rear.getNext();
        }
    }

    public Double dequeue(){
        if(!isEmpty()) {
            Double removed = (Double)front.getElement();
            front = front.getNext();       //front指向下一个新的头指针的位置
            if(front == null){
                rear = null;              //删除以后为空表，则rear变成null
            }
            return removed;
        }
        return null;
    }

    public void clear(){
        front = rear = null;
    }


    public static void main(String[] args){

    }
}
