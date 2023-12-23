public interface Stack<T> {
    public void clear();  //清空栈

    /** Push an element onto the top of the stack.
     @param it The element being pushed onto the stack. */
    public void push(T it); //入栈

    /** Remove and return the element at the top of the stack.
     @return The element at the top of the stack. */
    public T pop();      //弹栈

    /** @return A copy of the top element. */
    public T topValue();  //访问栈顶

    /** @return The number of elements in the stack. */
    public int length();
    /** @return true if the stack is empty; otherwise false. */
    public boolean isEmpty();
    /** @return true if the stack is full; otherwise false. */
    public boolean isFull();
}
