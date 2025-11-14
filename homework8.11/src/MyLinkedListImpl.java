public class MyLinkedListImpl<T> implements MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    public MyLinkedListImpl() {
        this.tail = null;
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addFirst(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T element) {
        Node<T> newNode = new Node<>(element);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T element) {
        if(index < 0 || index > size) {
            System.out.println("Error");
        }
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<T> current = getNode(index);
            Node<T> newNode = new Node<>(element);

            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;

            size++;
        }
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public T remove(int index) {
        Node<T> nodeToRemove = getNode(index);
        T data = nodeToRemove.data;

        if (nodeToRemove.prev == null) {
            head = nodeToRemove.next;
        } else {
            nodeToRemove.prev.next = nodeToRemove.next;
        }

        if (nodeToRemove.next == null) {
            tail = nodeToRemove.prev;
        } else {
            nodeToRemove.next.prev = nodeToRemove.prev;
        }

        nodeToRemove.data = null;
        nodeToRemove.prev = null;
        nodeToRemove.next = null;

        size--;
        return data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;

        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // Вспомогательный метод для получения узла по индексу
    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> current;
        if (index < size / 2) {
            // Ищем с начала
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            // Ищем с конца
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }
}
