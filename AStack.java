/** Array-based stack implementation */
class AStack<T> implements Stack<T> {

    private static final int defaultSize = 10;

    protected int maxSize;            //基于数组实现的栈能够容纳的最大栈元素个数。
    protected int top;                // 指向栈顶的下一个位置。
    protected T[] listArray;         // 定义泛型类型的数组引用。

    AStack() { this(defaultSize); }
    @SuppressWarnings("unchecked")
    AStack(int size) {
        maxSize = size;
        top = 0;
        listArray = (T[])new Object[size];// 泛型数组的分配方式，这种方式会编译警告，同学们忽略即可。这是Java泛型历史造成的。
    }

    public void clear() { top = 0; }

    public void push(T it) {
        if (isFull()){
            System.out.println("The stack is full.");
            return;
        }
        listArray[top++] = it;             //top指向栈顶的下一个位置
    }

    public T pop() {
        if (isEmpty()){
            System.out.println("The stack is empty.");
            return null;
        }
        return listArray[--top];
    }

    public T topValue() {
        if (isEmpty()){
            System.out.println("The stack is empty.");
            return null;
        }
        return listArray[top-1];
    }

    public int length() { return top; }

    public boolean isEmpty(){ return top == 0;}
    public boolean isFull(){ return top == maxSize;}
    public String toString()
    {
        StringBuilder out = new StringBuilder((length() + 1) * 4);
        out.append("< ");
        for (int i = top-1; i >= 0; i--) {
            out.append(listArray[i]);
            out.append(" ");
        }
        out.append(">");
        return out.toString();
    }
    public static void main(String[] args){
        //测试基于数组实现的泛型Stack
        //其中一个Stack里面存储的元素类型是Integer，一个Stack里面存储的数据类型是String。
        AStack<Integer> intStack = new AStack<>(20);
        AStack<String> stringStack = new AStack<>(20);
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

