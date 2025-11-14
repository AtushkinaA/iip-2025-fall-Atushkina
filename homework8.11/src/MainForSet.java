public class MainForSet {
    public static void main(String[] args) {
        MySet<String> set = new MySetImpl<>();

        System.out.println("Add apple: " + set.put("apple"));
        System.out.println("Add banana: " + set.put("banana"));
        System.out.println("Add apple again: " + set.put("apple"));

        System.out.println("Contains apple: " + set.contains("apple"));
        System.out.println("Contains orange: " + set.contains("orange"));

        System.out.println("Size: " + set.size());
        System.out.println("Remove banana: " + set.remove("banana"));
        System.out.println("Size after remove: " + set.size());
    }
}