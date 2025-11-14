public interface MySet<T> {
    boolean put(T element);
    boolean remove(T element);
    boolean contains(T element);
    int size();
}
