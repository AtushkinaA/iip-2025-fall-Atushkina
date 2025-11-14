public interface MyLinkedList<T> {
    void addFirst(T element);
    void addLast(T element);
    void add(int index, T element);
    T get(int index);
    T remove(int index);
    int size();
    String toString();
}
