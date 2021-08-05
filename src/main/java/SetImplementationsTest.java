import java.util.*;

public class SetImplementationsTest {
    private static NavigableSet<Integer> sortedSet = new TreeSet<>();

    public static void main(String[] args) {
        for(int i = 1; i <= 10; i++) {
            sortedSet.add(i);
        }

        System.out.println(getNextElem(5));
        System.out.println(getPrevElems(5));
    }

    public static Integer getNextElem(Integer elem) {
        return sortedSet.higher(elem);
    }

    public static Set<Integer> getPrevElems(Integer elem) {
        return sortedSet.headSet(elem, false);
    }
}
