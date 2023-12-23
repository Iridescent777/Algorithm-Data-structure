public class QueueString implements Queue<String> {




        private LinkedNode front = null;  //维护一个队首指针
        private LinkedNode rear = null;   //维护一个队尾指针

        private static class LinkedNode<String>{
            String element = null;                        //以泛型类型定义栈的element
            LinkedNode<String> next = null;              //以LinkedNode类型定义指针next
            public LinkedNode(String element , LinkedNode<String> next){
                this.element = element;
                this.next = next;
            }

            public LinkedNode getNext(){
                return this.next;              //获取当前结点指针域的值
            }

            public String getElement(){
                return this.element;         //获取当前结点的元素值
            }

            public void setElement(String element) {
                this.element = element;
            }

            public void setNext(LinkedNode<String> next) {
                this.next = next;
            }
        }
        public QueueString(){
            setup();
        }

        public void setup(){
            rear = front = null;
        }

        public boolean isFull(){
            return false;
        }

        public boolean isEmpty(){
            return front == null;
        }

        @Override
        public void clear(){
            rear = front = null;
        }
        @Override
        public void enqueue(String item){
            if(isEmpty()){
                rear = new LinkedNode<>(item,null);
                front = rear;  //front挂到尾上
            }else {
                rear.setNext(new LinkedNode(item, null));  //rear永远在尾指针的位置
                rear = rear.getNext();
            }
        }

        @Override
       public String dequeue(){
               if(!isEmpty()) {
                   String removed = (String)front.getElement();
                   front = front.getNext();       //front指向下一个新的头指针的位置
                   if(front == null){
                       rear = null;              //删除以后为空表，则rear变成null
                   }
                   return removed;
               }
               return null;
       }




        public static void main(String[] args){

        }
    }

