public class LStack<T> implements Stack<T> {
        //在 LStack 类中定义个了一个内部静态类，且声明其访问性为 private。如果这样做了，那么这个内部类只能被 LStack 类所使用
        //有很多时候，某一个类需要使用一个新类型，而这个新类型其实只需要被这个类所使用，此时就可以采用内部类的方式来实现，以增强其封装性。
        private static class LinkedNode<T>{    //内部静态类
            T element;                        //以泛型类型定义栈的element
            LinkedNode<T> next;              //以LinkedNode类型定义指针next
            public LinkedNode(T element , LinkedNode<T> next){
                this.element = element;
                this.next = next;
            }
        }
        private LinkedNode<T> top;        //栈顶指针
        private int length;
        public LStack(){                 //初始化栈
            top = null;
            length = 0;
        }
        public void push(T element){     //入栈
            top = new LinkedNode<>(element, top);  //top指向真实的栈顶
            length++;
        }
        public T pop(){
            if( isEmpty()) return null;
            LinkedNode<T> temp = top;
            T element = temp.element;
            top = top.next;
            temp.next = null;
            length--;
            return element;
        }
        public boolean isEmpty(){
            return top == null;
        }
        public boolean isFull(){
            return false;
        }
        public T topValue(){
            if(isEmpty()) return null;
            return top.element;
        }

        @Override
        public int length() {
            return length;
        }

        public void clear(){
            if(isEmpty()) return;
            while(top != null){
                LinkedNode<T> temp = top;
                top = top.next;
                temp.element = null;
                temp.next = null;
            }
        }
        public String toString()
        {
            StringBuilder out = new StringBuilder((length() + 1) * 4);
            LinkedNode<T> temp = top;
            out.append("< ");
            while(temp != null){
                out.append(temp.element);
                out.append(" ");
                temp = temp.next;
            }
            out.append(">");
            return out.toString();
        }
        public static void main(String[] args){
            //测试基于链式存储实现的泛型Stack
            //其中一个Stack里面存储的元素类型是Integer，一个Stack里面存储的数据类型是String。
            LStack<Integer> intStack = new LStack<>();
            LStack<String> stringStack = new LStack<>();
            intStack.push(20);
            intStack.push(30);
            intStack.push(40);
            System.out.println(intStack);
            intStack.pop();
            System.out.println(intStack);
            stringStack.push("hello");
            stringStack.push("world");
            stringStack.push("everyone!");
            System.out.println(stringStack);
            stringStack.pop();
            System.out.println(stringStack);
        }
    }


