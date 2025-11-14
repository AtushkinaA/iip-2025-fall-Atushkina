import java.util.ArrayList;
import java.util.List;

public class MySetImpl<T> implements MySet<T> {
    private List<T> list;
    public MySetImpl() {
        list = new ArrayList<>();
    }
    @Override
    public boolean put(T element) {
        if (list.contains(element)) {
            return false;
        }
        return list.add(element);
    }

    @Override
    public boolean remove(T element) {
        return list.remove(element);
    }

    @Override
    public boolean contains(T element) {
        return list.contains(element);
    }

    @Override
    public int size() {
        return list.size();
    }
}
