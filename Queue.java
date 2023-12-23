public interface Queue<T> {

    public void setup();
    public boolean isFull();
    public boolean isEmpty();
    public void enqueue(T newElement);
    public T dequeue();
    public void clear();

}
