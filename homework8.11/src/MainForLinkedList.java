public class MainForLinkedList {
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedListImpl<>();
        list.addFirst("A");
        list.addLast("B");
        list.addLast("C");
        list.add(1, "D");

        System.out.println("Список: " + list); // [A, D, B, C]
        System.out.println("Размер: " + list.size()); // 4

        System.out.println("Элемент по индексу 2: " + list.get(2)); // B

        list.remove(1); // Удаляем "D"
        System.out.println("После удаления: " + list); // [A, B, C]
        System.out.println("Новый размер: " + list.size()); // 3

        list.addFirst("X");
        list.addLast("Z");
        System.out.println("Финальный список: " + list); // [X, A, B, C, Z]

    }
}