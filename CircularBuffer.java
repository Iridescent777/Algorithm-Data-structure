public class CircularBuffer<T> extends AStack<T> {

     private int bottom = 0;   //这里要维护一个栈底指针

    public CircularBuffer(int Maxsize){
        super(Maxsize);
    }

    //重写方法

    public boolean isEmpty(){
        if(top == bottom){            //当头尾指针重合的时候就是空栈
            return true;
        }
        return false;
    }

    public boolean isFull(){
        return (top+1)%maxSize == bottom && !isEmpty();
    }

    public void push(T it) {
        listArray[top] = it;             //top指向栈顶的下一个位置
        if(isFull()) {
            bottom = (bottom + 1) % maxSize;     //栈顶向前
        }
        top = (top+1)%maxSize;
    }

    public T pop(){
        if (isEmpty()){
            System.out.println("The stack is empty.");
            return null;
        }
        T Element = listArray[top];
        top = (top-1+maxSize)%maxSize;
        return Element;
    }

    public String toString()
    {
        StringBuilder out = new StringBuilder((length() + 1) * 4);
        out.append("< ");
        if(bottom<top) {
            for (int i = top - 1;i>=bottom;i--) {
                out.append(listArray[i]);
                out.append(" ");
            }
        }
        else{
            for(int i = top -1;i>=0;i--){
                out.append(listArray[i]);
                out.append(" ");
            }
            for(int i = maxSize-1;i>=bottom-1;i--){
                out.append(listArray[i]);
                out.append(" ");
            }
        }
        out.append(">");
        return out.toString();
    }


    public static void main(String[] args){
        CircularBuffer<String> stringStack = new CircularBuffer<>(5);
        //System.out.println(intStack);
        //System.out.println(intStack);
        stringStack.push("a");
        stringStack.push("b");
        stringStack.push("c");
        stringStack.push("d");
        stringStack.push("e");
        System.out.println(stringStack);
        stringStack.push("f");
        System.out.println(stringStack);
        stringStack.pop();
        System.out.println(stringStack);
    }
}
